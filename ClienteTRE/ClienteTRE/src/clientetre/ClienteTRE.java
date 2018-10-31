/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetre;

import gui.FrameTRE;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charles
 */
public class ClienteTRE {
    
    String ip;
    int porta;
    Socket socket;
    PrintWriter writer;
            
    public ClienteTRE(String ip, int porta) {
        this.ip = ip;
        this.porta = porta;
        configurarRede(ip,porta);
    }
    
    private void configurarRede(String ip, int porta) {
        try {
            socket = new Socket(ip,porta);
            writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClienteTRE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarDados(String votos){
        writer.println(votos);
        writer.flush();
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new FrameTRE().setVisible(true);
    }
    
}
