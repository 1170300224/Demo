package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * 文本格式数据读写
 * @author s
 *
 */
public class TextIo 
{
	public static final String path = "io\\file\\";
	
	public static void main(String[] args) throws IOException
	{
		fileOutput_Files();
	}
	
	public static void base()
	{
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		while(in.hasNext())
		{
			System.out.println(in.nextLine().toUpperCase());
		}
		
	}
	
	public static void reader() throws IOException
	{
		Reader in = new InputStreamReader(System.in);
		
		while(true)
		{
			System.out.println(in.read());
		}
	}
	
	public static void fileOutput_PrintWriter() throws FileNotFoundException, UnsupportedEncodingException
	{
		PrintWriter out1 = new PrintWriter(path + "output_test1.txt","UTF-8");
		out1.println("123456");
		out1.close();
		
		//同out1?
		PrintWriter out2 = new PrintWriter(
				new OutputStreamWriter(
						new FileOutputStream(path + "output_test2.txt"),"UTF-8"),true/*自动冲刷*/);
		out2.println("123456");
		out2.close();
	}
	
	/**
	 * 处理中小长度文件
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static void fileOutput_Files() throws UnsupportedEncodingException, IOException
	{
		String content = "abcdefg\n1234567";
				
		Files.write(Paths.get(path+"output_tes3.txt"), content.getBytes("UTF-8"),StandardOpenOption.CREATE);
		
		Files.write(Paths.get(path+"output_tes3.txt"), "\noh hhh".getBytes("UTF-8"),StandardOpenOption.APPEND);
		
		@SuppressWarnings("serial")
		List<String> lines = new ArrayList<>(2) {{add("123");add("456");}};
		
		Files.write(Paths.get(path+"output_tes4.txt"),lines);
	}
	
	public static void fileInput_Files() throws IOException
	{
		
		String content = new String(Files.readAllBytes(Paths.get(path + "input_test1.txt")),"UTF-8");
		System.out.println(content);

		List<String> lines = Files.readAllLines(Paths.get(path+"input_test1.txt"),Charset.forName("UTF-8"));
		for(String line:lines)
		{
			System.out.println(line);
		}
		
		try(Stream<String> linesStream = Files.lines(Paths.get(path+"input_test1.txt"),Charset.forName("UTF-8")))
		{
			linesStream.forEach(s->{
				System.out.println(s);
			});
		}
	}
	
}











