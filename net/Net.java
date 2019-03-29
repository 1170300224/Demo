package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Scanner;

public class Net {
	
	public static void main(String[] args) throws Exception
	{
		showInternerAddress(null);
		
		System.out.println(getLocalHostLANAddress());
		/*
		showInternerAddress("www.baidu.com");
		showInternerAddress("www.google.com");
		showInternerAddress("www.bilibili.com");
		showInternerAddress("time-a.nist.gov");
		
		showInternetTime();
		*/
		echoServer();
	}
	
	/**
	 * 显示因特网时间。
	 * 联机到公共服务器
	 * @throws IOException
	 */
	public static void showInternetTime() throws IOException
	{
		try(	Socket s = new Socket("time-a.nist.gov",13);
				Scanner in = new Scanner(s.getInputStream(),"UTF-8");)
			{
				while(in.hasNextLine())
				{
					String line = in.nextLine();
					System.out.println(line);
				}
			}
	}
	
	/**
	 * 将域名转换为ip地址
	 * 坑爹啊！本机IP似乎是通过host文件查询的，查到的是以太网ip，，，，，
	 * @param host	域名，sss,ip地址确乎也可以作为参数
	 * @throws UnknownHostException
	 */
	public static void showInternerAddress(String host) throws UnknownHostException
	{
		if(host != null)
		{
			InetAddress[] addresses = InetAddress.getAllByName(host);
			for(InetAddress a : addresses)
			{
				System.out.println(a);
			}
		}
		else
		{
			InetAddress localHostAddress = InetAddress.getLocalHost();
			System.out.println(localHostAddress);
		}
	}
	
	/**
	 * 建立服务器
	 * 回显客户端输入
	 * 可用telnet连入
	 * @throws IOException
	 */
	public static void echoServer() throws IOException
	{
		try(ServerSocket s = new ServerSocket(23333))
		{
			try(Socket incoming = s.accept())	//wait for incoming
			{
				System.out.println("连接成功:"+incoming.getInetAddress());
				InputStream inStream = incoming.getInputStream();
				OutputStream outStream = incoming.getOutputStream();
				
				try(Scanner in = new Scanner(inStream,"UTF-8"))
				{
					PrintWriter out = new PrintWriter(
							new OutputStreamWriter(outStream,"UTF-8"),
							true/*自动刷新*/);
					
					out.println("Hello! Enter BYE to exit.");
					
					boolean done = false;
					while(!done && in.hasNextLine())
					{
						String line = in.nextLine();
						out.println("Echo: " + line);
						if(line.trim().equals("BYE"))
							done = true;
					}
				}
				System.out.println("连接关闭");
			}
		}
	}
	
	/**
	 * 获取本机ip
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static InetAddress getLocalHostLANAddress() throws Exception {
	    try {
	        InetAddress candidateAddress = null;
	        // 遍历所有的网络接口
	        for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
	            NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
	            // 在所有的接口下再遍历IP
	            for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
	                InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
	                if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
	                    if (inetAddr.isSiteLocalAddress()) {
	                        // 如果是site-local地址，就是它了
	                        return inetAddr;
	                    } else if (candidateAddress == null) {
	                        // site-local类型的地址未被发现，先记录候选地址
	                        candidateAddress = inetAddr;
	                    }
	                }
	            }
	        }
	        if (candidateAddress != null) {
	            return candidateAddress;
	        }
	        // 如果没有发现 non-loopback地址.只能用最次选的方案
	        InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
	        return jdkSuppliedAddress;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}




