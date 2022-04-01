/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestonesocket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author mauri
 */
public class Server {
    
    int portaServer = 2000;
    ServerSocket serverSocket;
    Socket socket;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    String messaggioRicevuto;
    String messaggioDaInviare;
    
    public Server(){
        try {
            //Apertura della porta di ascolto del server
            serverSocket = new ServerSocket(portaServer);
            System.out.println("Server avviato correttamente");
            //Il server accetta le richieste del client
            socket = serverSocket.accept();
            System.out.println("Connessione stabilita");
            System.out.println("Socket: "+socket);
        } catch (ConnectException ex) {
            System.err.print(ex);
        } catch (IOException ex) {
            System.err.print(ex);
        }
    }
    
    public void comunica(){
        try {
            //Inizializzazione degli oggetti di comunicazione lato server
            inDalClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outVersoClient = new DataOutputStream(socket.getOutputStream());
            //Invio e ricezione di messaggi con il server
            messaggioDaInviare = "Benvenuto!";
            outVersoClient.writeBytes(messaggioDaInviare);
            //messaggioRicevuto = inDalClient.readLine();
            //System.out.println("Il client risponde: "+messaggioRicevuto);
        } catch (IOException ex) {
            System.err.print(ex);
        }
    }
    
    public void chiudi(){
        try {
            //Chiusura della connessione
            socket.close();
        } catch (IOException ex) {
            System.err.print(ex);
        }
    }
}
