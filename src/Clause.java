import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

class Clause {

    private List<PropositionalSymbol> symbols;

    Clause(List<PropositionalSymbol> symbols) {
        this.symbols = symbols;
    }

    //the premise
    HashMap Premise() {
        HashMap<String, PropositionalSymbol> premise = new HashMap<>();
        for (PropositionalSymbol symbol : symbols) {
            if (!symbol.partofConclusion() && !symbol.isFact()) {
                premise.put(symbol.Description(), symbol);
            }
        }
        return premise;
    }

    List<PropositionalSymbol> PremiseForBC() {
        List<PropositionalSymbol> premise = new ArrayList<>();
        for (PropositionalSymbol symbol : symbols) {
            if (!symbol.partofConclusion() && !symbol.isFact()) {
                premise.add(symbol);
            }
        }
        return premise;
    }

    //the conclusion
    HashMap Conclusion() {
        HashMap<String, PropositionalSymbol> conclusion = new HashMap<>();
        for (PropositionalSymbol symbol : symbols) {
            if (symbol.partofConclusion()) {
                conclusion.put(symbol.Description(), symbol);
            }
        }
        return conclusion;
    }

    //the fact
    PropositionalSymbol Facts() {
        PropositionalSymbol fact = null;
        for (PropositionalSymbol symbol : symbols) {
            if (symbol.isFact()) {
                fact = symbol;
            }
        }
        return fact;
    }

    //all symbols in the clause
    List<PropositionalSymbol> Symbols() {
        return symbols;
    }
}