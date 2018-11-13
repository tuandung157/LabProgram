/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgramLab;

import java.util.Objects;

/**
 *
 * @author tuandung
 */
public class Machine implements Destroyable {

    protected String name;
    protected MachineStatus status;
    protected int durability;
    protected int position;

    public Machine(String name, int durability, int position) {
        this.name = name;
        this.status = MachineStatus.WORKING;
        this.durability = durability;
        this.position = position;
    }

    @Override
    public void being_destroyed(int damage) {
        if (durability >= 0) {
            durability -= damage;
        }
        if (durability < 0 && status==MachineStatus.WORKING) {
            System.out.println(name + " destroyed");
            status = MachineStatus.BROKEN;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.name);
        hash = 19 * hash + Objects.hashCode(this.status);
        hash = 19 * hash + this.durability;
        hash = 19 * hash + this.position;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Machine other = (Machine) obj;
        if (this.position != other.position) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "\n\tMachine{" + "name=" + name + ", status=" + status + ", durability=" + (durability<0?0:durability) + ", position=" + position + '}';
    }
    
    

}
