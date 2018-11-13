/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgramLab;
import java.io.*;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.String;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
/**
 *
 * @author tuandung
 */
public class Main_Lab5 {

//    public static void main(String[] args) throws IOException {
//        System.out.println(args[0]);
//        // init treeSet
//        SortedSet sortedSet = Collections.synchronizedSortedSet(new TreeSet<>(new Comparator<Police>() {
//            @Override
//            public int compare(Police o1, Police o2) {
//                return o1.compareTo(o2);
//            }
//        }));
//        String fileName = System.getProperty("user.home") + "\\" + args[0];
//        
//        try {
//            CsvFile.loadCsvFile(fileName, sortedSet);
//        } catch (IOException ex) {
//            Logger.getLogger(Main_Lab5.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        Scanner scanner = new Scanner(System.in);
//        //String currentLine;
//        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//       // while((currentLine = br.readLine())!=null){
//       //     System.out.println(currentLine);
//            
//       // }
//        while (true) {
//            String s = scanner.nextLine();
//            //String s = br.readLine();
//            if (s.equals("exit")) {
//                break;
//            }
//
//            if (s.equals("print")) {
//                for (Iterator it = sortedSet.iterator(); it.hasNext();) {
//                   Police p = (Police) it.next();
//                    System.out.println(p.toString());
//                }
//            }
//
//            if (s.startsWith("remove_lower ")) {
//                Police p = Police.jsonToObject(s.replaceFirst("remove_lower ", ""));
//                while (treeSet.lower(p) != null) {
//                    System.out.println(s);
//                    treeSet.remove(treeSet.lower(p));
//                }
//            }     "key":"value"
//  
//  /*    Thread thread = new Thread(){
//    public void run(){
//      System.out.println("Thread Running");
//    }
//  }
//
//  thread.start();*/
//            if (s.startsWith("add_if_min ")) {
//                Police p = Police.jsonToObject(s.replaceFirst("add_if_min ", ""));
//               while (treeSet.lower(p) != null) {
//                    treeSet.add(p);
//                }
//
//            }
//            if (s.startsWith("remove_greater ")) {
//                Police p = Police.jsonToObject(s.replaceFirst("remove_greater ", ""));
//                //System.out.println(s);
//                while (treeSet.higher(p) != null) {
//                    treeSet.remove(treeSet.higher(p));
//                }
//            }
//            if (s.startsWith("remove ")) {
//                Police p = Police.jsonToObject(s.replaceFirst("remove ", ""));
//                treeSet.remove(p);
//
//            }
//            if (s.startsWith("add")) {
//                for (int i = 0; i < 5; i++) {
//                    Police p = new Police("A" + (int) (Math.random() * 50), (int) (Math.random() * 80), (int) (Math.random() * 20), (int) (Math.random() * 10), (int) (Math.random() * 5));
//                    treeSet.add(p);
//                }
//           }
//            if (s.startsWith("load")) {
//                treeSet = new TreeSet<>(new Comparator<Police>() {
//                    @Override
//                    public int compare(Police o1, Police o2) {
//                        return o1.compareTo(o2);
//                    }
//                });
//                try {
//                    CsvFile.loadCsvFile(fileName, treeSet);
//                } catch (IOException ex) {
//                    Logger.getLogger(Main_Lab5.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//        }
//
//// show treeSet
////        for (Police police : treeSet) {
////            System.out.println(police.toString());
////        }
////        System.out.println(fileName);
////        System.out.println("write CSV file:");
////        try {
////            CsvFile.writeCsvFile(fileName, treeSet);
////        } catch (IOException ex) {
////            Logger.getLogger(Main_Lab5.class.getName()).log(Level.SEVERE, null, ex);
////        }
////    }
}
