package javmos.components;

import java.awt.*;
import java.awt.Graphics2D;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javmos.JavmosGUI;

public class CartesianPlane extends JavmosComponent {
    
    public CartesianPlane(JavmosGUI gui) {
        super(gui);
    }

    public void draw(Graphics2D graphics2D) {

        graphics2D.setColor(Color.BLACK);

        int zoom = (int) gui.getZoom();     // by default 50
        int px = gui.getPlaneWidth();       // by default 800
        int middle = px / 2;                // equal to 400 ---> give middle point
        double domStep = gui.getDomainStep();
        double rangeStep = gui.getRangeStep();
        // Added variable in order to prevent for loop from running 800 times
        //int lines = (px/2) / zoom;
        DecimalFormat dfth = new DecimalFormat("##.###");
        dfth.setRoundingMode(RoundingMode.HALF_DOWN);
        for (int i = 0; i <= 800; i++) {

            if (i == 0) {
                graphics2D.setStroke(new BasicStroke(2));
            } else {
                graphics2D.setStroke(new BasicStroke(1));
            }
            // quadrant 2 and 3 vertical

            graphics2D.drawLine(middle - zoom * i, 0, middle - zoom * i, px);
            //quadrant 1 and 2 horizontal
            graphics2D.drawLine(0, middle - zoom * i, px, middle - zoom * i);
            // quadrant 1 and 4 vertical
            graphics2D.drawLine(middle + zoom * i, 0, middle + zoom * i, px);
            // quadrant 3 and 4 horizontal
            graphics2D.drawLine(0, middle + zoom * i, px, middle + zoom * i);

            // negative horizontal
            graphics2D.drawString(String.valueOf(Double.parseDouble(dfth.format(i * domStep * -1))), middle - zoom * i, middle);
            // postive vertical
            graphics2D.drawString(String.valueOf(Double.parseDouble(dfth.format(i * rangeStep))), middle, middle - zoom * i);
            // negative vertical
            graphics2D.drawString(String.valueOf(Double.parseDouble(dfth.format(i * rangeStep * - 1))), middle, middle + zoom * i);
            //positive horizontal
            graphics2D.drawString(String.valueOf(Double.parseDouble(dfth.format(i * domStep))), middle + zoom * i, middle);

        }
    }
}
