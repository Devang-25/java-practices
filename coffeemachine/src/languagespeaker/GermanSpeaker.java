package languagespeaker;

public class GermanSpeaker extends Speaker
{
    public GermanSpeaker() { }

    public  void Speak()
    {
    	System.out.println("I speak German.");
        try {
			this.SayHello();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

   
}


