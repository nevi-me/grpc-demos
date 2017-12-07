package server

import io.grpc.Metadata
import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.grpc.stub.StreamObserver
import io.reactivex.Observable
import java.io.IOException
import java.util.logging.Logger
import proto.*

/**
 * Created by Neville on 9/18/2016.
 */
class InterchangeServer(serverBuilder: ServerBuilder<*>, port:Int) {
    private var port: Int = 0
    private val server: Server

    /**
     * Create a RouteGuide server listening on {@code port} using {@code featureFile} database.
     */
    @Throws(IOException::class)
    constructor(port:Int) : this(ServerBuilder.forPort(port), port) {}
    init{
        this.port = port
        server = serverBuilder.addService(InterchangeService()).build()
//        server = serverBuilder.addService(ServerInterceptors.intercept(TransitService(), HeaderServerInterceptor())).build()
    }
    /**
     * Start serving requests.
     */
    @Throws(IOException::class)
    fun start() {
        server.start()
        logger.info("Server started, listening on " + port)
        Runtime.getRuntime().addShutdownHook(object:Thread() {
            override fun run() {
                // Use stderr here since the logger may has been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down")
                this@InterchangeServer.stop()
                System.err.println("*** server shut down")
            }
        })
    }
    /**
     * Stop serving requests and shutdown resources.
     */
    fun stop() {
        server.shutdown()
    }
    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    @Throws(InterruptedException::class)
    fun blockUntilShutdown() {
        server.awaitTermination()
    }
    /**
     * Our implementation of RouteGuide service.
     *
     * <p>See route_guide.proto for details of the methods.
     */
    private class InterchangeService: InterchangeServiceGrpc.InterchangeServiceImplBase() {

        init{

        }

        override fun streamPackages(request: InterchangeProtos.ObjectID, responseObserver: StreamObserver<InterchangeProtos.Package>) {

            // as an example, return an error with metadata if ObjectID == "error"
            // please remember that these are just demos to help people with some concepts within gRPC
            // for people who aren't familiar/comfortable with Kotlin, I've added types for each variable so you can follow

            if (request.id == "error") {
                // create new trailer metadata
                val trailerMeta: Metadata = Metadata()

                // add string metadata, note that you use ASCII_STRING_MARSHALLER
                trailerMeta.put(Metadata.Key.of("String-header", Metadata.ASCII_STRING_MARSHALLER), "a string field")

                // create a Protobuf message that we can send as the binary trailer key
                val trailerPackage: InterchangeProtos.Package.Builder = InterchangeProtos.Package
                        .newBuilder()
                        .setName("Error Package")

                // build the trailerPackage, and convert it to a ByteArray
                // note that the key of binary payload should end with "-bin"
                trailerMeta.put(Metadata.Key
                        .of("Binary-header-bin", Metadata.BINARY_BYTE_MARSHALLER), trailerPackage.build().toByteArray())

                // Construct our StatusRuntimeException, which we'll send with the onError.
                // Notice that I'm passing the metadata into the SRE.
                // When you throw the default SRE, the client gets a default, which I believe is Status.Code.INTERNAL,
                // so to offer more details, you should change it to something else. Refer to the HTTP - gRPC error mapping
                val error = StatusRuntimeException(Status.fromCode(Status.Code.INVALID_ARGUMENT)
                        .withDescription("You supplied an incorrect object ID"), trailerMeta)

                // now send an onError with the error message that you've just created
                responseObserver.onError(error)
                
                return
            }

            val packages: MutableList<InterchangeProtos.Package> = mutableListOf()

            val names = listOf("Package 1", "Package 2", "Package 3")

            packages.addAll(names.map {
                InterchangeProtos.Package.newBuilder().setName(it).build()
            })

            // return packages as a stream
            Observable.fromIterable(packages).subscribe({
                responseObserver.onNext(it)
            }, {
                responseObserver.onError(it)
            }, {
                responseObserver.onCompleted()
            })
        }

    }
    companion object {
        private val logger = Logger.getLogger(InterchangeServer::class.java.name)
        /**
         * Main method. This comment makes the linter happy.
         */
        //        @Throws(Exception::class)
//        @JvmStatic fun main(args:Array<String>) {
//            val server = TransitServer(8980)
//            server.start()
//            server.blockUntilShutdown()
//        }
    }
}