package javmos.components.functions;

import javmos.JavmosGUI;

public abstract class Trignometric extends Function {
    
    protected double a;
    protected double k;
    public String trigFunction;
    public final String name;
    
    
    public Trignometric(JavmosGUI gui, String trigFunction, String name) {
        super(gui);
        /*if (trigFunction.charAt(0) != name.charAt(0)) {
            a = Double.parseDouble(trigFunction.substring(0, trigFunction.indexOf(name.charAt(0))));
        } else
            a = 1;
        if (trigFunction.charAt(trigFunction.indexOf("(") + 1) != 'x') {
            k = Double.parseDouble(trigFunction.substring(trigFunction.indexOf("(") + 1, trigFunction.indexOf("x")));         
        } else {
            k = 1;
        }*/
        this.trigFunction = trigFunction;
        this.name = name;
    }   
}
