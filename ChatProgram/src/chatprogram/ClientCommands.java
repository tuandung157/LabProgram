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
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author tuandung
 */
public class ClientCommands {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Socket sock = null;
        try {
            sock = new Socket("localhost", 3128);
            // reading from keyboard (keyRead object)
            BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
            // sending to client (pwrite object)
            OutputStream ostream = sock.getOutputStream();
            PrintWriter pwrite = new PrintWriter(ostream, true);
            // receiving from server ( receiveRead  object)
            InputStream istream = sock.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(istream);
            //BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
            System.out.println("Start the chitchat, type and press Enter key");
            String sendMessage;
            Scanner scanner = new Scanner(System.in);
            JFrame.setDefaultLookAndFeelDecorated(true);
            JFrame frame = new JFrame("abc");
            while (true) {
                try {
                    //đọc vào từ phím
                    pwrite.println(scanner.nextLine());       // sending to server
                    pwrite.flush();
                    Object returnedObj=null;
                        returnedObj = ois.readObject();
                    if (returnedObj!=null&&returnedObj.getClass() == String.class) {
                        System.out.println((String) returnedObj);
                    }
                    if (returnedObj!=null&&returnedObj.getClass() == ConcurrentSkipListSet.class) {
                        ConcurrentSkipListSet<Police> listSetReceived  = (ConcurrentSkipListSet<Police>) returnedObj;

                        //System.out.println(returnedObj.toString());
                        listSetReceived.forEach((police) -> {
                            System.out.println(police.toString()); // displaying at DOS prompt
                        });

                    }
                } catch (IOException ex) {
                    Logger.getLogger(ClientCommands.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClientCommands.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //}
            //};
            /*while (true) {
                sendMessage = keyRead.readLine();  // keyboard reading
                pwrite.println(sendMessage);       // sending to server
                pwrite.flush();     
            }*/
        } catch (IOException ex) {
            Logger.getLogger(ClientCommands.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (sock != null && sock.isConnected()) {
                try {
                    sock.close();
                } catch (IOException ex) {
                    Logger.getLogger(ClientCommands.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
