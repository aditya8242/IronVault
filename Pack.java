// packing code

import java.io.*;
import java.util.*;

class Pack
{
	public static void packDirectory(String DirName, String PackName, String Password) throws Exception
	{
		int iRet = 0;
		// byte Key = 0x11;
		int i = 0, j = 0;
		// Scanner sobj = new Scanner(System.in);
		byte[] Buffer = new byte[1024];

		byte bHeader[] = new byte[100];

		String Header = null;

		// System.out.println("Enter directory name: ");
		// String DirName = sobj.nextLine();

		// System.out.println("Enter the name of packed file: ");
		// String PackName = sobj.nextLine();

		// System.out.println("Enter password (for highest security): ");
		// String Password = sobj.nextLine();
		byte[] PasswordArray = Password.getBytes();
		int passLen = PasswordArray.length;
		int p = 0; // password index

		File fobj = new File(DirName);

		if(fobj.exists() && fobj.isDirectory())
		{
			File PackObj = new File(PackName);

			PackObj.createNewFile();

			FileOutputStream foobj = new FileOutputStream(PackObj);
			FileInputStream fiobj = null;

			System.out.println("Directory is present.");

			File fArr[] = fobj.listFiles();

			System.out.println("Number of files in the directory are " + fArr.length + ".");

			String[] extensions = {".txt", ".pdf", ".docx", ".xlsx", ".csv", 
                ".jpg", ".jpeg", ".png", ".mp4",
                ".java", ".cpp", ".c", ".py", ".json", ".xml", ".html", ".zip"};

			for(i = 0; i < fArr.length; i++)
			{
				String filename = fArr[i].getName().toLowerCase();
				boolean isValid = false;

				for(String ext : extensions)
				{
					if(filename.endsWith(ext))
					{
						isValid = true;
						break;
					}
				}

				if(fArr[i].isFile() && !fArr[i].isHidden() && isValid)
				{
					fiobj = new FileInputStream(fArr[i]);

					// header formation
					Header = fArr[i].getName() + " " + fArr[i].length();

					for(j = Header.length(); j < 100; j++)
					{
						Header = Header + " ";
					}

					bHeader = Header.getBytes();

					// write header into packed file
					foobj.write(bHeader, 0, 100);

					p = 0;

					// read the data from input files from folder
					while((iRet = fiobj.read(Buffer)) != -1)
					{
						// encryption logic
						for(j = 0; j < iRet; j++)
						{
							Buffer[j] = (byte)(Buffer[j] ^ PasswordArray[p]);
							p++;
							if(p == passLen)
							{
								p = 0;
							}
						}

						// write the file's data into pack file
						foobj.write(Buffer, 0, iRet);
					}

					fiobj.close();
				}
			}

			foobj.close();
		}
		else
		{
			System.out.println("Directory is not present.");
		}

		// sobj.close();
	}
}
