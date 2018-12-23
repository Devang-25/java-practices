package languagespeaker;

public class SpeakerFactory
{
    public static ISpeaker CreateSpeaker(Language language) throws ApplicationException
    {
        switch (language)
        {
            case English:
                return new EnglishSpeaker();
            case German:
                return new GermanSpeaker();
            case Spanish:
                return new SpanishSpeaker();
            default:
                throw new ApplicationException("No speaker can speak such language");
        }
    }
}