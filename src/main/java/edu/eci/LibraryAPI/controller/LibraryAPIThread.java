package edu.eci.LibraryAPI.controller;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author carloscl
 */
public class LibraryAPIThread extends Thread {
    
    private int idL;
    private String nombreLibro;
    private String email;

    public LibraryAPIThread(int idL, String nombreLibro, String email) {
        this.idL = idL;
        this.nombreLibro = nombreLibro;
        this.email = email;
    }

    @Override
    public void run() {
        try {
            //Debe esperar 1 minuto a que termine el proceso.
            Thread.sleep(60000);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        //Se establece una nueva sesion.
        Properties properties = System.getProperties();
        //Se establecen las propiedades necesarias para enviar correos outlook 365.
        String emailFrom = "carlos.medina-ri@mail.escuelaing.edu.co";
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "m.outlook.com");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailFrom, "MozaicoDeOpini141998");
            }
        });
        //Creo el mensaje
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(emailFrom));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setHeader("Notificacion", "Finalizacion creacion libro");
            message.setSubject("Notificación: Creacion de libro finalizada.");
            message.setText("Buen dia.\n\nEl libro '"+nombreLibro+"' se ha añadido correctamente a la libreria '"+idL+"'.\n\nLibraryAPI\nCarlos Andrés Medina Rivas");
            //Enviar el correo apenas el thread termine el proceso.
            Transport.send(message);
        } catch (AddressException ex) {
            System.out.println(ex.getMessage());
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
