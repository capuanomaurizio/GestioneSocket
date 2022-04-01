/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionesocket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author mauri
 */
public class Client {
    
    int portaServer = 2000; 
    Socket socket;
    BufferedReader tastiera;
    BufferedReader inDalServer;
    DataOutputStream outVersoServer;
    String messaggioRicevuto;
    String messaggioDaInviare;
    
    public Client(){
        try {
            //Richiesta di connessione al server
            socket = new Socket(InetAddress.getLocalHost(), 2000);
        } catch (UnknownHostException ex) {
            System.out.println("Errore, host inesistente");
            System.err.print(ex);
        } catch (IOException ex) {
            System.out.println("Errore nella connessione al server");
            System.err.print(ex);
        }
    }
    
    public void comunica(){
        try {
            //Inizializzazione degli oggetti di comunicazione lato client
            inDalServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outVersoServer = new DataOutputStream(socket.getOutputStream());
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            //Invio e ricezione di messaggi con il server
            messaggioRicevuto = inDalServer.readLine();
            System.out.println("Il server dice: "+messaggioRicevuto);
            System.out.print("La tua risposta al server: ");
            messaggioDaInviare = tastiera.readLine();
            outVersoServer.writeBytes(messaggioDaInviare);
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
