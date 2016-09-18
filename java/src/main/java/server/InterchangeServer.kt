package server

import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import rx.Subscriber
import java.io.IOException
import java.util.logging.Logger
import proto.*
import rx.Observable
import java.util.concurrent.TimeUnit

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
    private class InterchangeService(): InterchangeServiceGrpc.InterchangeServiceImplBase() {

        init{

        }

        override fun streamPackages(request: InterchangeProtos.ObjectID?, responseObserver: StreamObserver<InterchangeProtos.Package>) {
            val packages: MutableList<InterchangeProtos.Package> = mutableListOf()

            val names = listOf("Package 1", "Package 2", "Package 3")

            packages.addAll(names.map {
                InterchangeProtos.Package.newBuilder().setName(it).build()
            })
            Observable.from(packages).subscribe(object: Subscriber<InterchangeProtos.Package>() {
                override fun onCompleted() {
                    responseObserver.onCompleted()
                    println("done sending streams to client")
                }

                override fun onNext(t: InterchangeProtos.Package?) {
                    responseObserver.onNext(t)
                    println("sent $t to client")
                }

                override fun onError(e: Throwable?) {
                    responseObserver.onError(e)
                }
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