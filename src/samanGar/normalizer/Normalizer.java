package samanGar.normalizer;

import samanGar.ReadData;
import samanGar.sentence.Sentence;
import samanGar.sentence.Word;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dehghan on 8/24/2017.
 */
public class Normalizer {
    Map<Character, Character> charSet;
    List<Character> validCharSet;

    public Normalizer() {
        charSet=new ReadData().readMap("resources/normalizer/replaceChars.txt");
        validCharSet=new ReadData().readChars("resources/normalizer/validChars.txt");
    }

    public Normalizer(String charSetFile,String validCharFile) {
        charSet = new ReadData().readMap(charSetFile);
        validCharSet=new ReadData().readChars(validCharFile);
    }







    private void replaceCharSet(Sentence sentence) {
        for (int i = 0; i < sentence.length(); i++) {
            StringBuilder result = new StringBuilder();
            String tmp = sentence.get(i);
            for (int j = 0; j < tmp.length(); j++) {
                if (charSet.containsKey(tmp.charAt(j))) {
                    result.append(charSet.get(tmp.charAt(j)));
                } else {
                    result.append(tmp.charAt(j));
                }
            }
            sentence.replaceWord(i, result.toString());
        }

    }

    private void invalidChar(Sentence sentence) {
        for (int i = 0; i < sentence.length(); i++) {
            StringBuilder result = new StringBuilder();
            String tmp = sentence.get(i);
            for (int j = 0; j < tmp.length(); j++) {
                if (validCharSet.contains(tmp.charAt(j))) {
                    result.append(tmp.charAt(j));
                } else {
                    //Do nothing!
                }
            }
            sentence.replaceWord(i, result.toString());
        }
    }

    private void removeEmptyWords(Sentence sentence)
    {
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.get(i).trim().equals(""))
            {sentence.remove(i);i--;}
        }
    }

    public void process(Sentence sentence) {
        replaceCharSet(sentence);
        invalidChar(sentence);
        //pullRegex(sentence);
        //splitPunctuation(sentence);

        //Add semi-space here!
        //new SemiSpace().process(sentence);

        removeEmptyWords(sentence);
        //pushRegex(sentence);
    }
}
