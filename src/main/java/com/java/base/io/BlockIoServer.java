package com.java.base.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockIoServer {
	private static final int DEFAULT_PORT=8000;
	public static void main(String[] args) {
		try{
			//服务器端socket
			ServerSocket serverSocket=new ServerSocket(DEFAULT_PORT);
			//监听客户端socket连接,如果有socket进来则获取
			Socket clientSocket=serverSocket.accept();
			BufferedReader in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out=new PrintWriter(clientSocket.getOutputStream());
			String inputLine;
			while((inputLine=in.readLine())!=null){
				out.println(inputLine);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
