package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesIo 
{
	public static final String path = "io\\file\\";

	public static void main(String[] args)
	{
		
	}
	
	public void create() throws IOException
	{
		Files.createFile(Paths.get(path+"newFile")); 	//�ļ����Ѵ��ڣ����쳣�����ʹ�����ԭ���Ե�
	}
	
}
