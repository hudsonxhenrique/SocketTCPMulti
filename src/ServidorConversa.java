
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cris
 */
public class ServidorConversa extends Thread{
    private Socket socket;
    //private DataInputStream entrada;
    //private DataOutputStream saida;
    private ArrayList<PrintStream> clientes;

    public ServidorConversa(Socket socket, ArrayList<PrintStream> clientes){
        this.socket = socket;
        this.clientes = clientes;
        
        /*try{
            entrada = new DataInputStream(this.socket.getInputStream());
            saida = new DataOutputStream(this.socket.getOutputStream());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }*/
        this.start();
    }
    
    @Override
    public void run(){
        String mensagem = "";

            try {
                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                while ((mensagem = br.readLine()) != null) {
                    for (int a = 0; a < clientes.size(); a++) {
                        clientes.get(a).println(mensagem);
                        clientes.get(a).flush();
                    }
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }
    /*
    public void leitura(){
        new Thread(){
            
            @Override
            public void run(){
                while(true){
                try{
                String mensagem = entrada.readUTF();
                System.out.println("Mensagem Cliente:"+ mensagem);
                }catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
                }
            }
        }.start();*/
    }
