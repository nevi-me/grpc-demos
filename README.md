# GRPC Demos

These are [grpc.io](http://grpc.io) demos for various tasks, I have written them in Node.js and Java, but feel free to contribute more demos in other languages!


## Python

To generate/update the Python gRPC code, you have to manually do it through the terminal.
I don't know if there's a way to let a build tool do it for you, but here's how to do it:

```python
# change folders into the python folder
cd python

# generate code
python -m grpc_tools.protoc -I../proto --python_out=. --grpc_python_out=. ../proto/interchange.proto
```

You'll obviously need to have installed required dependencies, being `grpcio` and `grpcio_tools`.
