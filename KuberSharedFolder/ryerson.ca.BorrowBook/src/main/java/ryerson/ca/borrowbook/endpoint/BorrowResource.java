/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.borrowbook.endpoint;

import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import ryerson.ca.borrowbook.business.BorrowBusiness;
import ryerson.ca.borrowbook.business.BorrowXML;
 
 
import ryerson.ca.borrowbook.helper.BookBorrow;
import ryerson.ca.borrowbook.persistence.BOOK_Hold_CRUD;
import ryerson.ca.borrowbook.persistence.User_Book_Borrow_CRUD;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("borrow")
public class BorrowResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SearchResource
     */
    public BorrowResource() {

       
    }

   
    /*jist for test
    
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("getBooksHold")
    public String getXml() {
        BorrowBusiness borrow = new BorrowBusiness();
        BorrowXML books = borrow.getHolds();

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(BorrowXML.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(books, sw);

            return (sw.toString());

        } catch (JAXBException ex) {
            Logger.getLogger(BorrowResource.class.getName()).log(Level.SEVERE, null, ex);
            return ("error happened");
        }

    }

    /**
     * Retrieves representation of an instance of
     * ryerson.ca.searchbook.endpoint.SearchResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("getBooksByUser")
    public String getborrowedXml() {
        BorrowBusiness borrow = new BorrowBusiness();
        BorrowXML books = borrow.getBooksByQuery("");
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(BorrowXML.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(books, sw);

            return (sw.toString());

        } catch (JAXBException ex) {
            Logger.getLogger(BorrowResource.class.getName()).log(Level.SEVERE, null, ex);
            return ("error happened");
        }

    }

    /*
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("getBooksHold")
    public String getXml() {
        BorrowBusiness borrow = new BorrowBusiness();
        BorrowXML books = borrow.getHolds();
             JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(BorrowXML.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(books, sw);

            return (sw.toString());

        } catch (JAXBException ex) {
            Logger.getLogger(BorrowResource.class.getName()).log(Level.SEVERE, null, ex);
            return ("error happened");
        }
        
    }
    
    /**
     * Retrieves representation of an instance of
     * ryerson.ca.searchbook.endpoint.SearchResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("update")
    public String updateBookHold() {

        try {
            BOOK_Hold_CRUD.addHold("123414");
            return ("Inserted");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BorrowResource.class.getName()).log(Level.SEVERE, null, ex);
            return (ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(BorrowResource.class.getName()).log(Level.SEVERE, null, ex);
            return (ex.getMessage());
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("isBorrowed")
    public String isBorrowed() {
        BorrowBusiness borrow = new BorrowBusiness();
        BorrowXML book = borrow.getBooksByQuery("John123");

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(BookBorrow.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(book, sw);

            return (sw.toString());

        } catch (JAXBException ex) {
            Logger.getLogger(BorrowResource.class.getName()).log(Level.SEVERE, null, ex);
            return ("error happened");
        }
    }

    /**
     * PUT method for updating or creating an instance of SearchResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
