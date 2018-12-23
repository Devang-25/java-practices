package languagespeaker;







public abstract class Speaker implements ISpeaker
{


    public abstract void Speak();

    public void SayHello() throws ApplicationException
    {
        System.out.println("Hello world.");
    }

}



