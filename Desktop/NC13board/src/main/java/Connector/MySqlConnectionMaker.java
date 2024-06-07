package Connector;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnectionMaker implements ConnectionMaker{
    private final String URL = "jdbc:mysql://localhost:3306/board";
    private String USERNAME = "root";
    private String PASSWORD = "1234";
    private String DRIVE = "com.mysql.cj.jdbc.Driver";

    @Override
    public Connection makeConnection(){
       try{
           Class.forName(DRIVE);
       Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

       return connection;
       } catch (Exception e){
           e.printStackTrace();

           return null;
       }
    }
}
