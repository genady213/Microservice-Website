/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.borrowbook.business;


import static java.lang.System.in;
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
import org.apache.commons.codec.binary.Base64;
import ryerson.ca.borrowbook.helper.BookBorrow;
import ryerson.ca.borrowbook.persistence.BOOK_Hold_CRUD;

import ryerson.ca.borrowbook.persistence.User_Book_Borrow_CRUD;

/**
 *
 * @author student
 */
public class BorrowBusiness {

    public  BorrowXML getBooksByQuery(String username){
       Set<BookBorrow> books = User_Book_Borrow_CRUD.getBorrowedBooks(username);
       
       
        BorrowXML bs;
        bs = new BorrowXML();
        bs.setBook(new ArrayList(books));
        return (bs);
    }
    
    
    
    public  BorrowXML getHolds(){
        Set<BookBorrow> holds = BOOK_Hold_CRUD.getHolds();
       
       BorrowXML bs;
        bs = new BorrowXML();
        bs.setBook(new ArrayList(holds));
        return (bs);
    }
  
}
