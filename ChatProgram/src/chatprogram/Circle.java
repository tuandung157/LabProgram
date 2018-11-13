/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatprogram;

import ProgramLab.Police;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tuandung
 *
 *
 */
public class Circle implements MouseMotionListener, Runnable {

    private static final int MIN_SIZE = 50;
    private static final int MAX_SIZE = 100;

    int width, height;
    int direction = 1;
    Color backColor = new Color(214, 217, 223,255);
    Police data;
    private JFrame parent;

    public Circle(JFrame parent, Police police) {
        this.data = police;
        this.parent = parent;
        width = MIN_SIZE;
        height = MIN_SIZE;
    }

    @Override
    public void run() {
        direction = 1;
        //width+=1;
        //height+=1;
        do
        {
            try {
                if (width >= MAX_SIZE) {
                    direction *= -1;
                }
                width += getChangeRate(5);
                height += getChangeRate(5);
                if (direction > 0) {
                    draw(parent.getGraphics());
                } else {
                    draw_decrease(parent.getGraphics());
                }
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Circle.class.getName()).log(Level.SEVERE, null, ex);
            }

        }while (width >= MIN_SIZE);
        width = MIN_SIZE;
        height = MIN_SIZE;
        draw(parent.getGraphics());
        System.out.println("dead!");

    }

    private int getChangeRate(int time) {
        return (MAX_SIZE - MIN_SIZE) / time /5 * direction;
    }

    public int getX() {
        return data.getCurrent_position().getX() + MIN_SIZE / 2;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getY() {
        return data.getCurrent_position().getY() + MIN_SIZE / 2;
    }

    Color getColor() {
        return data.getColor();
    }

    public void drawInfo(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.setColor(Color.red);
        g2d.drawString("Name: " + data.getName(), getX() + width, getY() - 20);
        g2d.drawString("Atk: " + data.getAtk(), getX() + width, getY());
        g2d.drawString("Speed: " + data.getSpeed(), getX() + width, getY() + 20);

    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //g.clearRect(getX() - width, getY() - height, width * 4, height * 2);
        Ellipse2D.Double circle = new Ellipse2D.Double(getX() - width / 2, getY() - height / 2, width, height);
        g2d.setColor(getColor());
        g2d.fill(circle);
        if (isMouseIn) {
            drawInfo(g);
        }
    }

    public void draw_decrease(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double cirDouble = new Ellipse2D.Double(getX() - width / 2, getY() - height / 2, width, height);
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(backColor);
        g2d.draw(cirDouble);
        if (isMouseIn) {
            drawInfo(g);
        }
    }

    private double getDistance(double x, double y) {
        return Math.sqrt((getX() - x) * (getX() - x) + (getY() - y) * (getY() - y));
    }
    private boolean isMouseIn = false;

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (getDistance(e.getX(), e.getY()) < width / 2 && !isMouseIn) {
            isMouseIn = true;
            System.out.println("Entered");
            parent.repaint();
            //draw(parent.getGraphics());
        }
        if (getDistance(e.getX(), e.getY()) > width / 2 && isMouseIn) {
            isMouseIn = false;
            System.out.println("Exited");
            parent.repaint();
            //draw(parent.getGraphics());
        }
    }

}
