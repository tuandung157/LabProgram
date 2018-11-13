package ProgramLab;

import java.awt.Color;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tuandung
 */
public class Police extends Human implements DestroySkill, Comparable<Police>, Serializable {

    protected int atk = 1;

    public Police(String name, Date dob, Point current_position, int speed, int atk,Color color) {
        super(name, dob, current_position, speed, color);
        this.atk = atk;
        }

    public Police() {
        this("",new Date(),new Point(),0,0,Color.black);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy(Machine target) {
        target.being_destroyed(atk);
    }

    public int compareTo(Police police) {
        //return this.hashCode()-police.hashCode();
        return police.name.compareTo(this.name);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        return "Police{" + "name= " + name + ", Dob=" + sdf.format(dob) + ", point=" + current_position.toString() + ", speed=" + speed + ", atk=" + atk + '}';
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public static Police jsonToObject(String s) throws ParseException {
        Police p = new Police("", new Date(), new Point(), 0, 0,Color.BLACK);
        String[] pairs = s.substring(1, s.length() - 1).split(",");
        for (String pair : pairs) {
            //System.out.println(pair);
            String[] tokens = pair.split(":|\"");
            if ("name".equals(tokens[1])) {
                p.setName(tokens[4]);
            }
            if ("dob".equals(tokens[1])) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                Date str_date = sdf.parse(tokens[4]);
            }
            if ("current_position".equals(tokens[1])) {
                p.setCurrent_position(Point.parse(tokens[4]));
            }
            if ("speed".equals(tokens[1])) {
                p.setSpeed(Integer.parseInt(tokens[4]));
            }
            if ("atk".equals(tokens[1])) {
                p.setAtk(Integer.parseInt(tokens[4]));
            }
        }
        //System.out.println(p.toString());
        return p;
    }

//    @Override
//    public String toString() {
//        return "Police{" + "name=" + name + ", age=" + age + ", current_position=" + current_position + ", speed=" + speed + ", atk=" + atk + '}';
//    }
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.name.hashCode();
        hash = 79 * hash + this.dob.hashCode();
        hash = 79 * hash + this.current_position.hashCode();
        hash = 79 * hash + this.speed;
        hash = 79 * hash + this.atk;
        
        
        return (int) (hash + hash);
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
        final Police other = (Police) obj;
        return compareTo(other)!=0;
    }

    @Override
    public void climb() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

