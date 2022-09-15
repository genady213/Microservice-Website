/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.searchbook.helper;

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
public class Book {
    
    private String isbn; 
    private String title;
    private String barcode;
    ArrayList<Author> author ;

    public Book(){
        
    }
    public Book(String isbn, String title, String barcode, Author author) {
        this.isbn = isbn;
        this.title = title;
        this.barcode = barcode;
        this.author= new ArrayList<Author>();
        this.author.add(new Author(author.getFirstName(), author.getLastName()));
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getBarcode() {
        return barcode;
    }
    public ArrayList<Author> getAuthors() {
        return author;
    }
     public void addAuthor(ArrayList<Author> authors) {
         for(Author a:authors){
        this.author.add(new Author(a.getFirstName(),a.getLastName()));
         }
    }
    /*@Override
    public int hashCode() {
        return (Objects.hash(isbn));
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Book other = (Book) obj;
        if (isbn.equals(other.isbn))
            return true;
        else return (false);
        
    }*/
    
}
