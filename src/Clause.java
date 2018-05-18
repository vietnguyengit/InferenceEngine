import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

class Clause {

    private List<PropositionalSymbol> symbols;

    Clause(List<PropositionalSymbol> symbols) {
        this.symbols = symbols;
    }

    //the premise
    Hashtable Premise() {
        Hashtable<PropositionalSymbol, String> premise = new Hashtable<>();
        for (PropositionalSymbol symbol : symbols) {
            if (!symbol.partofConclusion() && !symbol.isFact()) {
                premise.put(symbol, symbol.Description());
            }
        }
        return premise;
    }

    //the conclusion
    List<PropositionalSymbol> Conclusion() {
        List<PropositionalSymbol> conclusion = new ArrayList<>();
        for (PropositionalSymbol symbol : symbols) {
            if (symbol.partofConclusion()) {
                conclusion.add(symbol);
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