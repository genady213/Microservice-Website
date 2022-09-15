/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.borrowbook.persistence;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import ryerson.ca.borrowbook.helper.BookBorrow;

/**
 *
 * @author student
 */
public class User_Book_Borrow_CRUD {
    
    public static Connection getCon() throws ClassNotFoundException{
       Connection con=null;
     try{
         Class.forName("com.mysql.jdbc.Driver");
        String connection=System.getenv("DB_URL");
        //String connection ="localhost:3306";
         con=DriverManager.getConnection("jdbc:mysql://"+connection+"/borrow_LBS?allowPublicKeyRetrieval=true&useSSL=false", "root", "student" );
        
         
         System.out.println("Connection established.");
     }
     catch(Exception e){ System.out.println(e);}
     return con;
     
    }
    
    public static Set<BookBorrow> getBorrowedBooks(String username){
        Set<BookBorrow> books= new HashSet<BookBorrow>();
        try{
            Connection con= getCon();
            String q;
            if(username.isEmpty()){
               q="select * from USER_BOOK_Borrow "
                 +";";
            }
            else
             q = "select * from USER_BOOK_Borrow "
                    + " WHERE username LIKE '"+username+"'"+";";

			PreparedStatement ps=con.prepareStatement(q);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){

				String isbn=rs.getString("isbn");
			
                                String date=rs.getDate("borrowDate").toString();
                                
                                
                                BookBorrow book = new BookBorrow(isbn,date, username);
                                books.add(book);
                                
                                }
			
			con.close();

		}catch(Exception e){System.out.println(e);}
           
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+books.size());
        return books;
        
    }
    public static BookBorrow getBorrowedBooks(String username, String isbn){
        BookBorrow book=null;
        try{
            Connection con= getCon();
            
            String q = "select * from USER_BOOK_Borrow "
                    + " WHERE username LIKE '"+username+"'"+" and "
                    + "isbn LIKE '"+isbn+"';";

			PreparedStatement ps=con.prepareStatement(q);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){

				
				
                                String date=rs.getDate("borrowDate").toString();
                                
                                
                                 book = new BookBorrow(isbn,date,username);
                                
                                
                                }
			
			con.close();

		}catch(Exception e){System.out.println(e);}
            
    
        return book;
        
    }
    
    
    public static boolean borrow(String username, String isbn){
      
        try{
            Connection con= getCon();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
             LocalDate date = LocalDate.now();
            System.out.println(date.format(formatter));
            String q = "insert into USER_BOOK_Borrow "
                    + "(isbn, username, borrowDate) values "
                    + "('"+isbn+"', '"+username+"', '"+date.format(formatter)+"');";
            Statement stmt = con.createStatement(); 
            stmt.execute(q);
            
			con.close();
                        return true;

		}catch(Exception e){System.out.println(e);
                return false;
                }
 
        
    }
}
