import java.util.*;

class Model {

    private HashMap<String, Boolean> assignments = new HashMap<>();
    //default constructor
    Model() {}

    Model union(PropositionalSymbol symbol, boolean b) {
        Model m = new Model();

        m.assignments.putAll(this.assignments);
        m.assignments.put(symbol.Description(), b);

        return m;
    }

    private boolean isSymbolTrue(String symbol) {
        return Boolean.TRUE.equals(assignments.get(symbol));
    }

    // p1 & p2 => p3

    private boolean premiseValue;
    private boolean conclusionValue;

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

            //TO-DO
            //assign value for premiseValue
            if (premiseSymbolswithModel.size() == 2) {
                premiseValue = value(premiseSymbolswithModel.get(0), premiseSymbolswithModel.get(1));
            }
            if (premiseSymbolswithModel.size() == 1) {
                premiseValue = value(premiseSymbolswithModel.get(0), null);
            }

            //assign value for conclusionValue
            if (conclusionSymbolswithModel.size() == 2) {
                conclusionValue = value(conclusionSymbolswithModel.get(0), conclusionSymbolswithModel.get(1));
            }
            if (conclusionSymbolswithModel.size() == 1) {
                conclusionValue = value(conclusionSymbolswithModel.get(0), null);
            }

            result = !(premiseValue && !conclusionValue);
            clause.setValue(result);

            return result;
        }
    }

    private boolean value(PropositionalSymbol lhs, PropositionalSymbol rhs) {
        //only 2 kind of right connectives for the symbols in the premise
        //as this program was written to define the way the premise to be like that
        if (rhs == null) {
            return lhs.getValue();
        } else if (lhs.rightConnective() == Connective.Conjunction) {
            return lhs.getValue() && rhs.getValue();
        } else {
            return lhs.getValue() || rhs.getValue();
        }
    }

    //p1 & p2 => p3
    boolean isKBTrue(List<Clause> clauses) {

        for (Clause c : clauses) {
            this.isClauseTrue(c);
        }

        return clauses.get(0).getValue() && clauses.get(1).getValue() && clauses.get(2).getValue() && clauses.get(3).getValue() &&
        clauses.get(4).getValue() && clauses.get(5).getValue() && clauses.get(6).getValue() && clauses.get(7).getValue() && clauses.get(8).getValue() && clauses.get(9).getValue();
    }
}
