package com.java.base.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


public class NonBlokingServer {
	private static final int DEFAULT_PORT=8000;
	public static void main(String[] args) {
		ServerSocketChannel serverSocketChannel;
		Selector selector;
		try{
			serverSocketChannel=ServerSocketChannel.open();
			InetSocketAddress address=new InetSocketAddress(DEFAULT_PORT);
			serverSocketChannel.bind(address);
			serverSocketChannel.configureBlocking(false);
			selector=Selector.open();
			serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
			while(true){
				selector.select();
				Set<SelectionKey> readyKeys=selector.selectedKeys();
				Iterator<SelectionKey> iterator=readyKeys.iterator();
				while(iterator.hasNext()){
					SelectionKey key=iterator.next();
					iterator.remove();
					try{
						if(key.isAcceptable()){
							ServerSocketChannel server=(ServerSocketChannel)key.channel();
							SocketChannel client=server.accept();
							client.configureBlocking(false);
							SelectionKey clientKey=client.register(selector, SelectionKey.OP_WRITE|SelectionKey.OP_READ);
							ByteBuffer buffer=ByteBuffer.allocate(100);
							clientKey.attach(buffer);
						}
						if(key.isReadable()){
							SocketChannel client=(SocketChannel)key.channel();
							ByteBuffer output=(ByteBuffer)key.attachment();
							client.read(output);
						}
						if(key.isWritable()){
							SocketChannel client=(SocketChannel)key.channel();
							ByteBuffer output=(ByteBuffer)key.attachment();
							output.flip();
							client.write(output);
							output.compact();
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
