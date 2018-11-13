/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatprogram;

import ProgramLab.Police;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author tuandung
 */
public class Client {

    Socket sock = null;
    PrintWriter writer;
    ObjectInputStream reader;

    public Client() {

    }

    public boolean isConnected() {
        return (sock != null && sock.isConnected());
    }

    public void connect() throws IOException {
        sock = new Socket("localhost", 3128);
        // reading from keyboard (keyRead object)
        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
        // sending to client (pwrite object)
        OutputStream ostream = sock.getOutputStream();
        writer = new PrintWriter(ostream, true);

        // receiving from server ( receiveRead  object)
        InputStream istream = sock.getInputStream();
        reader = new ObjectInputStream(istream);
        
        //BufferedReader receiveRead = new BufferedReader(new InputStreamReade10r(istream));
        System.out.println("Connected to server");
        System.out.println("thread client");
        
    }

    public void close() throws IOException {
        if (sock != null && sock.isConnected()) {
            sock.close();
        }
    }

    public synchronized Object request(String command) throws IOException, ClassNotFoundException {
        //đọc vào từ phím
        writer.println(command);       // sending to server
        writer.flush();
        Object returnedObj = null;
        returnedObj = reader.readObject();
        return returnedObj;
//            if (returnedObj != null && returnedObj.getClass() == String.class) {
//                System.out.println((String) returnedObj);
//            }
//            if (returnedObj != null && returnedObj.getClass() == ConcurrentSkipListSet.class) {
//                ConcurrentSkipListSet<Police> listSetReceived = (ConcurrentSkipListSet<Police>) returnedObj;
//
//                //System.out.println(returnedObj.toString());
//                listSetReceived.forEach((police) -> {
//                    System.out.println(police.toString()); // displaying at DOS prompt
//                });
//
//            }
    }
}
