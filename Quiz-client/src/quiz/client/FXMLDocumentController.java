/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



/**
 *
 * @author pawel
 */
public class FXMLDocumentController implements Initializable {
   
    public String lineOut;
    String serverHost = "127.0.0.1"; // adres IP serwera ("cyfrowo" lub z użyciem DNS) 
    int serverPort = 50000; // numer portu na którym nasłuchuje serwer 
   
    @FXML
    private TextField nick_txt;
    @FXML
    private TextField odpowiedz_txt;
    
    @FXML Button send_odp;
    
    @FXML
    private void send_message(ActionEvent event) {
        try
            {
            Socket socket = new Socket(serverHost, serverPort); 
            OutputStream sockOut = socket.getOutputStream(); 
            PrintWriter out = new PrintWriter(sockOut, true);
            
            if(nick_txt.getText().trim()!="" && nick_txt.getText().trim().length()>0 && 
                    odpowiedz_txt.getText().trim()!="" && odpowiedz_txt.getText().trim().length()>0)
                {
                lineOut=nick_txt.getText().trim()+":"+odpowiedz_txt.getText().trim();
                odpowiedz_txt.setText("");
                out.println(lineOut);
                lineOut=null;
                }
            sockOut.close(); //zamknięcie strumienia
            socket.close(); //zamknięcie gniazada 
            }
        catch (UnknownHostException exc) {
            System.out.println("nieznany host");
        } 
        catch (SocketException exc) {
            System.out.println("wyjątki związane z komunikacją przez gniazda ");
        } 
        catch (IOException exc) {
            System.out.println("inne wyjątki we/wy");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }     
}
