import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

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

    private static void appendFile(File f, String add)
    {
        //prev holds the value of the old file
        String prev = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try
        {
            //keeps appending contents of old file
            reader = new BufferedReader(new FileReader(f));
            String l = reader.readLine();
            while(l != null)
            {
                prev = prev + l + System.lineSeparator();
                l = reader.readLine();
            }
            //adds the new contents to the file
            prev = prev + add + System.lineSeparator();
            writer = new FileWriter(f);
            writer.write(prev);
        }
        //exception handlers
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //close reader/writers
            try
            {
                reader.close();
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
        //creates and writes to a file
        File cFile = fileCreator();
        List<String> contents = fileReader(cFile);

        //this iterator loops through the contents of the file and then prints it to the console
        Iterator<String> i = contents.iterator();
        while (i.hasNext())
            System.out.println(i.next());

        //asks user for input and stores it in userAppend
        Scanner in = new Scanner(System.in);
        System.out.println("\nWhat would you like to append to the file?");
        String userAppend = in.nextLine();

        //adds the user input to the file
        appendFile(cFile,"\nAdding extra stuff to the file: \n" + userAppend);

        //the reader loops through the updated file and prints it
        contents = fileReader(cFile);
        i = contents.iterator();
        while (i.hasNext())
            System.out.println(i.next());
    }
}
