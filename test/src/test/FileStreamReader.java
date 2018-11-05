package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileStreamReader {
	
	public void writeToFile(String fileName, byte[] content) throws IOException {
	    try (FileOutputStream os = new FileOutputStream(fileName)) {
	        os.write(content);
	    }
	}


//	Can you spot the bug?
//
//	What about this method to read the files back again?

	public byte[] readFromFile(String fileName) throws IOException {
	    byte[] buf = new byte[8192];
	    try (FileInputStream is = new FileInputStream(fileName)) {
	        int len = is.read(buf);
	        if (len < buf.length) {
	            return Arrays.copyOf(buf, len);
	        }
	        ByteArrayOutputStream os = new ByteArrayOutputStream(16384);
	        while (len != -1) {
	            os.write(buf, 0, len);
	            len = is.read(buf);      
	        }
	        return os.toByteArray();
	    }
	}


//	Spotted the bug yet?
//
//	Of course, the bug is in the title of this post! We are using FileInputStream and FileOutputStream.
//
//	So what, exactly, is wrong with that?
//
//	Have you ever noticed that FileInputStream overrides finalize()? Same goes for FileOutputStream , by the way.
//
//	Every time you create either a FileInputStream or a FileOutputStream, you are creating an object. Even if you close it correctly and promptly, it will be put into a special category that only gets cleaned up when the garbage collector does a full GC. Sadly, due to backwards compatibility constraints, this is not something that can be fixed in the JDK anytime soon as there could be some code out there where somebody has extended FileInputStream / FileOutputStream and is relying on those finalize() methods to ensure the call to close().
//
//	Now, that is not an issue for short-lived programs… or for programs that do very little file I/O… but for programs that create a lot of files, it can cause issues. For example, Hadoop found “long GC pauses were devoted to process high number of final references,” resulting from the creation of lots of FileInputStream instances.
//
//	The solution (at least if you are using Java 7 or newer) is not too hard — apart from retraining your muscle memory — just switch to Files.newInputStream(...) and Files.newOutputStream(...)
//
//	Our code becomes:

	public void writeToFile1(String fileName, byte[] content) throws IOException {
	    try (OutputStream os = Files.newOutputStream(Paths.get(fileName))) {
	        os.write(content);
	    }
	}
	public byte[] readFromFile1(String fileName) throws IOException {
	    byte[] buf = new byte[8192];
	    try (InputStream is = Files.newInputStream(Paths.get(fileName))) {
	        int len = is.read(buf);
	        if (len < buf.length) {
	            return Arrays.copyOf(buf, len);
	        }
	        ByteArrayOutputStream os = new ByteArrayOutputStream(16384);
	        while (len != -1) {
	            os.write(buf, 0, len);
	            len = is.read(buf);
	        }
	        return os.toByteArray();
	    }
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
