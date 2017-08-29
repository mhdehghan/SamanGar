package samanGar;

import samanGar.normalizer.Normalizer;
import samanGar.posTagger.POSTagger;
import samanGar.sentence.Sentence;
import samanGar.tokenizer.WordTokenizer;

public class Processor {

    public static void main(String[] args) {
        // write your code here



        Normalizer normalizer = new Normalizer();
        WordTokenizer wordTokenizer=new WordTokenizer();
        //POSTagger posTagger=new POSTagger();

        String inSentence = "";
        while (true) {
            System.out.println("Enter your sentence: ");
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            inSentence = scanner.nextLine();
            Sentence sentence=new Sentence(inSentence);

            if (inSentence.length() == 0) break;
            normalizer.process(sentence);
            System.out.println("Normalizer: Your sentence is: " + sentence.toString());

            wordTokenizer.process(sentence);
            System.out.println("WordTokenizer: Your sentence is: " + sentence.toString());

            //posTagger.process(sentence);
            //System.out.println("POSTagger: Your sentence is: " + sentence.toString());

        }
    }
}
