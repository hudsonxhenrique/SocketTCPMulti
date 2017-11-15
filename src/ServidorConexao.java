
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cris
 */
public class ServidorConexao {
    public static void main(String[] args) {
        
        ArrayList<PrintStream> clientes = new ArrayList<>();
        Socket socket;
        
        try {
            ServerSocket servidor = new ServerSocket(1600);
            
            while(true){
                
                socket = servidor.accept();
                clientes.add(new PrintStream(socket.getOutputStream()));
                ServidorConversa mensagem = new ServidorConversa(socket,clientes);
                //ServidorConversa conexao = new ServidorConversa(socket);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
