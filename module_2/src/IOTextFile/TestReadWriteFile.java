package IOTextFile;

import java.io.*;

public class TestReadWriteFile {
    public static void main(String[] args)  {
        try{
            FileReader fileReader = new FileReader("./src/IOTextFile/filetest.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            String line =null;
            BufferedWriter writer = new BufferedWriter(new FileWriter("./src/IOTextFile/fileOutput.txt"));

            while ((line = reader.readLine())!=null){
                System.out.println(line);
                writer.write(line);
            }
            writer.close();
            reader.close();
        }
       catch (IOException e){
           System.err.println("Không mở được file");
       }

    }
}