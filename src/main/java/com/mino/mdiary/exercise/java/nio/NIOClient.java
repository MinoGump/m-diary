package com.mino.mdiary.exercise.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

/**
 * NIO客户端 
 *
 * @author shirdrn
 */
public class NIOClient {

    private static final Logger log = Logger.getLogger(NIOClient.class.getName());
    private InetSocketAddress inetSocketAddress;

    public NIOClient(String hostname, int port) {
        inetSocketAddress = new InetSocketAddress(hostname, port);
    }

    /**
     * 发送请求数据 
     * @param requestData
     */
    public void send(String requestData) {
        try {
            SocketChannel socketChannel = SocketChannel.open(inetSocketAddress);
            socketChannel.configureBlocking(false);
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            socketChannel.write(ByteBuffer.wrap(requestData.getBytes()));
            while (true) {
                byteBuffer.clear();
                int readBytes = socketChannel.read(byteBuffer);
                if (readBytes > 0) {
                    byteBuffer.flip();
                    log.info("Client: readBytes = " + readBytes);
                    log.info("Client: data = " + new String(byteBuffer.array(), 0, readBytes));
                    socketChannel.close();
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String hostname = "localhost";
        String requestData = "Actions speak louder than words!";
        int port = 8000;
        new NIOClient(hostname, port).send(requestData);
    }
}