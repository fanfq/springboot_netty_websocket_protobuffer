//
//  Generated code. Do not modify.
//  source: ProtoID.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use msgTypeDescriptor instead')
const MsgType$json = {
  '1': 'MsgType',
  '2': [
    {'1': 'DEF', '2': 0},
    {'1': 'MSG_REG', '2': 1},
    {'1': 'MSG_REG_PUSH', '2': 11},
    {'1': 'PING', '2': 1001},
    {'1': 'PONG', '2': 1901},
  ],
};

/// Descriptor for `MsgType`. Decode as a `google.protobuf.EnumDescriptorProto`.
final $typed_data.Uint8List msgTypeDescriptor = $convert.base64Decode(
    'CgdNc2dUeXBlEgcKA0RFRhAAEgsKB01TR19SRUcQARIQCgxNU0dfUkVHX1BVU0gQCxIJCgRQSU'
    '5HEOkHEgkKBFBPTkcQ7Q4=');

