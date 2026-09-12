// unpacking code

import java.io.*;
import java.util.*;

class Unpack
{
	public static void unpackArchive(String FileName, String Password) throws Exception
	{
		// variable creation
		int FileSize = 0;
		// Scanner sobj = null;
		// String FileName = null;
		File fpackobj = null;
		FileInputStream fiobj = null;
		byte bHeader[] = new byte[100];
		String Header = null;
		String Tokens[] = null;
		File fobj = null;
		FileOutputStream foobj = null;
		byte Buffer[] = null;
		// byte Key = 0x11; // same as encryption key, for decryption
		int i = 0;
		int j = 0;
		int iRet = 0;

		// sobj = new Scanner(System.in);

		// System.out.println("Enter the name packed file: ");
		// FileName = sobj.nextLine();

		// System.out.println("Enter password (for highest security): ");
		// String Password = sobj.nextLine();
		byte[] PasswordArray = Password.getBytes();
		int passLen = PasswordArray.length;
		int p = 0; // for password index

		fpackobj = new File(FileName);

		if(!fpackobj.exists())
		{
			System.out.println("Error: There is no such packed file.");
			return;
		}

		fiobj = new FileInputStream(fpackobj);

		// read the header
		while((iRet = fiobj.read(bHeader, 0, 100)) != -1)
		{
			Header = new String(bHeader);

			Header = Header.trim();

			Tokens = Header.split(" ");

			System.out.println("File name: " + Tokens[0]);
			System.out.println("File size: " + Tokens[1]);

			fobj = new File(Tokens[0]);

			fobj.createNewFile();

			foobj = new FileOutputStream(fobj);
			
			FileSize = Integer.parseInt(Tokens[1]);
			
			// buffer for reading the data
			Buffer = new byte[FileSize];

			// read from packed file
			fiobj.read(Buffer, 0, FileSize);

			p = 0;

			for(j = 0; j < FileSize; j++)
			{
				Buffer[j] = (byte)(Buffer[j] ^ PasswordArray[p]);
				p++;
				if(p == passLen)
				{
					p = 0;
				}
			}

			// write into extracted file
			foobj.write(Buffer, 0, FileSize);
		}

		// close stuff
		// sobj.close();
		fiobj.close();
	}
}
