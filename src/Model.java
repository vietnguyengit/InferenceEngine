import java.util.*;

class Model extends Util {

    private HashMap<String, Boolean> assignments = new HashMap<>();
    //default constructor
    Model() {}

    Model union(PropositionalSymbol symbol, boolean b) {
        Model m = new Model();
        m.assignments.putAll(this.assignments);
        m.assignments.put(symbol.Description(), b);
        return m;
    }

    //check if a symbol is true or not using the initialised map
    private boolean isSymbolTrue(String symbol) {
        return Boolean.TRUE.equals(assignments.get(symbol));
    }

    boolean isClauseTrue(Clause clause) {
        //check if the param clause is unary clause or not
        boolean isUnary = false;
        if (clause.Symbols().size() == 1) {
            isUnary = true;
        }

        //case clause is unary
        boolean result;
        if (isUnary) {
            PropositionalSymbol symbol = clause.Symbols().get(0);

            //case the symbol is not negated
            if (symbol.leftConnective() == null) {
                result = this.isSymbolTrue(symbol.Description());
            } else {
                result = !this.isSymbolTrue(symbol.Description());
            }

            //set value for the clause, it will be used for checking KB value
            clause.setValue(result);
            return result;

        } else {
            //case clause includes multiple symbols
            List<PropositionalSymbol> premiseSymbolswithModel = new ArrayList<>();
            List<PropositionalSymbol> conclusionSymbolswithModel = new ArrayList<>();

            for (int i = 0; i < clause.Symbols().size(); i++) {
                PropositionalSymbol symbol = clause.Symbols().get(i);
                //the clause's premise
                if (!symbol.partofConclusion()) {
                    //set value for each symbol of the premise of the clause in the model
                    if (symbol.leftConnective() == null) {
                        symbol.setValue(this.isSymbolTrue(symbol.Description()));
                    } else {
                        symbol.setValue(!this.isSymbolTrue(symbol.Description()));
                    }
                    //set connective for each symbol of the premise of the clause in the model
                    if (symbol.rightConnective() != Connective.Implication) {
                        symbol.setRightConnective(symbol.rightConnective());
                    } else {
                        symbol.setRightConnective(null);
                    }
                    //add the new symbol to the list
                    premiseSymbolswithModel.add(symbol);
                } else {
                    if (symbol.leftConnective() == null) {
                        symbol.setValue(this.isSymbolTrue(symbol.Description()));
                    } else {
                        symbol.setValue(!this.isSymbolTrue(symbol.Description()));
                    }
                    if (symbol.rightConnective() != null) {
                        symbol.setRightConnective(symbol.rightConnective());
                    }
                    conclusionSymbolswithModel.add(symbol);
                }
            }

            //assign value for premiseValue
            boolean premiseValue = clauseValue(premiseSymbolswithModel);
            //assign value for conclusionValue
            boolean conclusionValue = clauseValue(conclusionSymbolswithModel);

            //get the binary clause (implication) value whether it is true or false
            //in terms of binary clause, only implication clause available
            //biconditional clauses were automatically converted into implication clauses at the beginning
            //e.g. a <=> b : a => b; b => a
            result = !(premiseValue && !conclusionValue);

            //set value for the clause, it will be used for checking KB value
            clause.setValue(result);

            return result;
        }
    }

    //check if the KB is true of false
    boolean isKBTrue(List<Clause> clauses) {
        //initialise value for each clause before able to return the whole KB value whether true or false
        for (Clause c : clauses) {
            this.isClauseTrue(c);
        }
        return kbValue(clauses);
    }
}
