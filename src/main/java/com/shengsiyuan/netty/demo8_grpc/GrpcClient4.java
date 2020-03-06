package com.shengsiyuan.netty.demo8_grpc;

import com.shengsiyuan.proto.StreamRequest;
import com.shengsiyuan.proto.StreamResponse;
import com.shengsiyuan.proto.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;

public class GrpcClient4 {


    public static void main(String[] args) throws Exception {

        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899).
                usePlaintext().build();

        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(managedChannel);

        System.out.println("--------------------");


        StreamObserver<StreamRequest> requestStreamObserver = stub.biTalk(new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse value) {
                System.out.println("client : " + value.getResponseInfo());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }
        });

        for (int i = 0; i < 10; ++i) {
            requestStreamObserver.onNext(StreamRequest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).build());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        Thread.sleep(50000);
    }
}




