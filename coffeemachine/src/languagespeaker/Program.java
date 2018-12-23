package languagespeaker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Program {

	public static void main(String args[]) {
		// This is your client code.
		ISpeaker speaker = null;
		try {
			speaker = SpeakerFactory.CreateSpeaker(Language.Spanish);
		} catch (ApplicationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		speaker.Speak();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Enter line");
			String line = br.readLine();

			if (line != null) {
				System.out.println(line);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
