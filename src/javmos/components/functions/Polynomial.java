
package javmos.components.functions;

import javax.swing.JOptionPane;
import javmos.JavmosGUI;
import javmos.enums.FunctionType;

public class Polynomial extends Function { // got parts of this class from farhans groups old code

    private int[] degrees;
    private double[] coefficients;

    public Polynomial(JavmosGUI gui, String polynomial) {
        super(gui);

        //try {
            polynomial = polynomial.contains("=") ? polynomial.substring(polynomial.indexOf("=") + 1, polynomial.length()) : polynomial; //if an = sign exists evrything after it is taken as the polynomial otherwise polynomial is taken by itself
            String[] terms = polynomial.charAt(0) == '-' ? polynomial.substring(1, polynomial.length()).split("\\+|\\-") : polynomial.split("\\+|\\-"); //# of terms is equal to the length of an array that splits the + and - signs from polynomial
            coefficients = new double[terms.length]; //# of coeffs = number of total terms
            degrees = new int[terms.length]; //# of degrees = number of total terms
            int termsStart = 0;

            //run a for loop to anazlyze each terms individually to retrieve coeffs and degrees 
            for (int i = 0; i < terms.length; i++) {
                if (terms[i].contains("x^")) {
                    if (terms[i].substring(0, 2).equals("x^")) {
                        coefficients[i] = 1; //if a term beings with x, coeff = 1
                    } else {
                        coefficients[i] = Double.parseDouble(terms[i].substring(0, terms[i].indexOf("x"))); //take everything before the x as coeff
                    }
                    degrees[i] = Integer.parseInt(terms[i].substring(terms[i].indexOf("^") + 1, terms[i].length())); //take everything after the ^ as degree
                } else if (terms[i].contains("x") && !terms[i].contains("^")) {
                    coefficients[i] = terms[i].length() == 1 ? 1 : Double.parseDouble(terms[i].substring(0, terms[i].indexOf("x"))); //if length of term is 1 then coeff must be 1, otherwise everything before the x is taken as coeff
                    degrees[i] = 1; //degree must be 1
                } else {
                    coefficients[i] = Double.parseDouble(terms[i]); //non x term therefore entire terms is parsed
                    degrees[i] = 0; //non x term therefore degree = 0
                }
                coefficients[i] *= (polynomial.contains("-") && polynomial.substring(termsStart, termsStart + 1).equals("-")) ? -1 : 1; //if a - exists in the polynomial and the first character of ther terms is -, multiply coeffeicient by -1
                termsStart += i == 0 && !(polynomial.charAt(0) == '-') ? terms[i].length() : terms[i].length() + 1; //used to refernce where each term begins relative to the entire polynomial string
            }

        //} catch (Exception exception) {
            //JOptionPane.showMessageDialog(gui.frame, polynomial + " is not a valid function!", "Error", JOptionPane.ERROR_MESSAGE, null);
        //}
    }

    @Override
    public String getFirstDerivative() {
        double[] derCoeff = new double[coefficients.length];
        int[] derDegrees = new int[degrees.length];
        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] != 0 && (degrees[i] != 0)) {
                derCoeff[i] = coefficients[i] * degrees[i];
                derDegrees[i] = degrees[i] - 1;
            }
        }
        String s = "";
        for (int i = 0; i < derCoeff.length; i++) {
            if (derDegrees[i] < 0 || (Math.abs(derCoeff[i]) == 1.0 && derDegrees[i] != 0)) {
                s += "";
            } else if ((derCoeff[i] < 0 || i == 0) || Math.abs(derCoeff[i]) == 1.0) { // If coefficient is negative
                s += derCoeff[i];
            } else if (derCoeff[i] > 0 && i != 0) { // If coefficient is positive
                s += "+" + derCoeff[i];
            }

            if (derDegrees[i] == 1) { //Add degree according to degree value
                s += "x";
            } else if (derDegrees[i] > 1) {
                s += "x^" + derDegrees[i];
            }
        }
        return s;
    }

    @Override
    public String getSecondDerivative() {
        return new Polynomial(gui, getFirstDerivative()).getFirstDerivative();
    }
    
    @Override
    public double getValueAt(double x, FunctionType functionType) {
        Polynomial p = null;
        if (functionType != null) switch (functionType) {
            case FIRST_DERIVATIVE:
                p = new Polynomial(gui, getFirstDerivative());
                break;
            case SECOND_DERIVATIVE:
                p = new Polynomial(gui, getSecondDerivative());
                break;
            case THIRD_DERIVATIVE:
                Polynomial j = new Polynomial(gui, getFirstDerivative());
                p = new Polynomial(gui, j.getSecondDerivative());
                break;
            default:
                p = this;
                break;
        }
        double[] coefficient = p.coefficients;
        int[] degree = p.degrees;
        double value = 0;
        for (int i = 0; i < coefficient.length; i++) {
            if (degree[i] < 0) {
                value += 0;
            } else if (degree[i] == 0) {
                value += coefficient[i];
            } else {
                value += coefficient[i] * Math.pow(x, degree[i]);
            }
        }
        return value;
    }
}