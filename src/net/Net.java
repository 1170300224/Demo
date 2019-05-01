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
	 * ��ʾ������ʱ�䡣
	 * ����������������
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
	 * ������ת��Ϊip��ַ
	 * �ӵ���������IP�ƺ���ͨ��host�ļ���ѯ�ģ��鵽������̫��ip����������
	 * @param host	������sss,ip��ַȷ��Ҳ������Ϊ����
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
	 * ����������
	 * ���Կͻ�������
	 * ����telnet����
	 * @throws IOException
	 */
	public static void echoServer() throws IOException
	{
		try(ServerSocket s = new ServerSocket(23333))
		{
			try(Socket incoming = s.accept())	//wait for incoming
			{
				System.out.println("���ӳɹ�:"+incoming.getInetAddress());
				InputStream inStream = incoming.getInputStream();
				OutputStream outStream = incoming.getOutputStream();
				
				try(Scanner in = new Scanner(inStream,"UTF-8"))
				{
					PrintWriter out = new PrintWriter(
							new OutputStreamWriter(outStream,"UTF-8"),
							true/*�Զ�ˢ��*/);
					
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
				System.out.println("���ӹر�");
			}
		}
	}
	
	/**
	 * ��ȡ����ip
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static InetAddress getLocalHostLANAddress() throws Exception {
	    try {
	        InetAddress candidateAddress = null;
	        // �������е�����ӿ�
	        for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
	            NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
	            // �����еĽӿ����ٱ���IP
	            for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
	                InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
	                if (!inetAddr.isLoopbackAddress()) {// �ų�loopback���͵�ַ
	                    if (inetAddr.isSiteLocalAddress()) {
	                        // �����site-local��ַ����������
	                        return inetAddr;
	                    } else if (candidateAddress == null) {
	                        // site-local���͵ĵ�ַδ�����֣��ȼ�¼��ѡ��ַ
	                        candidateAddress = inetAddr;
	                    }
	                }
	            }
	        }
	        if (candidateAddress != null) {
	            return candidateAddress;
	        }
	        // ���û�з��� non-loopback��ַ.ֻ�������ѡ�ķ���
	        InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
	        return jdkSuppliedAddress;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}




