package javmos.components.functions;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.HashSet;
import javmos.JavmosGUI;
import javmos.components.JavmosComponent;
import javmos.components.Point;
import javmos.enums.FunctionType;
import javmos.enums.RootType;


public abstract class Function extends JavmosComponent {

    protected Function(JavmosGUI gui) {
        super(gui);
    }

    public HashSet<Point> getXIntercepts() {
        return RootType.X_INTERCEPT.getRoots(gui, this, -50, 50);
    }
    
    public HashSet<Point> getCriticalPoints() {
        return RootType.CRITICAL_POINT.getRoots(gui, this, -50, 50);
    }
    
    public HashSet<Point> getInflectionPoints() {
        return RootType.INFLECTION_POINT.getRoots(gui, this, -50, 50);
    }

    @Override
    public void draw(java.awt.Graphics2D graphics2D) {
        Color myColor = new Color(153, 0, 76);
        graphics2D.setColor(myColor);
        graphics2D.setStroke(new java.awt.BasicStroke(3));

        for (double i = gui.getMinDomain(); i < gui.getMaxDomain(); i += 0.01) {
            try {
                if (this.getValueAt(i, FunctionType.ORIGINAL) > gui.getMinRange() && this.getValueAt(i, FunctionType.ORIGINAL) < gui.getMaxRange()) {
                    double xa = i * gui.getZoom() / gui.getDomainStep();
                    double ya = this.getValueAt(i, FunctionType.ORIGINAL) * gui.getZoom() / gui.getRangeStep();
                    double xb = (i + 0.01) * gui.getZoom() / gui.getDomainStep();
                    double yb = this.getValueAt(i + 0.01, FunctionType.ORIGINAL) * gui.getZoom() / gui.getRangeStep();
                    Line2D.Double line = new Line2D.Double(xa + 400, 400 - ya, xb + 400, 400 - yb);
                    if (line.getY1() + 5000 > line.getY2()) {
                        graphics2D.draw(line);
                    }
                    //graphics2D.draw(new Line2D.Double(xa + 400, 400 - ya, xb + 400, 400 - yb));
                }
            } catch (ArrayIndexOutOfBoundsException e) {               
            } catch (NullPointerException ex) {            
            }
        }        
    }
    public abstract String getFirstDerivative();
    
    public abstract String getSecondDerivative();
    
    public abstract double getValueAt(double x, FunctionType functionType);
}
