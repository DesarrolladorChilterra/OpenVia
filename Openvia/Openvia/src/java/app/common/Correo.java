/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.common;

import java.util.ArrayList;
import java.util.List;
//import javax.mail.*;
//import javax.mail.internet.*;

/**
 *
 * @author Miguelón
 */
public class Correo {
    
    public static void send(String smtpHost, int smtpPort, String from, String to, String subject, String content) {
//        try {
//
//            // Create a mail session
//            java.util.Properties props = new java.util.Properties();
//            props.put("mail.smtp.host", smtpHost);
//            props.put("mail.smtp.port", "" + smtpPort);
//            Session session = Session.getDefaultInstance(props, null);
//
//            // Construct the message
//            Message msg = new MimeMessage(session);
//            msg.setFrom(new InternetAddress(from));
//            msg.setRecipients(Message.RecipientType.TO, getTo(to));
//            msg.setSubject(subject);
//            msg.setText(content);
//
//            // Send the message
//            Transport.send(msg);
//        } catch (MessagingException ex) {
//            ex.printStackTrace();
//        }
    }
    
//    private static InternetAddress[] getTo(String listTo) {
//        InternetAddress[] aTo = new InternetAddress[]{};
//        List a = new ArrayList();
//        String[] s = listTo.split(",");
//        for (int i=0 ; i < s.length; i++) {
//            try {
//                a.add(new InternetAddress(s[i].trim()));
//            } catch (AddressException ex) {
//                ex.printStackTrace();
//            }
//        }
//        aTo = (InternetAddress[]) a.toArray( aTo);
//        return aTo;
//    }
            
    public static void main(String[] args) throws Exception {
            // Send a test message
            send("correo.comten.cl", 25, "soporte@copeval.cl", "mvega@comten.cl",
                 "Prueba correo", "Este correo fúé enviado CON Conexion a VPN copeval");
//            send("smtp.copeval.cl", 25, "info@copeval.cl", "mvega@comten.cl, mvegabrante@gmail.com",
//                 "Prueba correo", "Este correo fúé enviado CON Conexion a VPN copeval");
    }

}
