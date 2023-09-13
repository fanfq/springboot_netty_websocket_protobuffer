//
//  Generated code. Do not modify.
//  source: KeepAliveMsg.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class C2S extends $pb.GeneratedMessage {
  factory C2S({
    $fixnum.Int64? timestamp,
  }) {
    final $result = create();
    if (timestamp != null) {
      $result.timestamp = timestamp;
    }
    return $result;
  }
  C2S._() : super();
  factory C2S.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory C2S.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(_omitMessageNames ? '' : 'C2S', package: const $pb.PackageName(_omitMessageNames ? '' : 'tutorial'), createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'timestamp')
    ..hasRequiredFields = false
  ;

  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  C2S clone() => C2S()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  C2S copyWith(void Function(C2S) updates) => super.copyWith((message) => updates(message as C2S)) as C2S;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static C2S create() => C2S._();
  C2S createEmptyInstance() => create();
  static $pb.PbList<C2S> createRepeated() => $pb.PbList<C2S>();
  @$core.pragma('dart2js:noInline')
  static C2S getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<C2S>(create);
  static C2S? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get timestamp => $_getI64(0);
  @$pb.TagNumber(1)
  set timestamp($fixnum.Int64 v) { $_setInt64(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasTimestamp() => $_has(0);
  @$pb.TagNumber(1)
  void clearTimestamp() => clearField(1);
}

class S2C extends $pb.GeneratedMessage {
  factory S2C({
    $fixnum.Int64? timestamp,
  }) {
    final $result = create();
    if (timestamp != null) {
      $result.timestamp = timestamp;
    }
    return $result;
  }
  S2C._() : super();
  factory S2C.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory S2C.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(_omitMessageNames ? '' : 'S2C', package: const $pb.PackageName(_omitMessageNames ? '' : 'tutorial'), createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'timestamp')
    ..hasRequiredFields = false
  ;

  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  S2C clone() => S2C()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  S2C copyWith(void Function(S2C) updates) => super.copyWith((message) => updates(message as S2C)) as S2C;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static S2C create() => S2C._();
  S2C createEmptyInstance() => create();
  static $pb.PbList<S2C> createRepeated() => $pb.PbList<S2C>();
  @$core.pragma('dart2js:noInline')
  static S2C getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<S2C>(create);
  static S2C? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get timestamp => $_getI64(0);
  @$pb.TagNumber(1)
  set timestamp($fixnum.Int64 v) { $_setInt64(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasTimestamp() => $_has(0);
  @$pb.TagNumber(1)
  void clearTimestamp() => clearField(1);
}

class C2SMsg extends $pb.GeneratedMessage {
  factory C2SMsg({
    C2S? c2s,
  }) {
    final $result = create();
    if (c2s != null) {
      $result.c2s = c2s;
    }
    return $result;
  }
  C2SMsg._() : super();
  factory C2SMsg.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory C2SMsg.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(_omitMessageNames ? '' : 'C2SMsg', package: const $pb.PackageName(_omitMessageNames ? '' : 'tutorial'), createEmptyInstance: create)
    ..aOM<C2S>(1, _omitFieldNames ? '' : 'c2s', subBuilder: C2S.create)
    ..hasRequiredFields = false
  ;

  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  C2SMsg clone() => C2SMsg()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  C2SMsg copyWith(void Function(C2SMsg) updates) => super.copyWith((message) => updates(message as C2SMsg)) as C2SMsg;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static C2SMsg create() => C2SMsg._();
  C2SMsg createEmptyInstance() => create();
  static $pb.PbList<C2SMsg> createRepeated() => $pb.PbList<C2SMsg>();
  @$core.pragma('dart2js:noInline')
  static C2SMsg getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<C2SMsg>(create);
  static C2SMsg? _defaultInstance;

  @$pb.TagNumber(1)
  C2S get c2s => $_getN(0);
  @$pb.TagNumber(1)
  set c2s(C2S v) { setField(1, v); }
  @$pb.TagNumber(1)
  $core.bool hasC2s() => $_has(0);
  @$pb.TagNumber(1)
  void clearC2s() => clearField(1);
  @$pb.TagNumber(1)
  C2S ensureC2s() => $_ensure(0);
}

class S2CMsg extends $pb.GeneratedMessage {
  factory S2CMsg({
    $core.int? code,
    $core.String? msg,
    S2C? s2c,
  }) {
    final $result = create();
    if (code != null) {
      $result.code = code;
    }
    if (msg != null) {
      $result.msg = msg;
    }
    if (s2c != null) {
      $result.s2c = s2c;
    }
    return $result;
  }
  S2CMsg._() : super();
  factory S2CMsg.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory S2CMsg.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(_omitMessageNames ? '' : 'S2CMsg', package: const $pb.PackageName(_omitMessageNames ? '' : 'tutorial'), createEmptyInstance: create)
    ..a<$core.int>(1, _omitFieldNames ? '' : 'code', $pb.PbFieldType.O3)
    ..aOS(2, _omitFieldNames ? '' : 'msg')
    ..aOM<S2C>(3, _omitFieldNames ? '' : 's2c', subBuilder: S2C.create)
    ..hasRequiredFields = false
  ;

  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  S2CMsg clone() => S2CMsg()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  S2CMsg copyWith(void Function(S2CMsg) updates) => super.copyWith((message) => updates(message as S2CMsg)) as S2CMsg;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static S2CMsg create() => S2CMsg._();
  S2CMsg createEmptyInstance() => create();
  static $pb.PbList<S2CMsg> createRepeated() => $pb.PbList<S2CMsg>();
  @$core.pragma('dart2js:noInline')
  static S2CMsg getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<S2CMsg>(create);
  static S2CMsg? _defaultInstance;

  @$pb.TagNumber(1)
  $core.int get code => $_getIZ(0);
  @$pb.TagNumber(1)
  set code($core.int v) { $_setSignedInt32(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasCode() => $_has(0);
  @$pb.TagNumber(1)
  void clearCode() => clearField(1);

  @$pb.TagNumber(2)
  $core.String get msg => $_getSZ(1);
  @$pb.TagNumber(2)
  set msg($core.String v) { $_setString(1, v); }
  @$pb.TagNumber(2)
  $core.bool hasMsg() => $_has(1);
  @$pb.TagNumber(2)
  void clearMsg() => clearField(2);

  @$pb.TagNumber(3)
  S2C get s2c => $_getN(2);
  @$pb.TagNumber(3)
  set s2c(S2C v) { setField(3, v); }
  @$pb.TagNumber(3)
  $core.bool hasS2c() => $_has(2);
  @$pb.TagNumber(3)
  void clearS2c() => clearField(3);
  @$pb.TagNumber(3)
  S2C ensureS2c() => $_ensure(2);
}


const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames = $core.bool.fromEnvironment('protobuf.omit_message_names');
