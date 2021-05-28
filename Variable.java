import java.util.*;
/**
 * A Variable node.
 *
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
 */
public class Variable extends Node
{
    // instance variables
    private String name;
    HashMap<String, String> symbols = new HashMap<String, String>();
    
    /**
     * Constructor for objects of class Variable.
     * @param var is a String which represents the variable's name.
     */
    public Variable(final String var){
        super();
        loadVariables();
        name = var;
        if (symbols.containsKey(var)) {
            name = symbols.get(var);
        }
    }

    private void loadVariables() {
        symbols.put("alpha","\\alpha");
        symbols.put("beta","\\beta");
        symbols.put("gamma","\\gamma");
        symbols.put("delta","\\delta");
        symbols.put("epsilon","\\epsilon");
        symbols.put("zeta","\\zeta");
        symbols.put("eta","\\eta");
        symbols.put("theta","\\theta");
        symbols.put("iota","\\iota");
        symbols.put("kappa","\\kappa");
        symbols.put("lambda","\\lambda");
        symbols.put("mu","\\mu");
        symbols.put("nu","\\nu");
        symbols.put("xi","\\xi");
        symbols.put("pi","\\pi");
        symbols.put("rho","\\rho");
        symbols.put("sigma","\\sigma");
        symbols.put("tau","\\tau");
        symbols.put("upsilon","\\upsilon");
        symbols.put("phi","\\phi");
        symbols.put("chi","\\chi");
        symbols.put("psi","\\psi");
        symbols.put("omega","\\omega");
        symbols.put("Alpha","A");
        symbols.put("Beta","B");
        symbols.put("Gamma","\\Gamma");
        symbols.put("Delta","\\Delta");
        symbols.put("Epsilon","E");
        symbols.put("Zeta","Z");
        symbols.put("Eta","H");
        symbols.put("Theta","\\Theta");
        symbols.put("Iota","I");
        symbols.put("Kappa","K");
        symbols.put("Lambda","\\Lambda");
        symbols.put("Mu","M");
        symbols.put("Nu","N");
        symbols.put("Xi","\\Xi");
        symbols.put("Pi","\\Pi");
        symbols.put("Rho","P");
        symbols.put("Sigma","\\Sigma");
        symbols.put("Tau","T");
        symbols.put("Upsilon","\\Upsilon");
        symbols.put("Phi","\\Phi");
        symbols.put("Chi","X");
        symbols.put("Psi","\\Psi");
        symbols.put("Omega","\\Omega");
        symbols.put("inf","\\infty");
        symbols.put("empty", "\\emptyset");
    }
    
    @Override
    // Returns Latex of Variable (replaces built in variables with latex)
    public String toString() {
        return "{" + name + "}";
    }
}
