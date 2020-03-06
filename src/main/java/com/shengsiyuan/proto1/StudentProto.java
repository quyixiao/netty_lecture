// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Student.proto

package com.shengsiyuan.proto1;

public final class StudentProto {
  private StudentProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_shengsiyuan_proto_MyRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_shengsiyuan_proto_MyRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_shengsiyuan_proto_MyResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_shengsiyuan_proto_MyResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_shengsiyuan_proto_StudentRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_shengsiyuan_proto_StudentRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_shengsiyuan_proto_StudentResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_shengsiyuan_proto_StudentResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_shengsiyuan_proto_StudentResponseList_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_shengsiyuan_proto_StudentResponseList_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_shengsiyuan_proto_StreamRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_shengsiyuan_proto_StreamRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_shengsiyuan_proto_StreamResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_shengsiyuan_proto_StreamResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rStudent.proto\022\025com.shengsiyuan.proto\"\035" +
      "\n\tMyRequest\022\020\n\010username\030\001 \001(\t\"\036\n\nMyRespo" +
      "nse\022\020\n\010realname\030\002 \001(\t\"\035\n\016StudentRequest\022" +
      "\013\n\003age\030\001 \001(\005\":\n\017StudentResponse\022\014\n\004name\030" +
      "\001 \001(\t\022\013\n\003age\030\002 \001(\005\022\014\n\004city\030\003 \001(\t\"V\n\023Stud" +
      "entResponseList\022?\n\017studentResponse\030\001 \003(\013" +
      "2&.com.shengsiyuan.proto.StudentResponse" +
      "\"%\n\rStreamRequest\022\024\n\014request_info\030\001 \001(\t\"" +
      "\'\n\016StreamResponse\022\025\n\rresponse_info\030\001 \001(\t" +
      "2\247\003\n\016StudentService\022^\n\025GetRealNameByUser" +
      "name\022 .com.shengsiyuan.proto.MyRequest\032!" +
      ".com.shengsiyuan.proto.MyResponse\"\000\022e\n\020G" +
      "etStudentsByAge\022%.com.shengsiyuan.proto." +
      "StudentRequest\032&.com.shengsiyuan.proto.S" +
      "tudentResponse\"\0000\001\022q\n\030GetStudentsWrapper" +
      "ByAges\022%.com.shengsiyuan.proto.StudentRe" +
      "quest\032*.com.shengsiyuan.proto.StudentRes" +
      "ponseList\"\000(\001\022[\n\006BiTalk\022$.com.shengsiyua" +
      "n.proto.StreamRequest\032%.com.shengsiyuan." +
      "proto.StreamResponse\"\000(\0010\001B(\n\026com.shengs" +
      "iyuan.proto1B\014StudentProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_com_shengsiyuan_proto_MyRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_shengsiyuan_proto_MyRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_shengsiyuan_proto_MyRequest_descriptor,
        new java.lang.String[] { "Username", });
    internal_static_com_shengsiyuan_proto_MyResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_shengsiyuan_proto_MyResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_shengsiyuan_proto_MyResponse_descriptor,
        new java.lang.String[] { "Realname", });
    internal_static_com_shengsiyuan_proto_StudentRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_com_shengsiyuan_proto_StudentRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_shengsiyuan_proto_StudentRequest_descriptor,
        new java.lang.String[] { "Age", });
    internal_static_com_shengsiyuan_proto_StudentResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_com_shengsiyuan_proto_StudentResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_shengsiyuan_proto_StudentResponse_descriptor,
        new java.lang.String[] { "Name", "Age", "City", });
    internal_static_com_shengsiyuan_proto_StudentResponseList_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_com_shengsiyuan_proto_StudentResponseList_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_shengsiyuan_proto_StudentResponseList_descriptor,
        new java.lang.String[] { "StudentResponse", });
    internal_static_com_shengsiyuan_proto_StreamRequest_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_com_shengsiyuan_proto_StreamRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_shengsiyuan_proto_StreamRequest_descriptor,
        new java.lang.String[] { "RequestInfo", });
    internal_static_com_shengsiyuan_proto_StreamResponse_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_com_shengsiyuan_proto_StreamResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_shengsiyuan_proto_StreamResponse_descriptor,
        new java.lang.String[] { "ResponseInfo", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
