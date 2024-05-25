package readingfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        String path1 ="readfrom.txt";
        String path2 ="readto.txt";
     
        
        DbConnection rf = new DbConnection();
        //rf.readFromFileToDB(path2);
        File f = new File(path2);
        //f.delete();
        rf.createFile();
        rf.WriteFromDBToFile(path2);
       

    }
}
