/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.holdbook.helper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
 @XmlRootElement(name = "book")
 @XmlAccessorType(XmlAccessType.FIELD)
public class BookHold {
    
    private String isbn; 

    public BookHold(String isbn, String holdDate, String username) {
        this.isbn = isbn;
        this.holdDate = holdDate;
        this.username = username;
    }
  
    public BookHold() {
        this.isbn = "";
        this.holdDate = "";
        this.username = "";
    }
    private String holdDate;
    private String username;

    public String getIsbn() {
        return isbn;
    }

    public String getHoldDate() {
        return holdDate;
    }

    public String getUsername() {
        return username;
    }


   
    
}
