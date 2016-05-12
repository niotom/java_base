package com.java.base.io;

import java.net.ServerSocket;
import java.net.Socket;

import com.java.base.handler.MultiThreadServerHandler;

public class MultiThreadBlockIoServer {
	private static final int DEFAULT_PORT=8000;
	public static void main(String[] args) {
		try{
			Socket clientSocket=null;
			ServerSocket serverSocket=new ServerSocket(DEFAULT_PORT);
			while(true){
				clientSocket=serverSocket.accept();
				//多线程handler处理,主线程可以接受其他客户端的socket
				new Thread(new MultiThreadServerHandler(clientSocket)).start();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
