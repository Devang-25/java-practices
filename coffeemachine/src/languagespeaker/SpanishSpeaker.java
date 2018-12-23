package languagespeaker;

public class SpanishSpeaker extends Speaker
{
    public SpanishSpeaker() { }


    public  void Speak()
    {
    	System.out.println("I speak Spanish.");
    }

    public  void SayHello() throws ApplicationException
    {
        throw new ApplicationException("I cannot say Hello World.");
    }

}