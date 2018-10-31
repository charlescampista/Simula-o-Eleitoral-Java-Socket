/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidortre;

import gui.FrameTRE;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charles
 */
public class ServidorTRE {

    PrintWriter writer;
    FrameTRE frameTRE;
    Socket socket;
    public ServidorTRE() {
        frameTRE = new FrameTRE(this);
        frameTRE.setVisible(true);
    }
    
    
    public void configurarServidor(int porta){
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(porta);
            while (true) {
                Socket socket = serverSocket.accept();
                //writer = new PrintWriter(socket.getOutputStream());
                new Thread(new EscutaCliente(socket)).start();
                
                
            }
        } catch(Exception e) {}
    }
    
    private  class EscutaCliente implements Runnable {
        
        Scanner scanner;
        public EscutaCliente(Socket socket) {
            try {
                scanner = new Scanner(socket.getInputStream());    
            }catch(Exception e) {}
        }
        
        
        @Override
        public void run() {
            try {
                    String texto;
                    while( (texto = scanner.nextLine()) != null) {
                        System.out.println("Recebeu: "+texto);
                        frameTRE.calcularVotos(texto);
                        
                    }
                
            } catch(Exception e) {}
        }
        
    }
    
    
    public void enviarDados(String votos,String ip, int porta){
        try {
            socket = new Socket(ip,porta);
            writer = new PrintWriter(socket.getOutputStream());
            writer.println(votos);
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServidorTRE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        ServidorTRE servidor = new ServidorTRE();
        
    }
    
}
