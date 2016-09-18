package proto;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.1)",
    comments = "Source: interchange.proto")
public class InterchangeServiceGrpc {

  private InterchangeServiceGrpc() {}

  public static final String SERVICE_NAME = "interchange.InterchangeService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<proto.InterchangeProtos.ObjectID,
      proto.InterchangeProtos.Package> METHOD_STREAM_PACKAGES =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          generateFullMethodName(
              "interchange.InterchangeService", "StreamPackages"),
          io.grpc.protobuf.ProtoUtils.marshaller(proto.InterchangeProtos.ObjectID.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(proto.InterchangeProtos.Package.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static InterchangeServiceStub newStub(io.grpc.Channel channel) {
    return new InterchangeServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static InterchangeServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new InterchangeServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static InterchangeServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new InterchangeServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class InterchangeServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void streamPackages(proto.InterchangeProtos.ObjectID request,
        io.grpc.stub.StreamObserver<proto.InterchangeProtos.Package> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_STREAM_PACKAGES, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_STREAM_PACKAGES,
            asyncServerStreamingCall(
              new MethodHandlers<
                proto.InterchangeProtos.ObjectID,
                proto.InterchangeProtos.Package>(
                  this, METHODID_STREAM_PACKAGES)))
          .build();
    }
  }

  /**
   */
  public static final class InterchangeServiceStub extends io.grpc.stub.AbstractStub<InterchangeServiceStub> {
    private InterchangeServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InterchangeServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InterchangeServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InterchangeServiceStub(channel, callOptions);
    }

    /**
     */
    public void streamPackages(proto.InterchangeProtos.ObjectID request,
        io.grpc.stub.StreamObserver<proto.InterchangeProtos.Package> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_STREAM_PACKAGES, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class InterchangeServiceBlockingStub extends io.grpc.stub.AbstractStub<InterchangeServiceBlockingStub> {
    private InterchangeServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InterchangeServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InterchangeServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InterchangeServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<proto.InterchangeProtos.Package> streamPackages(
        proto.InterchangeProtos.ObjectID request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_STREAM_PACKAGES, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class InterchangeServiceFutureStub extends io.grpc.stub.AbstractStub<InterchangeServiceFutureStub> {
    private InterchangeServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InterchangeServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InterchangeServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InterchangeServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_STREAM_PACKAGES = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final InterchangeServiceImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(InterchangeServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_STREAM_PACKAGES:
          serviceImpl.streamPackages((proto.InterchangeProtos.ObjectID) request,
              (io.grpc.stub.StreamObserver<proto.InterchangeProtos.Package>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_STREAM_PACKAGES);
  }

}
