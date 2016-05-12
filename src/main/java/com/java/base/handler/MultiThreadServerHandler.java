package com.java.base.handler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiThreadServerHandler implements Runnable{
	private Socket clientSocket;
	public MultiThreadServerHandler(Socket clientSocket){
		this.clientSocket=clientSocket;
	}
	public void run() {
		try{
			PrintWriter out=new PrintWriter(clientSocket.getOutputStream(),true);
			BufferedReader in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String inputLine=null;
			while((inputLine=in.readLine())!=null){
				out.println(inputLine);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
