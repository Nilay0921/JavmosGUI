package javmos.components.functions;

import javmos.JavmosGUI;
import javmos.enums.FunctionType;

public class Tangent extends Trignometric {

    public Tangent(JavmosGUI gui, String function) {
        super(gui, function, "tan");
        int idxValue = trigFunction.indexOf("=");
        trigFunction = trigFunction.substring(idxValue + 1);
        if (trigFunction.charAt(0) != name.charAt(0)) {
            a = Double.parseDouble(trigFunction.substring(0, trigFunction.indexOf(name.charAt(0))));
        } else {
            a = 1;
        }
        if (trigFunction.charAt(trigFunction.indexOf("(") + 1) != 'x') {
            k = Double.parseDouble(trigFunction.substring(trigFunction.indexOf("(") + 1, trigFunction.indexOf("x")));
        } else {
            k = 1;
        }
    }

    @Override
    public String getFirstDerivative() {
        return a * k + "sec^2(" + k + "x)";
    }

    @Override
    public String getSecondDerivative() {
        return 2 * a * k * k + "sec^2(" + k + "x)tan(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        switch (functionType) {
            case FIRST_DERIVATIVE:
                return a * k / Math.pow((Math.cos(k * x)), 2);
            case SECOND_DERIVATIVE:
                return 2 * a * k * k * Math.tan(k * x) / Math.pow(Math.cos(k * x), 2);
            case THIRD_DERIVATIVE:
                return 2 * a * Math.pow(k, 3) * (1 / Math.pow(Math.cos(k * x), 2)) * (3 * (1 / Math.pow(Math.cos(k * x), 2)) - 2);
            default:
                return a * Math.tan(k * x);
        }
    }
}
