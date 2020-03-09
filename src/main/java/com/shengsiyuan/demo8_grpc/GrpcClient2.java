package com.shengsiyuan.demo8_grpc;

import com.shengsiyuan.proto.StudentRequest;
import com.shengsiyuan.proto.StudentResponse;
import com.shengsiyuan.proto.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class GrpcClient2 {


    public static void main(String[] args) {

        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899).
                usePlaintext().build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.
                newBlockingStub(managedChannel);



        System.out.println("--------------------");

        Iterator<StudentResponse> iter = blockingStub.
                getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());

        while (iter.hasNext()) {
            StudentResponse studentResponse = iter.next();

            System.out.println(studentResponse.getName() + ", " + studentResponse.getAge() + ", " + studentResponse.getCity());
        }


    }
}




