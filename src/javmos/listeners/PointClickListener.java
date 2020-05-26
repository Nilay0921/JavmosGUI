package javmos.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import javmos.JavmosGUI;
import javmos.components.JavmosComponent;
import javmos.components.Point;
//import javmos.components.RootType;

public class PointClickListener implements MouseListener {

    private final JavmosGUI gui;
    private LinkedList<Point> points;

    public PointClickListener(JavmosGUI gui) {
        this.gui = gui;
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (points != null) {
            for (Point point : points) {
                if (point.getPoint().contains(event.getX(), event.getY())) {
                    gui.setPointLabel(point.toString(), point.getRootType());
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Not needed!
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Not needed!
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // Not needed!
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Not needed!
    }

    public void setPoints(LinkedList<Point> points) {
        this.points = points;
    }
}
