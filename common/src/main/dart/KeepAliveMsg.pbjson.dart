//
//  Generated code. Do not modify.
//  source: KeepAliveMsg.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use c2SDescriptor instead')
const C2S$json = {
  '1': 'C2S',
  '2': [
    {'1': 'timestamp', '3': 1, '4': 1, '5': 3, '10': 'timestamp'},
  ],
};

/// Descriptor for `C2S`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List c2SDescriptor = $convert.base64Decode(
    'CgNDMlMSHAoJdGltZXN0YW1wGAEgASgDUgl0aW1lc3RhbXA=');

@$core.Deprecated('Use s2CDescriptor instead')
const S2C$json = {
  '1': 'S2C',
  '2': [
    {'1': 'timestamp', '3': 1, '4': 1, '5': 3, '10': 'timestamp'},
  ],
};

/// Descriptor for `S2C`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List s2CDescriptor = $convert.base64Decode(
    'CgNTMkMSHAoJdGltZXN0YW1wGAEgASgDUgl0aW1lc3RhbXA=');

@$core.Deprecated('Use c2SMsgDescriptor instead')
const C2SMsg$json = {
  '1': 'C2SMsg',
  '2': [
    {'1': 'c2s', '3': 1, '4': 1, '5': 11, '6': '.tutorial.C2S', '10': 'c2s'},
  ],
};

/// Descriptor for `C2SMsg`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List c2SMsgDescriptor = $convert.base64Decode(
    'CgZDMlNNc2cSHwoDYzJzGAEgASgLMg0udHV0b3JpYWwuQzJTUgNjMnM=');

@$core.Deprecated('Use s2CMsgDescriptor instead')
const S2CMsg$json = {
  '1': 'S2CMsg',
  '2': [
    {'1': 'code', '3': 1, '4': 1, '5': 5, '10': 'code'},
    {'1': 'msg', '3': 2, '4': 1, '5': 9, '10': 'msg'},
    {'1': 's2c', '3': 3, '4': 1, '5': 11, '6': '.tutorial.S2C', '10': 's2c'},
  ],
};

/// Descriptor for `S2CMsg`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List s2CMsgDescriptor = $convert.base64Decode(
    'CgZTMkNNc2cSEgoEY29kZRgBIAEoBVIEY29kZRIQCgNtc2cYAiABKAlSA21zZxIfCgNzMmMYAy'
    'ABKAsyDS50dXRvcmlhbC5TMkNSA3MyYw==');

