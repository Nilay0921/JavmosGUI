package javmos.components;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Objects;
import javmos.JavmosGUI;
import javmos.enums.RootType;

public class Point extends JavmosComponent {

    public final int RADIUS = 15;
    public double x, y;
    public final RootType type;
    public Ellipse2D.Double point;

    public Point(JavmosGUI gui, RootType type, double x, double y) {
        super(gui);
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public RootType getRootType() {
        return type;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public int hashCode() { // returns the hash code for the x points
        return Objects.hash(x);
    }

    @Override
    public boolean equals(Object object) {
        return getClass().hashCode() == object.getClass().hashCode();
    }

    @Override
    public String toString() {
        if (x == 0) {
            x = Math.abs(0.0);
        } else if (y == 0) {
            y = Math.abs(0.0);
        }
        return type.getPointName() + ": (" + x + ", " + y + ")";
    }

    public Ellipse2D.Double getPoint() {
        return point;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        double x1 = (x * gui.getZoom() / gui.getDomainStep()) + gui.getPlaneWidth() / 2;
        double y1 = (y * -1 * gui.getZoom() / gui.getRangeStep()) + gui.getPlaneHeight() / 2;
        point = new Ellipse2D.Double(x1 - 6, y1 - 6, RADIUS, RADIUS);
        graphics2D.setColor(type.getPointColor());
        graphics2D.draw(point);
        graphics2D.fill(point);
    }
}
