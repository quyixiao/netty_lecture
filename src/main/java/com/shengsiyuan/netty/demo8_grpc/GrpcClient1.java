package com.shengsiyuan.netty.demo8_grpc;


import com.shengsiyuan.proto.MyRequest;
import com.shengsiyuan.proto.MyResponse;
import com.shengsiyuan.proto.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient1 {

    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899).
                usePlaintext().build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.
                newBlockingStub(managedChannel);


        MyResponse myResponse = blockingStub.
                getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());

        System.out.println(myResponse.getRealname());

    }
}