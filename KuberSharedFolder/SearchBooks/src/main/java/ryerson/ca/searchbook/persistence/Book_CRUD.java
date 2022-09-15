/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.searchbook.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import ryerson.ca.searchbook.helper.Author;
import ryerson.ca.searchbook.helper.Book;

/**
 *
 * @author student
 */
public class Book_CRUD {

    private static Connection getCon() {
        Connection con = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            String connection = System.getenv("DB_URL");
            //  String connection ="localhost:3306";
            con = DriverManager.getConnection("jdbc:mysql://" + connection
                    + "/search_LBS?allowPublicKeyRetrieval=true&useSSL=false", "root", "student");

            System.out.println("Connection established.");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static Set<Book> searchForBooks(String query) throws SQLException {
        Set<Book> books = new HashSet<Book>();
        
            Connection con = getCon();

            String q = "select * from BOOK NATURAL JOIN BOOK_AUTHOR "
                    + "NATURAL JOIN AUTHOR WHERE title LIKE '%" + query + "%'"
                    + " OR firstName LIKE '%"
                    + query + "%' OR lastName LIKE '%"
                    + query + "%';";
            System.out.println(q);
            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //been= new UserInfo();
                String isbn = rs.getString("isbn");
                String title = rs.getString("title");
                String barcode = rs.getString("barcode");
                String firstname = rs.getString("firstName");
                String lastname = rs.getString("lastName");
                Author author = new Author(firstname, lastname);

                Book book = new Book(isbn, title, barcode, author);
                books.add(book);

            }

            con.close();

         

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>" + books.size());
        return books;

    }
    
    
        public static boolean addBook(String isbn, String barcode, String title) throws ClassNotFoundException, SQLException{
      
       
           Connection con= getCon();
    
            String q = "insert into BOOK "
                    + "(isbn, title, barcode) values "
                    + "('"+isbn+"', "
                    +"'"+title+"', "
                    +"'"+barcode+"');";
            Statement stmt = con.createStatement();   
            System.out.println(q);
            stmt.execute(q);
			con.close();
                        return true;

		
 
        
    }
}
