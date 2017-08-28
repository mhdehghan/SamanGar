package samanGar;

import samanGar.normalizer.Normalizer;
import samanGar.sentence.Sentence;

public class Processor {

    public static void main(String[] args) {
        // write your code here

        //ReadData readData=new ReadData();
        //readData.writeCompressedFile("test.txt","این یک متن تست است.");
        //System.out.println(readData.readCompressedFile("test.txt"));
        //if(true)return;

        Normalizer normalizer = new Normalizer();

        String inSentence = "";
        while (true) {
            System.out.println("Enter your sentence: ");
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            inSentence = scanner.nextLine();
            Sentence sentence=new Sentence(inSentence);

            if (inSentence.length() == 0) break;
            normalizer.process(sentence);

            System.out.println("Your sentence is: " + sentence.toString());

        }
    }
}
