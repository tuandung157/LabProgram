/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatprogramserver;

import ProgramLab.Point;
import ProgramLab.Police;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tuandung
 */
public class Server extends Thread{
    private ServerSocket sersock;
    private ArrayList<Police> data;
    public Server(ArrayList<Police> data) throws IOException {
        this.data = data;
        sersock = new ServerSocket(3128);
        
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                System.out.println("Server  ready for chatting");
                System.out.println("threadwait");
                Socket sock = sersock.accept();
                SvRunnable newRunnable = new SvRunnable(sock) {
                    @Override
                    @SuppressWarnings("empty-statement")
                    public void run() {

                        ObjectOutputStream objStream = null;
                        BufferedReader receiveRead = null;
                        try {
                            // reading from keyboard (keyRead object)
                            BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
                            // sending to client (pwrite object)
                            OutputStream ostream = cliSocket.getOutputStream();
                            //PrintWriter pwrite = new PrintWriter(ostream, true);
                            objStream = new ObjectOutputStream(ostream);
                            // receiving from server ( receiveRead  object)
                            InputStream istream = cliSocket.getInputStream();
                            receiveRead = new BufferedReader(new InputStreamReader(istream));
                            String receiveMessage, sendMessage;
                            while (true) {
                                try {
                                    if ((receiveMessage = receiveRead.readLine()) != null) {
                                        if (receiveMessage.equals("exit")) {
                                            break;                                           
                                        } if (receiveMessage.startsWith("print")) {
                                            synchronized (data){
                                                objStream.writeObject(data.clone());
                                                System.out.println(data);
                                                }
                                        } else {
                                            objStream.writeObject("Command not recognized!");
                                        }
                                        objStream.flush();
                                    }
                                } catch ( IOException ex) {
                                    break;
                                }
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(Server_cmd.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            try {
                                objStream.close();
                                receiveRead.close();
                            } catch (IOException ex) {
                                Logger.getLogger(Server_cmd.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                };
                new Thread(newRunnable).start();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }

            }
    }
    
    
}
