/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgramLab;

import java.io.Serializable;

/**
 *
 * @author tuandung
 */
public class PComparator implements Serializable , java.util.Comparator<Police>{

    public PComparator() {
    }


    @Override
    public int compare(Police o1, Police o2) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         return o1.compareTo(o2);
    }
    
}
