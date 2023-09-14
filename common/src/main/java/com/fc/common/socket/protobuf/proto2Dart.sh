#!/bin/bash

PROTO_SRC_PATH=/Users/fred/workspace/mycode/ideaprojs/springboot_netty_websocket_protobuffer/common/src/main/java/com/fc/common/socket/protobuf
DART_TARGET_PATH=/Users/fred/workspace/mycode/ideaprojs/springboot_netty_websocket_protobuffer/common/src/main/dart



#echo $PROTO_SRC_PATH/*.proto
rm -rf DART_TARGET_PATH/*.dart

cd $PROTO_SRC_PATH

PROTO_MSG_PATH=$DART_TARGET_PATH/proto_msg.dart
rm $PROTO_MSG_PATH
touch $PROTO_MSG_PATH

for i in *.proto
do
echo "generate cli protocol dart code: $i"
echo $DART_TARGET_PATH
protoc --dart_out=$DART_TARGET_PATH ./$i
done


for i in *.proto
do
array=(${i//./ })
name=${array[0]}
echo "import '$name.pb.dart' as $name;" >> $PROTO_MSG_PATH
done



for i in *.proto
do
echo "" >> $PROTO_MSG_PATH
array=(${i//./ })
name=${array[0]}
echo "typedef C2S_${name} = $name.C2S;" >> $PROTO_MSG_PATH
echo "typedef C2SMsg_${name} = $name.C2SMsg;" >> $PROTO_MSG_PATH
echo "typedef S2C_${name} = $name.S2C;" >> $PROTO_MSG_PATH
echo "typedef S2CMsg_${name} = $name.S2CMsg;" >> $PROTO_MSG_PATH
done