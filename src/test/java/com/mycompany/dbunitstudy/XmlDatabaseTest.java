/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dbunitstudy;

import java.io.File;
import javax.sql.DataSource;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dickdijk
 */
public class XmlDatabaseTest {
    
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";//org.h2.Driver.class.getName();
  private static final String JDBC_URL = "jdbc:derby://localhost:1527/pearl1";
  private static final String USER = "dick";
  private static final String PASSWORD = "dick";

  
    public XmlDatabaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

   
    
    @Before
  public void importDataSet() throws Exception {
      IDataSet dataSet = readDataSet();
      cleanlyInsert(dataSet);
  }

  private IDataSet readDataSet() throws Exception {
      return new FlatXmlDataSetBuilder().build(new File("src/main/resources/dataset.xml"));
  }

  private void cleanlyInsert(IDataSet dataSet) throws Exception {
      IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
      databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
      databaseTester.setDataSet(dataSet);
      databaseTester.onSetup();
  }

  @Test
  public void bogusTest() {
    
      Assert.assertEquals("Dit is een onzin tests", true, false);
}
//  @Test
//  public void findsAndReadsExistingPersonByFirstName() throws Exception {
//      PersonRepository repository = new PersonRepository(dataSource());
//      Person charlie = repository.findPersonByFirstName("Charlie");
//
//      assertThat(charlie.getFirstName(), is("Charlie"));
//      assertThat(charlie.getLastName(), is("Brown"));
//      assertThat(charlie.getAge(), is(42));
//  }
//
//  @Test
//  public void returnsNullWhenPersonCannotBeFoundByFirstName() throws Exception {
//      PersonRepository repository = new PersonRepository(dataSource());
//      Person person = repository.findPersonByFirstName("iDoNotExist");
//
//      assertThat(person, is(nullValue()));
//  }
//
//  private DataSource dataSource() {
//      JdbcDataSource dataSource = new JdbcDataSource();
//      dataSource.setURL(JDBC_URL);
//      dataSource.setUser(USER);
//      dataSource.setPassword(PASSWORD);
//      return dataSource;
//  }
    
}
