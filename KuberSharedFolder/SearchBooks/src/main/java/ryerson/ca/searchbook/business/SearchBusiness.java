/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.searchbook.business;

import ryerson.ca.searchbook.helper.BooksXML;
import static java.lang.System.in;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import ryerson.ca.searchbook.helper.Book;
import ryerson.ca.searchbook.persistence.Book_CRUD;

/**
 *
 * @author student
 */
public class SearchBusiness {
    
    public  BooksXML getBooksByQuery(String query) throws SQLException{
       Set<Book> books = Book_CRUD.searchForBooks(query);
       Map<String ,Book> allAuthorsBooks= new HashMap ();
           System.out.println("&&&&&&&&&&&&&&&&&&&&&&"+ books.size());
        for(Book book : books){
            if(allAuthorsBooks.containsKey(book.getIsbn())){
                allAuthorsBooks.get(book.getIsbn()).addAuthor(book.getAuthors());
            }
            else{
               
                allAuthorsBooks.put(book.getIsbn(),book );
            }
        }
        System.out.println("**********************"+ allAuthorsBooks.size());
        BooksXML bs;
        bs = new BooksXML();
        bs.setBook(new ArrayList(allAuthorsBooks.values()));
        return (bs);
    }
    
      
}
