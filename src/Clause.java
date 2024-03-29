/*
    Hoang Viet Nguyen (101272826)
    Introduction to AI: Assignment 02
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Clause {

    private List<PropositionalSymbol> symbols = new ArrayList<>();
    private boolean value;

    private Connective rightConnective;

    Clause(List<PropositionalSymbol> symbols) {
        this.symbols = symbols;
    }
    Clause(PropositionalSymbol symbol) {
        this.symbols.add(symbol);
    }

    //To be used for connecting the clauses, used by TT method
    Connective rightConnective() {
        return this.rightConnective;
    }
    void connect() {
        this.rightConnective = Connective.Conjunction;
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

    //getter and setter to be used by TT
    boolean getValue() {
        return value;
    }
    void setValue(boolean value) {
        this.value = value;
    }

    //List of symbols used by BC method
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