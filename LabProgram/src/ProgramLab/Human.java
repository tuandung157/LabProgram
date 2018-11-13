/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgramLab;

import java.awt.Color;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Objects;
/**
 *
 * @author tuandung
 */
public abstract class Human implements SpeakSkill, ClimbSkill, Serializable{
    protected String name;
    protected Date dob;
    protected Point current_position;  
    protected int speed;
    protected Color color;
    protected LocalDateTime time;
    protected int ID;
    public Human(String name, Date dob, Point current_position, int speed,Color color) {
        this.name = name;
        this.dob = dob;
        this.current_position = current_position;
        this.speed = speed;
        this.color = color;
        this.time = LocalDateTime.now();
        }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    
    
    @Override
    public void speak(String phrase) {
        System.out.println(phrase);
    }
@Override
    public void speak() {
        System.out.println("*cough*");
    }
@Override
    public void speak(String phrase,int time) {
        System.out.println(phrase);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

//    @Override
//    public void climb() {
//        current_position+=1;
//        speak(name+": Im at "+current_position);
//    }

//    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 71 * hash + Objects.hashCode(this.name);
//        hash = 71 * hash + this.age;
//        return hash;
//    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        final Human other = (Human) obj;
        if (this.current_position != other.current_position) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed)  {
        if (speed < 0){
            throw new SpeedException();
        }
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Human{" + "name=" + name + ", dob=" + dob + ", current_position=" + current_position + ", speed=" + speed + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.dob);
        return hash;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Point getCurrent_position() {
        return current_position;
    }

    public void setCurrent_position(Point current_position) {
        this.current_position = current_position;
    }

    public Human(){
        
    }

}
