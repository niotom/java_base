package com.java.base.io;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.java.base.handler.MultiThreadServerHandler;

public class BlockIoThreadPoolServer {
	private static final int DEFAULT_PORT=8000;
	public static void main(String[] args) {
		try{
			ExecutorService threadPool=Executors.newFixedThreadPool(5);
			ServerSocket serverSocket=new ServerSocket(DEFAULT_PORT);
			Socket clientSocket=null;
			while(true){
				clientSocket=serverSocket.accept();
				//提交到线程池中
				threadPool.submit(new Thread(new MultiThreadServerHandler(clientSocket)));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
