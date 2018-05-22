/*
    Hoang Viet Nguyen (101272826)
    Introduction to AI: Assignment 02
*/

import java.util.ArrayList;
import java.util.List;

enum Connective {
    //a biconditional sentence are automatically converted into Implication sentences
    //e.g. p1 <=> p2: p1 => p2; p2 => p1
    Negation, Conjunction, Disjunction, Implication, Biconditional
}

class SymbolParser {
    //clause: symbol, connective
    private String clause;
    private boolean isUnary = false;
    private boolean isBinary = false;

    private List<PropositionalSymbol> symbols = new ArrayList<>();

    SymbolParser(String clause) {
        this.clause = clause;
        //check if the clause is unary or binary
        if (this.clause.contains("=>")) {
            this.isBinary = true;
        } else {
            this.isUnary = true;
        }
        this.rawClauseParser();
    }

    //extract the original clause passed through param
    private void rawClauseParser() {
        //sentences
        if (isBinary) {
            String[] temp = clause.split("=>");

            //premise
            String body = temp[0];
            //and-or for premise
            String[] tokens = body.split("&|\\|");
            //Initially false of all the symbol (body)
            for (int i = 0; i < tokens.length; i++) {
                PropositionalSymbol newSymbol = new PropositionalSymbol(tokens[i]);
                //assign available connective on the right of the symbol
                if (i < connectiveSplitter(body).size()) {
                    newSymbol.setRightConnective(connectiveSplitter(body).get(i));
                }
                //last element of the premise has connective => on the right
                if (i == tokens.length - 1) {
                    newSymbol.setRightConnective(Connective.Implication);
                }
                symbols.add(newSymbol);
            }

            //conclusion
            String head = temp[1];
            //and-or for conclusion
            tokens = head.split("&|\\|");
            //Initially false of all the symbol (head)
            for (int i = 0; i < tokens.length; i++) {
                PropositionalSymbol newSymbol = new PropositionalSymbol(tokens[i]);
                newSymbol.partofConclusion(true);
                //assign available connective on the right of the symbol
                if (i < connectiveSplitter(head).size()) {
                    newSymbol.setRightConnective(connectiveSplitter(head).get(i));
                }
                symbols.add(newSymbol);
            }
        }

        //fact
        if (isUnary) {
            String head = clause;
            //initially symbol known to be true in the KB
            PropositionalSymbol newSymbol = new PropositionalSymbol(head);
            //this symbol is a fact
            newSymbol.setFact(true);
            symbols.add(newSymbol);
        }
    }

    //split the premise and conclusion to multiple symbols with their connectives
    private List<Connective> connectiveSplitter(String input) {
        List<Connective> connectives = new ArrayList<>();
        //parsing available connectives in the premise and conclusion
        String rawConnectives = input.replaceAll("[a-zA-Z0-9]", "");
        String[] splitConnectives = rawConnectives.split("");
        for (String rawConnective : splitConnectives) {
            Connective connective;
            switch(rawConnective) {
                case "&":
                    connective = Connective.Conjunction;
                    break;
                case "|":
                    connective = Connective.Disjunction;
                    break;
                default:
                    connective = null;
                    break;
            }
            connectives.add(connective);
        }
        return connectives;
    }

    //raw clause
    String Clause() {
        return clause;
    }

    //extracted symbols from the raw clause
    List<PropositionalSymbol> Symbols() {
        return symbols;
    }
}
