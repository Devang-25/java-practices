package inputoutput;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class InputOutputStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputOutputStream iostream= new InputOutputStream();
		iostream.getStreamBytes();
		//iostream.getStreamLines();

	}
	
	public void getStreamBytes() {
		try {
			FileOutputStream outst= new FileOutputStream("geek.txt");
			ObjectOutputStream objOutSt= new ObjectOutputStream(outst);
			objOutSt.writeUTF("GeeksForGeeks");
			objOutSt.flush();
			
			FileInputStream inst = new FileInputStream("geek.txt");
			ObjectInputStream objinst = new ObjectInputStream(inst);
			

	        byte[] buffer = new byte[25]; 
	  
	        // Use of read(byte[] buffer, int offset, int maxlen) 
	        objinst.read(buffer, 2,20); 
	  
	        System.out.print("Use of read(buffer, offset, maxlen) : "); 
	        for (int i = 0; i < 20; i++) 
	        { 
	            System.out.print((char)buffer[i]); 
	        }
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}


	public void getStreamLines() {
		try {
			FileOutputStream outst= new FileOutputStream("geek.txt");
			ObjectOutputStream objOutSt= new ObjectOutputStream(outst);
			objOutSt.writeUTF("hello");
			objOutSt.writeUTF("hi");
			objOutSt.writeUTF("hi");
			objOutSt.flush();
			
			FileInputStream inst = new FileInputStream("geek.txt");
			ObjectInputStream objinst = new ObjectInputStream(inst);
			for(int i=0; i< objinst.available(); i++) {
				System.out.println(objinst.readUTF());
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}


}
