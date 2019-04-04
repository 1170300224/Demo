package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DirectoryIo 
{
	public static final String path = "io\\file\\";

	public static void main(String[] args) throws IOException
	{
		create();
	}
	
	public static void create() throws IOException
	{
		Files.createDirectory(Paths.get(path+"newDic_test"));	//除最后一个目录部件外，必须已存在;
		
		Files.createDirectories(Paths.get(path+"Dic_test\\newDic_test"));

	}
	
}
