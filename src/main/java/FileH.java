import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileH {

    //creates and writes to a file
    private static File fileCreator() throws IOException
    {
        //creates a new file if the file does not exist
        File f = new File("/Users/ankur/Documents/GitHub/fileHandling/src/main/java/testFile.txt");
        if(f.createNewFile())
            System.out.println("File Created");
        else System.out.println("File Exists");

        //writes to the file then closes it
        FileWriter w = new FileWriter(f);
        w.write("qwerty uiop lkjhg gfdsa zxcv bnm");
        w.write("\n\nReading All Bytes or Lines from a File\n" +
                "If you have a small-ish file and you would like to read its entire contents in one pass, \nyou can use the readAllBytes(Path) or readAllLines(Path, Charset) method. \nThese methods take care of most of the work for you, such as opening and closing the stream, \nbut are not intended for handling large files. \nThe following code shows how to use the readAllBytes method:");

        w.write("\n\nPath file = ...;\n" +
                "byte[] fileArray;\n" +
                "fileArray = Files.readAllBytes(file);");

        w.close();
        return f;
    }

    private static List<String> fileReader(File f)
    {
        //the arraylist stores all the lines in the file
        List<String> fileLines = new ArrayList<String>();
        //this block populates the arraylist
        try
        {
            fileLines = Files.readAllLines(Paths.get(f.getAbsolutePath()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return fileLines;
    }

    public static void main(String[] args) throws IOException
    {
        //creates and writes to a file
        List<String> contents = fileReader(fileCreator());
        //this iterator loops through the contents of the file and then prints it to the console
        Iterator<String> i = contents.iterator();
        while (i.hasNext())
            System.out.println(i.next());

    }
}
