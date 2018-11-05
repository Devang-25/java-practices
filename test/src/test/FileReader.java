package test;

import java.io.*;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;




public class FileReader {
	
	public void listFolders(File dir) {
		File[] subdirs= dir.listFiles(new FileFilter() {
			
			public boolean accept(File pathname) {
				return pathname.isHidden()== false && pathname.isDirectory();
			}
		});
	//	System.out.println("\n directory of: " + dir.getAbsolutePath());
		listFiles(dir);
		
		for(File folder: subdirs) {
			listFolders(folder);
		}
		
	}
	
	private void listFiles(File dir) {
		File[] files= dir.listFiles();
		for(File file : files) {
//			if(file.length() >= 1024) {
//				System.out.println(file + " , with length >= 1kb, size: " + 
//			file.length()/1024 + "." + file.length()%1024 + " kb");
//			}
			System.out.println("file: "+ file);
			BufferedReader br=null;
			try {
				java.io.FileReader fr= new java.io.FileReader(file);
				br= new BufferedReader(fr);
				String line;
				while((line= br.readLine()) !=null) {
					System.out.println(line);
					
				}
			} catch(FileNotFoundException err) {
				System.out.println("FileNotFoundException error: "+ err);
			}catch(IOException err) {
				System.out.println("IOException error: "+ err);
			} 
			catch (Error err) {
				System.out.println("Error: "+ err);
			}finally {
	            try {
	                br.close();
	            } catch (IOException e) {
	                System.out.println("Unable to close file: " + file.toString());
	            }
	            catch(NullPointerException ex) {
	            }
	        }
			
			
			
		}
		
	}
	
	static void readFiles(File theFile) {
		LineIterator it=null;
		try {
		 it= FileUtils.lineIterator(theFile, "UTF-8");
		
		    while (it.hasNext()) {
		        String line = it.nextLine();
		        System.out.println(line);
		        // do something with line
		    }
		} catch(IOException e) {
			System.out.println(e);
		}
		finally {
			if(it !=null) {
				LineIterator.closeQuietly(it);
			}
		    
		}
	}
		
	static void readFilesScanner(String path) {
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		    inputStream = new FileInputStream(path);
		    sc = new Scanner(inputStream, "UTF-8");
		    while (sc.hasNextLine()) {
		        String line = sc.nextLine();
		        System.out.println(line);
		    }
		    // note that Scanner suppresses exceptions
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		catch(IOException e) {
			System.out.println(e);
		}
		finally {
		    if (inputStream != null) {
		        try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    if (sc != null) {
		        sc.close();
		    }
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FileReader().listFolders(new File("/home/preetykumari/Projects/myprojects/data-structure"));
	//	FileReader.readFiles(new File("/home/preetykumari/Projects/myprojects/data-structure/bst.js"));
	//	FileReader.readFilesScanner("/home/preetykumari/Projects/myprojects/data-structure/bst.js");
		
	}

}
