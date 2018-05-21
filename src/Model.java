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
    boolean isClauseTrue(Clause clause) {

        //check if the param clause is unary clause or not
        boolean isUnary = false;
        if (clause.Symbols().size() == 1) {
            isUnary = true;
        }

        //case clause is unary
        if (isUnary) {
            PropositionalSymbol symbol = clause.Symbols().get(0);

            //case the symbol is not negated
            if (symbol.leftConnective() == null) {
                return this.isSymbolTrue(symbol.Description());
            } else {
                return !this.isSymbolTrue(symbol.Description());
            }

        } else {
            //list true/false value of symbols from the clause's premise
            List<Boolean> premiseValues = new ArrayList<>();
            List<Connective> premiseConnectives = new ArrayList<>();
            //boolean value of the clause's premise
            boolean premiseValue;

            //list true/false value of symbols from the clause's conclusion
            List<Boolean> conclusionValues = new ArrayList<>();
            List<Connective> conclusionConnectives = new ArrayList<>();
            //boolean value of the clause's conclusion
            boolean conclusionValue;

            //case clause includes multiple symbols

            for (int i = 0; i < clause.Symbols().size(); i++) {

                PropositionalSymbol symbol = clause.Symbols().get(i);
                //the clause's premise
                if (!symbol.partofConclusion()) {
                    //case the symbol is not negated
                    if (symbol.leftConnective() == null) {
                        premiseValues.add(this.isSymbolTrue(symbol.Description()));
                    } else {
                        premiseValues.add(!this.isSymbolTrue(symbol.Description()));
                    }
                    //initialise list of connectives between symbols in the premise
                    if (symbol.rightConnective() != Connective.Implication) {
                        premiseConnectives.add(symbol.rightConnective());
                    }

                } else { //the clause's conclusion
                    //case the symbol is not negated
                    if (symbol.leftConnective() == null) {
                        conclusionValues.add(this.isSymbolTrue(symbol.Description()));
                    } else {
                        conclusionValues.add(!this.isSymbolTrue(symbol.Description()));
                    }
                    //initialise list of connectives between symbols in the conclusion
                    if (symbol.rightConnective() != null) {
                        conclusionConnectives.add(symbol.rightConnective());
                    }
                }
            }

            //assign value for premiseValue
            premiseValue = premiseValues.get(0);

            //assign value for conclusionValue
            conclusionValue = conclusionValues.get(0);

            return !(premiseValue && !conclusionValue);
        }
    }

    //p1 & p2 => p3
    boolean isKBTrue(List<Clause> clauses) {

        List<Boolean> values = new ArrayList<>();

        for (Clause clause : clauses) {
            values.add(this.isClauseTrue(clause));
        }

        return  values.get(0);
    }
}
