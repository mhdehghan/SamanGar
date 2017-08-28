package samanGar;

import java.io.*;
import java.util.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by dehghan on 8/25/2017.
 */
public class ReadData
{

    public List<Character> readChars(String inputFileAddress)
    {

        Iterator<String> itr=readConfigFile(inputFileAddress).iterator();
        List<Character> charResult=new LinkedList<Character>();

        int cntCharResult=0;
        while(itr.hasNext()){
            charResult.add(itr.next().toString().charAt(0));
        }

        return charResult;
    }

    public Map<Character,Character> readMap(String inputFileAddress) {

        Iterator<String> itr = readConfigFile(inputFileAddress).iterator();
        Map<Character, Character> charResult = new HashMap<Character, Character>();
        int cntCharResult = 0;
        while (itr.hasNext()) {
            String tmpItr = itr.next().toString();
            charResult.put(tmpItr.split("\t")[0].charAt(0), tmpItr.split("\t")[1].charAt(0));
        }
        return charResult;
    }

    public List<String> readConfigFile(String FILENAME)
    {
        return readFile(FILENAME,true);
    }
    public List<String> readFile(String FILENAME)
    {
        return readFile(FILENAME,false);
    }

    private List<String> readFile(String FILENAME,boolean ignoreHashTagEmptyLine)
    {
        List<String> result=new LinkedList<String>();

        BufferedReader br = null;
        FileReader fr = null;

        try {

            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                if(ignoreHashTagEmptyLine && sCurrentLine.trim().startsWith("#"))continue;
                if(ignoreHashTagEmptyLine && sCurrentLine.equals(""))continue;
                if(ignoreHashTagEmptyLine && sCurrentLine.trim().startsWith("\\#"))sCurrentLine=sCurrentLine.replaceFirst("\\#","#");
                result.add(sCurrentLine);
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
        return result;
    }

    public String readCompressedFile(String fileName) {
        try {
            GZIPInputStream gis = new GZIPInputStream(new FileInputStream(fileName));
            ByteArrayOutputStream fos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            gis.close();
            return new String(fos.toByteArray());
        } catch (IOException ex) {
            System.out.println("Invalid input file!");
            return null;
        }
    }

    public void writeCompressedFile(String fileName, String value) {
        try {
            InputStream is = new ByteArrayInputStream(value.getBytes());
            GZIPOutputStream gzipOS = new GZIPOutputStream(new FileOutputStream(fileName));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                gzipOS.write(buffer, 0, len);
            }
            gzipOS.close();
            is.close();
        } catch (IOException ex) {
            System.out.println("Writing failed!");
        }
    }

}

