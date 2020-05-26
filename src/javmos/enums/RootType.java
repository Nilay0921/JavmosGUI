package javmos.enums;

import java.awt.Color;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashSet;
import javmos.JavmosGUI;
import javmos.components.Point;
import javmos.components.functions.Function;

public enum RootType {
    X_INTERCEPT(Color.GREEN, "X-INT", FunctionType.ORIGINAL, FunctionType.FIRST_DERIVATIVE),
    CRITICAL_POINT(Color.RED, "CP", FunctionType.FIRST_DERIVATIVE, FunctionType.SECOND_DERIVATIVE),
    INFLECTION_POINT(Color.BLUE, "IP", FunctionType.SECOND_DERIVATIVE, FunctionType.THIRD_DERIVATIVE);

    public final Color color;
    public final String rootName;
    public final int ATTEMPTS = 10;
    public final FunctionType functionOne;
    public final FunctionType functionTwo;

    RootType(Color color, String rootName, FunctionType functionOne, FunctionType functionTwo) {
        this.color = color;
        this.rootName = rootName;
        this.functionOne = functionOne;
        this.functionTwo = functionTwo;
    }

    public Color getPointColor() {
        return color;
    }

    public String getPointName() {
        return rootName;
    }
    
    public java.util.HashSet<Point> getRoots(JavmosGUI gui, Function function, double minDomain, double maxDomain) {
        DecimalFormat dfth = new DecimalFormat("##.###");
        dfth.setRoundingMode(RoundingMode.HALF_DOWN);
        double xRootPoint;
        double yRootPoint;
        Point point;
        java.util.HashSet hashSet = new HashSet();
        for (double i = minDomain; i < maxDomain; i += 0.01) {
            try {
                xRootPoint = newtonsMethod(function, i, ATTEMPTS);
                yRootPoint = function.getValueAt(xRootPoint, FunctionType.ORIGINAL);
                /*if (function.equals(RootType.X_INTERCEPT)) {
                    point = new Point(gui, this, Double.parseDouble(dfth.format(xRootPoint)), 0.0);
                    hashSet.add(point);
                }*/
                point = new Point(gui, this, Double.parseDouble(dfth.format(xRootPoint)), Double.parseDouble(dfth.format(yRootPoint)));
                hashSet.add(point);
                
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            }
        }
        return hashSet;
    }
    
    public Double newtonsMethod(Function function, double guess, int attempts) {
        if (function.getValueAt(guess, functionTwo) == 0) {
            return null;
        } else if (attempts == 0) {
            return null;
        } else if (Math.abs(function.getValueAt(guess, functionOne) / function.getValueAt(guess, functionTwo)) < 0.0000001) {
            return guess;
        }
        return newtonsMethod(function, guess - (function.getValueAt(guess, functionOne) / function.getValueAt(guess, functionTwo)), attempts - 1);
    }
}
