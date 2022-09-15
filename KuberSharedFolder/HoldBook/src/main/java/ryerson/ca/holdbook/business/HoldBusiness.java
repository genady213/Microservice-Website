/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.holdbook.business;


import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import ryerson.ca.holdbook.helper.BookHold;
import ryerson.ca.holdbook.persistence.BOOK_Hold_CRUD;


public class HoldBusiness {
         

    public BookHold getBooks(String isbn) {
        BookHold bs = BOOK_Hold_CRUD.getHoldBook(isbn);

        return (bs);
    }

    public boolean hold(String isbn, String userid) throws ClassNotFoundException, SQLException, ServerAddressNotSuppliedException, IOException, InterruptedException {
        boolean success = false;
        
            success = BOOK_Hold_CRUD.addHold(isbn, userid);
            if(success){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
             LocalDate date = LocalDate.now();
        
            LocalDate exDate = date.plusDays(14);

            Messaging.sendmessage("HOLD:"+isbn+":"+userid+":"+exDate.format(formatter));
            }
            
        
        return success;
    }

 
}
