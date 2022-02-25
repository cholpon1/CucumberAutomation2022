package utilities;

import io.cucumber.java.it.Ma;

import java.sql.*;
import java.util.*;

public class JDBC {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:postgresql://localhost/HR"; //Path and /name -> in this case HR is the name
        String username = "postgres";
        String password = "Admin";
        String query = "SELECT * FROM employees LIMIT 5"; // Query must be as detailed as possible

        Connection connection;//road
        Statement statement;//vehicle
        ResultSet resultSet;//data that we get from Database
        ResultSetMetaData resultSetMetaData; // data about data

        connection= DriverManager.getConnection(url,username,password);
        statement=connection.createStatement();
        resultSet=statement.executeQuery(query);
        resultSetMetaData=resultSet.getMetaData();


        System.out.println(resultSetMetaData.getColumnName(1));// Metadata is INFO about data
        System.out.println(resultSetMetaData.getColumnCount());
        System.out.println(resultSetMetaData.getTableName(1));

       // resultSet.next(); // -> Output

//        System.out.println(resultSet.getString(3));
//
//        System.out.println(resultSet.getString("salary"));


       // resultSet.next();

//        System.out.println(resultSet.getString(3));
//
//        System.out.println(resultSet.getString("salary"));


        while(resultSet.next()){

            System.out.print(resultSet.getString(1)+" ");

            System.out.print(resultSet.getString(2)+" ");

            System.out.print(resultSet.getString("salary")+" ");

            System.out.print(resultSet.getString("manager_id")+" ");

            System.out.println("");

        }


//        try { -- Try to figure out why try catch is not working here but method signature is required
//            connection= DriverManager.getConnection(url,username,password);
//        } catch (SQLException e) {
//            System.out.println("Check URL, username or password");
//        }


        ResultSet resultSet1=statement.executeQuery(query);
        List<Map<String,Object>> data=new ArrayList<>();

        while (resultSet1.next()){
            Map<String, Object> row =new HashMap<>();

            for(int i=1; i<=resultSetMetaData.getColumnCount(); i++){
                row.put(resultSetMetaData.getColumnName(i),resultSet1.getString(i));
            }
            data.add(row);
        }

        System.out.println(data.get(0));

        System.out.println(data.get(0).get("last_name"));

        System.out.println(data.get(1).get("first_name"));

       // System.out.println(data.get(10).get("email")); // out of bounds because we don't have any index after 4

        connection.close();

        System.out.println(resultSet.getString(1));


//       Below is to try
//        List<Map<String,Object>> data= new ArrayList<>();

//        for(int i=1; i<=resultSetMetaData.getColumnCount(); i++){
//            data.get(i).put(resultSetMetaData.getColumnName(i),resultSet.getString(i));
//        }



    }
}
