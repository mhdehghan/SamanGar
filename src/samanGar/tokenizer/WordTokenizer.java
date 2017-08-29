package samanGar.tokenizer;

import samanGar.ReadData;
import samanGar.normalizer.Normalizer;
import samanGar.sentence.Sentence;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dehghan on 8/29/2017.
 */
public class WordTokenizer
{
    List<String> regex;
    List<String> puncs;
    public WordTokenizer()
    {
        regex=new ReadData().readConfigFile("resources/normalizer/topRegex.txt");
        puncs=new ReadData().readConfigFile("resources/normalizer/punctuations.txt");
    }

    public WordTokenizer(String punctuationsFile,String topRegexFile)
    {
        puncs = new ReadData().readConfigFile(punctuationsFile);
        regex = new ReadData().readConfigFile(topRegexFile);
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

    private void removeEmptyWords(Sentence sentence)
    {
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.get(i).trim().equals(""))
            {sentence.remove(i);i--;}
        }
    }


    HashMap<String,String> tmpRegex=new HashMap<>();
    private void pullRegex(Sentence sentence)
    {
        tmpRegex.clear();
        for (int i = 0; i <sentence.length() ; i++) {
            String token=sentence.get(i);
            for (int j = 0; j < regex.size(); j++) {
                String reg=regex.get(j);
                while(Pattern.compile(reg).matcher(token).matches())
                {
                    String key="REGEX"+String.format("%04d",tmpRegex.size());
                    Pattern pattern=Pattern.compile(reg);
                    Matcher matcher=pattern.matcher(token);
                    matcher.matches();
                    String value= matcher.group(0);
                    token=token.replaceFirst(value,key);
                    tmpRegex.put(key,value);
                }
            }
            if(!token.equals(sentence.get(i)))//Update sentence.get(i)
            {
                sentence.replaceWord(i,token);
            }
        }
    }

    private void pushRegex(Sentence sentence)
    {
        String text=sentence.toString();

        for (String key: tmpRegex.keySet()) {
            text=text.replace(key,tmpRegex.get(key));
        }
        sentence.setSentence(text);
    }

    public void process(Sentence sentence)
    {
        pullRegex(sentence);
        splitPunctuation(sentence);
        removeEmptyWords(sentence);
        pushRegex(sentence);
    }
}
