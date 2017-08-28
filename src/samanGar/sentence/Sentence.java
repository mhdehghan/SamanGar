package samanGar.sentence;

import java.util.LinkedList;

/**
 * Created by dehghan on 8/24/2017.
 */
public class Sentence
{
    private LinkedList<String> words=new LinkedList<>();
    private LinkedList<String> posTags=new LinkedList<>();
    private int cntWords=0;

    public Sentence(String sent)
    {
        for(String word: sent.split(" "))
        {
            words.add(word);
        }
    }

    public void add(String word)
    {
        words.add(word);
    }
    public String get(int i)
    {
        if(i>words.size())return null;

        return words.get(i);
    }
    public void replaceWord(int i,String word)
    {
        if(i>words.size())return;

        //words.remove(i);
        words.set(i,word);
    }

    public void remove(int i)
    {
        if(i>words.size())return;

        words.remove(i);
    }
    public int length()
    {
        return words.size();
    }
    public boolean existWord(String word)
    {
        if(words.contains(word))return true;
        return false;
    }

    public String toString()
    {
        String result="";
        for (int i = 0; i < words.size(); i++) {
            result+=words.get(i)+" ";
        }
        result=result.trim();
        return result;
    }

    public void setSentence(String sent)
    {
        words.clear();
        for(String word: sent.split(" "))
        {
            words.add(word);
        }
    }
    public LinkedList<String> ToLinkedListString()
    {
        return null;
    }
    public String[] ToArrayString()
    {
        return null;
    }



}
