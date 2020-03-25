#!/bin/bash

PROTO_SRC_PATH=/Users/fred/workspace/mycode/ideaprject/nettyprotobuf/common/src/main/java/com/fc/common/socket/protobuf
JAVA_TARGET_PATH=/Users/fred/workspace/mycode/ideaprject/nettyprotobuf/common/src/main/java



#echo $PROTO_SRC_PATH/*.proto
rm -rf JAVA_TARGET_PATH/*.java

cd $PROTO_SRC_PATH

for i in *.proto
do
echo "generate cli protocol java code: $i"
echo $JAVA_TARGET_PATH
protoc --java_out=$JAVA_TARGET_PATH ./$i
done


