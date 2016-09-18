var grpc = require('grpc'),
  Rx = require('rxjs');

var PROTO_PATH = __dirname + '/../proto/interchange.proto';

var InterchangeProto = grpc.load(PROTO_PATH).interchange;

function streamPackages (call) {
  // console.log(call);

  var packages = require('../data/packages.json');
  Rx.Observable.from(packages).subscribe(function (package) {
    call.write(package);
  }, function (err) {
    console.error(err.stack);
    call.end();
  }, function () {
    console.log('done streaming, close stream');
    call.end();
  });
}

function authenticateRequest(header) {}

function main() {
  var Server = new grpc.Server();
  Server.addProtoService(InterchangeProto.InterchangeService.service, {
    streamPackages: streamPackages
  });
  Server.bind('0.0.0.0:12345', grpc.ServerCredentials.createInsecure());
  Server.start();
  console.log('started nodejs server');
}

main();
