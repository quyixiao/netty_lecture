package com.shengsiyuan.netty.demo7_thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import thrift.generated.PersonService;
// Thrift 传输格式
// TBinaryProtocol 二进制格式
// TCompactProtocol 压缩格式
// TJSONProtocol JSON格式
// TSimpleJSONProtocol 提供了JSON 只写协义，生成的文件很容易通过脚本语言解析
// TDebugProtocol 使用易懂的可读的文本格式以便于对 debug
// TSocket 阻塞式的 socket
// TRframedTransport 以 frame 为单位进行传输，非阻塞式服务中使用它
// TFileTransport 以文件的形式进行传输
// TMemoryTransport 将内存用于 I/O ，Java  实现时内部实际使用了简单的 ByteArrayOutputStream。
// TZibTransport 使用 zlib 进行压缩，与其他的传输方式联合使用，无 java 实现
// TSimpleSever 简单的单线程服务模型，常用于测试
// TThreaPoloolServer 多线程服务模型，使用标准的阻塞式 IO
// TNonblockingServer 多线程服务模型，使用非阻塞式 IO(  需要使用 TFramedTransport 数据传输方式)
// THsHaServer -THsHa 引入 了线程池中去处理，其模型把读写任务放到线程池中去处理，Half-sync/Half-async 的处理模式，Half-aysnc 是处理
// IO 事件上（accept/read/write io） /Half-sync 用于 Handler 对 rpc 的同步处理
// Thript 对多语言的支持
//

public class ThriftServer {

    public static void main(String[] args) throws Exception {
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);

        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());
        // 协义层传输
        arg.protocolFactory(new TCompactProtocol.Factory());
        // 传输层
        arg.transportFactory(new TFramedTransport.Factory());
        //
        arg.processorFactory(new TProcessorFactory(processor));


        TServer server = new THsHaServer(arg);

        System.out.println("Thrift Server Started!");

        server.serve();

    }
}
