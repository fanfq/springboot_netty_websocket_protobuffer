#!/bin/bash

PROTO_SRC_PATH=/Users/fred/workspace/mycode/ideaprojs/springboot_netty_websocket_protobuffer/common/src/main/java/com/fc/common/socket/protobuf
DART_TARGET_PATH=/Users/fred/workspace/mycode/ideaprojs/springboot_netty_websocket_protobuffer/common/src/main/dart



#echo $PROTO_SRC_PATH/*.proto
rm -rf DART_TARGET_PATH/*.dart

cd $PROTO_SRC_PATH

for i in *.proto
do
echo "generate cli protocol dart code: $i"
echo $DART_TARGET_PATH
protoc --dart_out=$DART_TARGET_PATH ./$i
done