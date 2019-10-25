package demo.reporting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryExecutor {

  // JDBC driver name and database URL
  static final String JDBC_DRIVER = "org.h2.Driver";
  static final String DB_URL = "jdbc:h2:mem:testdb";

  //  Database credentials
  static final String USER = "sa";
  static final String PASS = "";

  private Writer writer;
  private Query query;
  private ResultSet resultSet;

  //ctor
  public QueryExecutor(Writer writer, Query query) {
    this.writer = writer;
    this.query = query;
  }

  public void executeQuery(){
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      System.out.print("Connecting to database... ");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 3: Execute a query
      System.out.print("Querying database... ");
      stmt = conn.createStatement();
      resultSet = stmt.executeQuery(query.getSql());

      // STEP 4: Extract information
      processResults();

      // STEP 5: Clean-up environment
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
    System.out.println("Query completed!");
  }

  public void processResults() throws SQLException {
    //print labels
    writeColumnLabels();

    // STEP 4: Extract data from result set row by row
    while(resultSet.next()) {
      String strResultLine="";
      // Retrieve by column name  EACH QUERY HAS ITS OWN SET OF COLUMN: ARRAY OF COLUMNS
      for(int i=0; i<query.getColumns().length; i++){
        String content = resultSet.getString(query.getColumns()[i]);
        if(i == query.getColumns().length-1){ //last element
//          System.out.print(content+"\n");
          strResultLine+=content+"\n";
        } else{
//          System.out.print(content+","); //csv style
          strResultLine+=content+",";

        }
      }
      writer.writeToFile(strResultLine);
    }
  }

  public void writeColumnLabels(){
    String strLine="";
    for(int i=0; i<query.getColumns().length; i++){
      if(i == query.getColumns().length-1){ //last label
//        System.out.print(columnLabels[i]+"\n");
        strLine+=query.getColumns()[i]+"\n";
      } else{
//        System.out.print(columnLabels[i]+",");
        strLine+=query.getColumns()[i]+",";
      }
    }
    // print in file
    writer.writeToFile(strLine);
  }
}
