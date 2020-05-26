package javmos.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.LinkedList;
import javax.swing.JPanel;
import javmos.JavmosGUI;
import javmos.components.functions.Function;
import javmos.listeners.PointClickListener;


public class JavmosPanel extends JPanel {

    private final JavmosGUI gui;
    public final LinkedList<JavmosComponent> components;

    public JavmosPanel(JavmosGUI gui) {
        this.gui = gui;
        this.components = new LinkedList<>();
    }

    public Function getFunction() {
        for (JavmosComponent j : components) {
            if (j instanceof Function) {
                return (Function) j;
            }
        } 
        return null;
    }

    public void setPlane(CartesianPlane plane) {
        components.addFirst(plane);
        
    }

    public void setFunction(Function function) {
        components.add(function);
        LinkedList<Point> list = new LinkedList<>();
        list.addAll(function.getXIntercepts());
        list.addAll(function.getCriticalPoints());
        list.addAll(function.getInflectionPoints());
        components.addAll(function.getXIntercepts());
        components.addAll(function.getCriticalPoints());
        components.addAll(function.getInflectionPoints());
        components.addAll(list);
        PointClickListener pc = new PointClickListener(gui);
        pc.setPoints(list);
        this.addMouseListener(pc);
    }
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2 = (Graphics2D) graphics;
        CartesianPlane cp = new CartesianPlane(gui);
        cp.draw(g2);
        for (int i = 0; i < components.size(); i++) {
            components.get(i).draw(g2);
        }      
    }
}
