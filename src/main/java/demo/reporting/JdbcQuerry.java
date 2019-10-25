package demo.reporting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcQuerry {
  // JDBC driver name and database URL
  static final String JDBC_DRIVER = "org.h2.Driver";
  static final String DB_URL = "jdbc:h2:mem:testdb";

  //  Database credentials
  static final String USER = "sa";
  static final String PASS = "";

  public static void executeQuerry(String sqlQuerry, String [] colLabels){
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 3: Execute a query
      System.out.println("Querying database...");
      stmt = conn.createStatement();
//      String sql =  "SELECT * FROM MANUFACTURER";
      ResultSet resultSet = stmt.executeQuery(sqlQuerry);
//      System.out.println("Created table in given database...");

      extractResult(resultSet, colLabels);


      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch(SQLException se) {
      //Handle errors for JDBC
      se.printStackTrace();
    } catch(Exception e) {
      //Handle errors for Class.forName
      e.printStackTrace();
    } finally {
      //finally block used to close resources
      try{
        if(stmt!=null) stmt.close();
      } catch(SQLException se2) {
      } // nothing we can do
      try {
        if(conn!=null) conn.close();
      } catch(SQLException se){
        se.printStackTrace();
      } //end finally try
    } //end try
    System.out.println("Goodbye!");
  }

  public static void extractResult (ResultSet resultSet, String [] colLabels) throws SQLException {
    //print labels
    printColLabels(colLabels);

    // STEP 4: Extract data from result set row by row
    while(resultSet.next()) {
      String strResultLine="";
      // Retrieve by column name  EACH QUERRY HAS ITS OWN SET OF COLUMN: ARRAY OF COLUMNS
      for(int i=0; i<colLabels.length; i++){
        String content = resultSet.getString(colLabels[i]);
        if(i == colLabels.length-1){ //last element
//          System.out.print(content+"\n");
          strResultLine+=content+"\n";
        } else{
//          System.out.print(content+","); //csv style
          strResultLine+=content+",";

        }
      }

      Writer.writeToFile(strResultLine);

    }
//    return null; //FIXME: proper return
  }

  public static void printColLabels(String[] columnLabels){
    String strLine="";
    for(int i=0; i<columnLabels.length; i++){
      if(i == columnLabels.length-1){ //last label
//        System.out.print(columnLabels[i]+"\n");
        strLine+=columnLabels[i]+"\n";
      } else{
//        System.out.print(columnLabels[i]+",");
        strLine+=columnLabels[i]+",";
      }
    }
    // print in file
    Writer.writeToFile(strLine);
  }
}
