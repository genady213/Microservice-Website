/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.borrowbook.business;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import ryerson.ca.borrowbook.helper.BookBorrow;


 @XmlRootElement
       public class BorrowXML{
           private ArrayList<BookBorrow> books;
           @XmlElementWrapper
           @XmlElement(name="bookBorrowed")
           public List<BookBorrow>getBooks(){
               return books;
               
           }
           BorrowXML(){
               
               
           }
           public void setBook(ArrayList<BookBorrow> bs){
               books=bs;
               
           }
           
       }