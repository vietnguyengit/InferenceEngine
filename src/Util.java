/*
    Hoang Viet Nguyen (101272826)
    Introduction to AI: Assignment 02
*/

import java.util.List;

class Util {

    //recursive function to check if the clause is true or false
    boolean clauseValue(List<PropositionalSymbol> symbols) {
        boolean a = symbols.get(0).getValue();
        Connective connective = symbols.get(0).rightConnective();

        //if the clause has only just one symbol. e.g: p1
        if (connective == null) {
            return a;
        }
        //if the clause has more than one symbol. e.g: p1 & p2; p1 \/ p2
        while (symbols.size() > 1) {
            symbols.remove(0);
            boolean b = symbols.get(0).getValue();
            //only 2 kind of right connectives for the symbols in the premise
            //as this program was written to define the way the premise to be like that
            if (connective == Connective.Conjunction) {
                a = a && b;
            } else {
                a = a || b;
            }
        }
        return a;
    }

    //recursive method to be used by isKBTrue, looping between the element clauses to get the final boolean value
    boolean kbValue(List<Clause> clauses) {
        //initialise first necessary elements for returning final value
        boolean a = clauses.get(0).getValue();
        Connective connective = clauses.get(0).rightConnective();

        //if KB only has 1 clause, return that clause value instead of jumping to the recursive loop
        if (connective == null) {
            return a;
        }

        while (clauses.size() > 1) {
            clauses.remove(0);
            boolean b = clauses.get(0).getValue();
            //KB in this case is a combination of clauses, each connected by conjuction connective
            a = a && b;
        }
        return a;
    }

}
