package javmos.components.functions;

/**
 *
 * @author sammb
 */
import javmos.JavmosGUI;
import javmos.enums.FunctionType;

public class Sine extends Trignometric {

    public Sine(JavmosGUI gui, String function) {
        super(gui, function, "sin");
        int idxValue = trigFunction.indexOf("="); 
        trigFunction = trigFunction.substring(idxValue + 1);
        if (trigFunction.charAt(0) != name.charAt(0)) {
            a = Double.parseDouble(trigFunction.substring(0, trigFunction.indexOf(name.charAt(0))));
        } else
            a = 1;
        if (trigFunction.charAt(trigFunction.indexOf("(") + 1) != 'x') {
            k = Double.parseDouble(trigFunction.substring(trigFunction.indexOf("(") + 1, trigFunction.indexOf("x")));         
        } else {
            k = 1;
        }
    }

    @Override
    public String getFirstDerivative() {
        return a * k + "cos(" + k + "x)";
    }

    @Override
    public String getSecondDerivative() {
        return -a * k * k + "sin(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        switch(functionType) {
            case FIRST_DERIVATIVE:
                return a * k * Math.cos(k * x);
            case SECOND_DERIVATIVE:
                return -a * k * k * Math.sin(k * x);
            case THIRD_DERIVATIVE:
                return -a * k * k * k * Math.cos(k * x);
            default:
                return a * Math.sin(k * x);
        }
    }  
}
