import grpc
from grpc.framework.common import cardinality
from grpc.framework.interfaces.face import utilities as face_utilities

import interchange_pb2 as interchange__pb2
import interchange_pb2 as interchange__pb2


class InterchangeServiceStub(object):

  def __init__(self, channel):
    """Constructor.

    Args:
      channel: A grpc.Channel.
    """
    self.StreamPackages = channel.unary_stream(
        '/interchange.InterchangeService/StreamPackages',
        request_serializer=interchange__pb2.ObjectID.SerializeToString,
        response_deserializer=interchange__pb2.Package.FromString,
        )


class InterchangeServiceServicer(object):

  def StreamPackages(self, request, context):
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')


def add_InterchangeServiceServicer_to_server(servicer, server):
  rpc_method_handlers = {
      'StreamPackages': grpc.unary_stream_rpc_method_handler(
          servicer.StreamPackages,
          request_deserializer=interchange__pb2.ObjectID.FromString,
          response_serializer=interchange__pb2.Package.SerializeToString,
      ),
  }
  generic_handler = grpc.method_handlers_generic_handler(
      'interchange.InterchangeService', rpc_method_handlers)
  server.add_generic_rpc_handlers((generic_handler,))
