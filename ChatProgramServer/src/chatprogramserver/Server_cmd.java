///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package chatprogramserver;
//
//import ProgramLab.*;
//import java.awt.Color;
//import java.io.*;
//import java.io.PrintWriter;
//import static java.lang.Math.E;
//import static java.lang.StrictMath.E;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.net.SocketException;
//import java.text.ParseException;
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentSkipListSet;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class Server_cmd {
//
//
//
//    private static ConcurrentSkipListSet<Police> csSet = new ConcurrentSkipListSet<>(new PComparator());
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//
//
//        String fileName =System.getProperty("user.home") + "\\" + "polices.csv"; /*"/home/s247407/polices.csv";*/
//        System.out.println(fileName);
//
//        try {
//            CsvFile.loadCsvFile(fileName, (List<Police>) csSet);
//        } catch (ParseException ex) {
//            Logger.getLogger(Server_cmd.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Server_cmd.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println("Date Loaded In!");
//        try {
//            ServerSocket sersock = new ServerSocket(3128);
//
//            while (true) {
//                System.out.println("Server  ready for chatting");
//                Socket sock = sersock.accept();
//                SvRunnable newRunnable = new SvRunnable(sock) {
//                    @Override
//                    @SuppressWarnings("empty-statement")
//                    public void run() {
//
//                        ObjectOutputStream objStream = null;
//                        BufferedReader receiveRead = null;
//                        try {
//                            // reading from keyboard (keyRead object)
//                            BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
//                            // sending to client (pwrite object)
//                            OutputStream ostream = cliSocket.getOutputStream();
//                            //PrintWriter pwrite = new PrintWriter(ostream, true);
//                            objStream = new ObjectOutputStream(ostream);
//                            // receiving from server ( receiveRead  object)
//                            InputStream istream = cliSocket.getInputStream();
//                            receiveRead = new BufferedReader(new InputStreamReader(istream));
//                            String receiveMessage, sendMessage;
//                            while (true) {
//                                try {
//                                    if ((receiveMessage = receiveRead.readLine()) != null) {
//                                        //System.out.println(receiveMessage);
//                                        if (receiveMessage.equals("exit")) {
//                                            break;                                           
//                                        } else if (receiveMessage.startsWith("remove_lower")) {
//                                            Police p = Police.jsonToObject(receiveMessage.replaceFirst("remove_lower ", ""));
//                                            while (csSet.lower(p) != null) {
//                                                System.out.println(p);
//                                                csSet.remove(csSet.lower(p));
//                                            }
//                                            objStream.writeObject("Completed!");
//                                        } else if (receiveMessage.startsWith("add_if_min ")) {
//                                            synchronized(csSet){
//                                            Police p = Police.jsonToObject(receiveMessage.replaceFirst("add_if_min ", ""));
//                                            while (csSet.lower(p) != null) {
//                                                csSet.add(p);
//                                                }
//                                            }
//                                            objStream.writeObject("Completed!");
//                                        } else if (receiveMessage.startsWith("remove_greater ")) {
//                                            synchronized(csSet){
//                                            Police p = Police.jsonToObject(receiveMessage.replaceFirst("remove_greater ", ""));
//                                            //System.out.println(receiveMessage);
//                                            while (csSet.higher(p) != null) {
//                                                csSet.remove(csSet.higher(p));
//                                                }
//                                            }
//                                            objStream.writeObject("Completed!");
//                                        
//                                        }else if (receiveMessage.startsWith("remove ")) {
//                                            Police p = Police.jsonToObject(receiveMessage.replaceFirst("remove ", ""));
//                                            final Set<Police> delList = Collections.newSetFromMap(new ConcurrentHashMap<>());
//                                            csSet.stream().filter( h -> p.equals(p)).forEach((Police h)->{
//                                                System.out.println("remove " + h);
//                                                delList.add(h);
//                                                csSet.remove(p);
//                                            });                                        
//                                            objStream.writeObject("Completed!");
//                                        } else if (receiveMessage.equals("add_random")) {
//                                            
//                                            synchronized (csSet){
//                                                Random rand = new Random(System.currentTimeMillis());
//                                                Police p = new Police("Noname"+(new Random(System.currentTimeMillis()).nextInt()), 
//                                                    new Date(rand.nextLong()), new Point(0, 0), 0, 0,new Color(rand.nextInt(),false));
//                                                csSet.add(p);
//                                            };
//                                            //csSet.add(p);
//                                            objStream.writeObject("Completed!");
//                                        } else if (receiveMessage.startsWith("add")) {
//                                            Police p = Police.jsonToObject(receiveMessage.replaceFirst("add ", ""));
//                                            csSet.add(p);
//                                            objStream.writeObject("Completed!");
//
//                                        } else if (receiveMessage.startsWith("print")) {
//                                            synchronized (csSet){
//                                                //objStream.writeObject(csSet);
//                                                objStream.writeObject(csSet.clone());
//                                            }
//                                        } else {
//                                            objStream.writeObject("Command not recognized!");
//                                        }
//                                        objStream.flush();
//                                    }
//                                    //sendMessage = keyRead.readLine();
//
//                                    //pwrite.println(sendMessage);
//                                    //pwrite.flush();
//                                } catch ( IOException | ParseException ex) {
//                                    break;
//                                    //Logger.getLogger(ChatProgramServer.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                            }
//                        } catch (IOException ex) {
//                            Logger.getLogger(Server_cmd.class.getName()).log(Level.SEVERE, null, ex);
//                        } finally {
//                            try {
//                                objStream.close();
//                                receiveRead.close();
//                            } catch (IOException ex) {
//                                Logger.getLogger(Server_cmd.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                        }
//                    }
//                };
//                new Thread(newRunnable).start();
//
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(Server_cmd.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    static void addRandom(){
//    
//    }
//}