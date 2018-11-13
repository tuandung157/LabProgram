/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgramLab;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author tuandung
 */
public class ResearchCenter {
    //attributes
    //private Human human;
    private List<Machine> machines;

    public ResearchCenter() {
        machines = new LinkedList<>();
        machines.add(new Machine("барометр", 3,20));
        machines.add(new Machine("сейсмограф", 4,30));
        machines.add(new Machine("дождемер", 2,50));
        machines.add(new Machine("анемометр", 5,90));
        
    }
    public Machine getMachine(int position){
        for(Machine m:machines){
            if(m.position==position) return m;
        }
        return null;
    }
    public List<Machine> getMachine() {
        return machines;
    }
    public boolean allDestroyed(){
        for(Machine m : machines) if(m.status==MachineStatus.WORKING) return false;
        return true;
    }
    @Override
    public String toString() {
        return "ResearchCenter" + machines + '\n';
    }        
}
