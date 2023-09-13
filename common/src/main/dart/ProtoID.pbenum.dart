//
//  Generated code. Do not modify.
//  source: ProtoID.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

/// 消息头
class MsgType extends $pb.ProtobufEnum {
  static const MsgType DEF = MsgType._(0, _omitEnumNames ? '' : 'DEF');
  static const MsgType MSG_REG = MsgType._(1, _omitEnumNames ? '' : 'MSG_REG');
  static const MsgType MSG_REG_PUSH = MsgType._(11, _omitEnumNames ? '' : 'MSG_REG_PUSH');
  static const MsgType PING = MsgType._(1001, _omitEnumNames ? '' : 'PING');
  static const MsgType PONG = MsgType._(1901, _omitEnumNames ? '' : 'PONG');

  static const $core.List<MsgType> values = <MsgType> [
    DEF,
    MSG_REG,
    MSG_REG_PUSH,
    PING,
    PONG,
  ];

  static final $core.Map<$core.int, MsgType> _byValue = $pb.ProtobufEnum.initByValue(values);
  static MsgType? valueOf($core.int value) => _byValue[value];

  const MsgType._($core.int v, $core.String n) : super(v, n);
}


const _omitEnumNames = $core.bool.fromEnvironment('protobuf.omit_enum_names');
