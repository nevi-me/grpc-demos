var grpc = require('grpc');

var PROTO_PATH = __dirname + '/../proto/interchange.proto';

var InterchangeProto = grpc.load(PROTO_PATH).interchange;

var client = new InterchangeProto.InterchangeService('192.168.1.2:12346', grpc.credentials.createInsecure());

function streamPackages() {
  // send a random ObjectID and get a stream of random 'packages' back
  var call = client.streamPackages({_id: '1234567890'});

  call.on('data', function (package) {
    console.log(package);
  });
  call.on('end', function () {});
  call.on('error', function (err) {
    console.error(err.stack);
  });
  call.on('status', function (status) {
    console.log(status);
  });
}

var processName = process.argv.shift();
var scriptName = process.argv.shift();
var command = process.argv.shift();

if (command == 'streamPackages')
    return streamPackages();
