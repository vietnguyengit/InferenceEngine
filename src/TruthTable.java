/*
    Hoang Viet Nguyen (101272826)
    Introduction to AI: Assignment 02
*/

import java.util.*;

class TruthTable {

    private int count = 0;
    private boolean doesEntail = false;
    private KnowledgeBase kb;
    private Clause query;

    //default constructor
    TruthTable(KnowledgeBase kb, String alpha) {
        this.kb = kb;
        PropositionalSymbol querySymbol = new PropositionalSymbol(alpha);
        this.query = new Clause(querySymbol);
    }

    //TT-ENTAILS
    //param: kb - a sentence in PL; query - a sentence in PL
    private boolean entail(KnowledgeBase kb, Clause alpha) {
        List<PropositionalSymbol> symbols = new ArrayList<>();

        //store unique string value
        Set<String> uniqueIdentities = new LinkedHashSet<>();
        //get unique symbol description from the KB
        for (PropositionalSymbol symbol : kb.Symbols()) {
            uniqueIdentities.add(symbol.Description());
        }

        //initialise symbols: a list of the proposition symbols in KB and α
        for (String symbol : uniqueIdentities) {
            symbols.add(new PropositionalSymbol(symbol));
        }

        return ttCheckAll(kb, alpha, symbols, new Model());
    }

    //TT_CHECK_ALL
    private boolean ttCheckAll(KnowledgeBase kb, Clause alpha, List<PropositionalSymbol> symbols, Model model) {

        if (symbols.isEmpty()) {
            if (model.isKBTrue(kb.joinedClauseKB())) {
                if (model.isClauseTrue(alpha)) {
                    count++;
                    doesEntail = model.isClauseTrue(alpha);
                }
                return doesEntail;
            } else {
                //if KB is false always return true
                return true;
            }
        }

        PropositionalSymbol p = symbols.get(0); //p
        List<PropositionalSymbol> rest = symbols.subList(1, symbols.size()); //rest

        return ttCheckAll(kb, alpha, rest, model.union(p, true))
                && ttCheckAll(kb, alpha, rest, model.union(p, false));
    }

    void Result() {
        //always return true when KB is false, so an additional condition is count > 0
        if (this.entail(kb, query) && count > 0) {
            System.out.println("YES: " + count);
        } else {
            System.out.println("NO");
        }
    }
}