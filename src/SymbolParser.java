import java.util.ArrayList;
import java.util.List;

class SymbolParser {

    //definite clause: disjunction with exactly 1 is positive
    //horn clause: disjunction at most 1 is positive
    //clause: symbol, connective
    private String clause;
    private boolean isUnary = false;
    private boolean isBinary = false;
    private List<PropositionalSymbol> symbols = new ArrayList<>();

    SymbolParser(String clause) {

        this.clause = clause;

        //check if the clause is unary or binary
        int indexCheckA = this.clause.indexOf("=>");
        int indexCheckB = this.clause.indexOf("<=>");
        if (indexCheckA >= 0 || indexCheckB >= 0) {
            this.isBinary = true;
        } else {
            this.isUnary = true;
        }

        this.rawClauseParser();
    }

    //extract the original clause passed through param
    private void rawClauseParser() {

        if (isBinary) {

            String[] temp = clause.split("=>|<=>");
            String body = temp[0];

            //and-or
            String[] tokens = body.split("&|\\|");

            //Initially false of all the symbol (body)
            for (String token : tokens) {
                symbols.add(new PropositionalSymbol(token));
            }

            String head = temp[1];
            //Initially false of all the symbol (head)
            symbols.add(new PropositionalSymbol(head));
        }

        if (isUnary) {
            String head = clause;
            //initially symbol known to be true in the KB
            symbols.add(new PropositionalSymbol(head, true));
        }

        //if a symbol has negation notation -> it's negative
        for (PropositionalSymbol symbol : symbols) {
            if (symbol.Description().contains("~")) {
                symbol.setConnective(Connective.Negation);
                symbol.setPositive(false);
            } else {
                symbol.setPositive(true);
            }
        }
    }

    String Clause() {
        return clause;
    }

    List<PropositionalSymbol> Symbols() {
        return symbols;
    }
}
