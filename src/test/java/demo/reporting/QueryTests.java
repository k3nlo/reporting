package demo.reporting;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/** 
* Query Tester. 
* 
* @author <Authors name> 
* @since <pre>Oct. 25, 2019</pre> 
* @version 1.0 
*/

public class QueryTests {
  Query query = null;

@Before
public void before() throws Exception {
  String qDescription = "Get all Manufacturers";
  String qSQL = "SELECT NAME, COUNTRY, YEAR FROM MANUFACTURER;";
  String [] qColumns = {"NAME","COUNTRY","YEAR"};
}

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getDescription() 
* 
*/ 
@Test
public void testGetDescription() throws Exception {
  assertEquals("Get all manufacturers", query.getDescription());
} 

/** 
* 
* Method: setDescription(String description) 
* 
*/ 
@Test
public void testSetDescription() throws Exception {
  query.setDescription("new_desc");
  assertEquals("new_desc", query.getDescription());
} 

/** 
* 
* Method: getSql() 
* 
*/ 
@Test
public void testGetSql() throws Exception {
  assertEquals("SELECT NAME, COUNTRY, YEAR FROM MANUFACTURER;", query.getSql());
} 

/** 
* 
* Method: setSql(String sql) 
* 
*/ 
@Test
public void testSetSql() throws Exception {
  query.setDescription("SELECT * FROM MANUFACTURER;");
  assertEquals("ELECT * FROM MANUFACTURER;", query.getSql());
} 

/** 
* 
* Method: getColumns() 
* 
*/ 
@Test
public void testGetColumns() throws Exception {
  String [] columns = {"NAME","COUNTRY","YEAR"};
  assertArrayEquals( columns, query.getColumns() );
} 

/** 
* 
* Method: setColumns(String[] columns) 
* 
*/ 
@Test
public void testSetColumns() throws Exception {
  String [] columns = {"col1","col2","col3"};
  query.setColumns(columns);
  assertArrayEquals( columns, query.getColumns() );

} 


} 
