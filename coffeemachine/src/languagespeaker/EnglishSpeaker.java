package languagespeaker;

public class EnglishSpeaker extends Speaker
{
    public EnglishSpeaker() { }


    public  void Speak()
    {
        try {
			this.SayHello();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("I speak English.");
    }

  
}

