package io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class BinaryIo 
{
	public static final String path = "io\\file\\";

	
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		ObjectIo();
	}
	
	public static void data_input_output() throws IOException
	{
		DataOutputStream out = new DataOutputStream(new FileOutputStream(path+"data_IO_test1.dat"));
		out.writeInt(1);
		out.writeDouble(2.9);
		out.close();
		
		DataInputStream in = new DataInputStream(new FileInputStream(path+"data_IO_test1.dat"));
		System.out.println(in.readInt());
		System.out.println(in.readDouble());
		in.close();
	}
	
	public static void randomAccess() throws IOException
	{
		RandomAccessFile inOut = new RandomAccessFile(path+"data_IO_test2.dat","rw");
		
		if(inOut.length() == 0)
		{
			inOut.writeInt(1);
			inOut.writeInt(2);
			inOut.writeInt(3);
			inOut.close();
			return;
		}
		else
		{
			System.out.println(inOut.readInt());
			inOut.seek(4*(3-1));
			System.out.println(inOut.readInt());
			inOut.close();
			return;
		}
	}
	
	public static void ObjectIo() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		
		class Staff implements Serializable
		{
			private static final long serialVersionUID = 2447346017815601205L;
			@SuppressWarnings("unused")
			public int a = 10;
			public String b = "Aj";
		}
		
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path+"objectIo_test.dat"));
		out.writeInt(1);
		out.writeObject(new Staff());
		out.close();
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(path+"objectIo_test.dat"));
		System.out.println(in.readInt());
		System.out.println(((Staff)in.readObject()).b);
		in.close();
	}
	
	/**
	 * 不可用！
	 * @throws IOException
	 */
	public static void zipIo() throws IOException
	{
		ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path+"zip_IO_test.zip"));
		
		DataOutputStream out1 = new DataOutputStream(new FileOutputStream(path+"data_for_zip_IO_test.dat"));
		out1.writeInt(1);
		out1.writeDouble(2.9);
		out1.close();
		
		PrintWriter out2 = new PrintWriter(path+"text_for_zip_IO_test.txt","UTF-8");
		out2.println("abcdefg");
		out2.println("1234567");
		out2.close();
		
		ZipEntry ze = new ZipEntry(path+"data_for_zip_IO_test.dat");
		zout.putNextEntry(ze);
		
		zout.closeEntry();
		
		ze = new ZipEntry(path+"text_for_zip_IO_test.txt");
		zout.putNextEntry(ze);
		zout.closeEntry();
		
		zout.close();
		
		//<<java核心技术 2>>上的方法确乎不可行
		/*
		ZipInputStream zin = new ZipInputStream(new FileInputStream(path+"zip_IO_test.zip"));
		ZipEntry entry;
		while((entry = zin.getNextEntry()) != null)
		{
			InputStream in = zin.getInputStream(entry);	//无此方法
		}
		*/
	}
	
	
}









