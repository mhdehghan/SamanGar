package samanGar.normalizer;

import samanGar.ReadData;
import samanGar.sentence.Sentence;
import samanGar.sentence.Word;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dehghan on 8/24/2017.
 */
public class Normalizer {
    Map<Character, Character> charSet;
    List<Character> validCharSet;
    List<String> puncs;

    public Normalizer() {
        charSet=new ReadData().readMap("resources/Normalizer/replaceChars.txt");
        validCharSet=new ReadData().readChars("resources/Normalizer/validChars.txt");
        puncs=new ReadData().readFile("resources/Normalizer/punctuations.txt");
    }

    public Normalizer(String charSetFile,String validCharFile,String punctuationsFile) {
        charSet = new ReadData().readMap(charSetFile);
        validCharSet=new ReadData().readChars(validCharFile);
        puncs=new ReadData().readFile(punctuationsFile);
    }

    public void process(Sentence sentence) {
        replaceCharSet(sentence);
        invalidChar(sentence);
        splitPunctuation(sentence);
        removeEmptyWords(sentence);
    }

    private void splitPunctuation(Sentence sentence){
        String text=sentence.toString();
        StringBuilder out = new StringBuilder();
        for (String word: text.split(" ")) {
            if ((word.length() > 1) && word.replaceAll("[0-9a-zA-Zا-ی]", "").length() > 0) {
                for (int i = 0; i < word.length(); i++) {
                    if (puncs.contains(word.substring(i, i + 1))) {
                        if (i != 0) {
                            out.append(" ");
                        }
                        out.append(word.substring(i, i + 1));
                        if (i != word.length() - 1) {
                            out.append(" ");
                        }
                    } else {
                        out.append(word.substring(i, i + 1));
                    }
                }
                out.append(" ");
            } else {
                out.append(word + " ");
            }
        }
        sentence.setSentence(out.toString().trim());
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
}
