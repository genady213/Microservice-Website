/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.borrowbook.helper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author student
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BookBorrow {
    
    private String isbn; 

    public BookBorrow(String isbn, String borrowDate, String username) {
        this.isbn = isbn;
        this.borrowDate = borrowDate;
        this.username = username;
    }
    public BookBorrow() {
        this.isbn = null;
        this.borrowDate = null;
        this.username = null;
    }
    private String borrowDate;
    private String username;

    public String getIsbn() {
        return isbn;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public String getUsername() {
        return username;
    }


   
    
}
