package com.xinghaol.io;

import com.google.common.base.Strings;
import com.xinghaol.io.exception.InvalidHeaderException;
import com.xinghaol.io.model.Headers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: lixinghao
 * @date: 2020/7/22 6:54 下午
 * @Description: 基于 Java NIO 实现简单的 HTTP 服务器
 */
public class TinyHttpd {
    private static final int DEFAULT_PORT = 8080;
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final String INDEX_PAGE = "index.html";
    private static final String STATIC_RESOURCE_DIR = "static";
    private static final String META_RESOURCE_DIR_PREFIX = "/meta/";
    private static final String KEY_VALUE_SEPARATOR = ":";
    private static final String CRLF = "\r\n";

    private int port;

    public TinyHttpd() {
        this(DEFAULT_PORT);
    }

    public TinyHttpd(int port) {
        this.port = port;
    }

    /**
     * 启动方法
     */
    public void start() throws IOException {
        // 开启一个serverSocket的管道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress("localhost", port));
        // 设置为非阻塞方式
        ssc.configureBlocking(false);

        // 创建一个轮询socket的selector，底层调用Linux内核epoll_create方法
        Selector selector = Selector.open();
        // 注册事件，使得管道处于一个可以接受连接的状态 accept
        // 当服务端收到客户端的一个连接请求时，‘SelectionKey.OP_ACCEPT’将会触发。在NioEventLoop的事件循环中会对该事件进行处理：
        // 注册事件时，OP_ACCEPT、OP_READ都是一个个的集合，把自己加进到感兴趣的集合里面去
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            // 得到已经准备好I/O的socket
            int readyNumber = selector.select();
            if (readyNumber <= 0) {
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();

                // 用于判断某当前key的channel是否处于一个可以接受IO请求的状态
                if (selectionKey.isAcceptable()) {
                    // 对读请求感兴趣，注册读请求的事件
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    // isReadable用于判断当前key的channel是否已经处于一个可以读的状态

                } else if (selectionKey.isWritable()) {

                }
            }
        }
    }

    /**
     * 处理请求，解析消息头
     *
     * @param selectionKey
     */
    private void request(SelectionKey selectionKey) throws IOException {
        // 从通道中获取请求头数据
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(DEFAULT_BUFFER_SIZE);
        socketChannel.read(byteBuffer);

        // 将limit设置为最大值，防止读取到空白
        byteBuffer.flip();
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        String header = new String(bytes);
    }

    private Headers parseHeaders(String headString) {
        if (Strings.isNullOrEmpty(headString)) {
            throw new InvalidHeaderException();
        }
        // 解析请求头第一行
        int index = headString.indexOf(CRLF);
        if (index == -1) {
            throw new InvalidHeaderException();
        }

        Headers headers = new Headers();
        String firstLine = headString.substring(0, index);
        String[] parts = firstLine.split(" ");

        /**
         * 请求头的第一行必须由三部分构成，分别为Method PATH VERSION
         * eg: GET /index.html HTTP/1.1
         */
        if (parts.length < 3) {
            throw new InvalidHeaderException();
        }
        headers.setMethod(parts[0]);
        headers.setPath(parts[1]);
        headers.setVersion(parts[2]);

        return headers;
    }
}
