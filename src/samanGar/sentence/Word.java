package samanGar.sentence;

/**
 * Created by dehghan on 8/24/2017.
 */
public class Word
{
    private String content="";
    public void set(String word)
    {
        content=word;
    }
    public String get(){return content;}

    public String ToString()
    {
        return content.toString();
    }
    public char[] ToChar()
    {
        return content.toCharArray();
    }
    public Character[] ToCharacter()
    {
        char[] tmp=content.toCharArray();
        Character[] characters=new Character[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            characters[i]=tmp[i];
        }
        return characters;
    }
    public void concat(String Str)
    {
        content.concat(Str);
    }
    public int length()
    {
        return content.length();
    }

}
