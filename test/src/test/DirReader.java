package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DirReader {

	static void readFiles(File dir) {
		File[] files = dir.listFiles();
		for (File file : files) {
			if (!file.isDirectory()) {
				try {
					System.out.println(file);
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					String line;
					while ((line = br.readLine()) != null) {
						System.out.println(line);
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("processing directory file: " + file);
				readFiles(file);
			}

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("/home/preetykumari/Projects/myprojects");
		readFiles(file);

	}

}
