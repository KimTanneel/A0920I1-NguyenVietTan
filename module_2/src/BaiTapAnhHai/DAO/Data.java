package BaiTapAnhHai.DAO;

import java.io.*;

public class Data {
    private String path;
    private BufferedWriter reader;

    public String readFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = null;
        String rs = "";
        while((line = reader.readLine())!=null){
            rs+=line;rs+="\n";
        }
        return rs;
    }
    public void writeFile(String path,String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        System.out.println(data);
        writer.write(data);
        writer.close();
    }
}
