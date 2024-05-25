package readingfile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
  private static String driver = "oracle.jdbc.driver.OracleDriver";
   private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
   private static String userName = "system";
  private static String password = "123";
   static oracle.jdbc.OracleDriver ob;

    
       static void WriteFromDBToFile(String file ){
            
        try {
            
            Connection con = DriverManager.getConnection(getUrl(), getUserName(), getPassword());
            String query= "select name,salary,phone from emp";
            PreparedStatement pr= con.prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            while(rs.next()){
                String name=rs.getString("name");
                String salary=rs.getString("salary");
                String phone=rs.getString("phone");
               String stringLine=name+","+salary+","+phone;
                writer.write(stringLine);
                writer.newLine();
            }
            
            writer.close();
            pr.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    } 
     
     
     
     static void readFromFileToDB(String file){
         try {
             Connection connection = DriverManager.getConnection(getUrl(), getUserName(), getPassword());
             String sql = "INSERT INTO emp (name, salary, phone) VALUES (?, ?, ?)";
             PreparedStatement ps = connection.prepareStatement(sql);
             BufferedReader reader = new BufferedReader(new FileReader(file));
             String line;
             while((line=reader.readLine())!=null){
                 String [] splitLine= line.split(",");
                 ps.setString(1,splitLine[0]);
                 ps.setString(2,splitLine[1]); 
                 ps.setString(3,splitLine[2]); 
                 ps.executeUpdate();      
             }
             reader.close();
             ps.close();
             connection.close();
         } catch (SQLException e) {
             e.printStackTrace(); 
         } catch (FileNotFoundException e) {
             e.printStackTrace(); 
         } catch (IOException e) {
             e.printStackTrace(); 
         }
     }
     static void createFile(){
         try {
             FileWriter fd = new FileWriter("readto.txt");
         } catch (IOException e) {
         }
     }


    public static String getDriver() {
        return driver;
    }

    public static void setDriver(String driver) {
        DbConnection.driver = driver;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        DbConnection.url = url;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        DbConnection.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        DbConnection.password = password;
    }
}
