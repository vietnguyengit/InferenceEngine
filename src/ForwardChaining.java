/*
    Hoang Viet Nguyen (101272826)
    Introduction to AI: Assignment 02
*/

import java.util.*;

//This class was written using the pseudo code from Figure 7.15 (page 258) AI - A Modern Approach 3rd edition
class ForwardChaining {

    private Set<String> entailed = new LinkedHashSet<>();
    private boolean doesEntail;

    ForwardChaining(KnowledgeBase kb, String query) {
        List<Clause> clauses = kb.Clauses();

        //initialise count, agenda & inferred
        //count: "a table, where count[c] is the number of symbols in c’s premise"
        HashMap<Clause, Integer> count = new HashMap<>();
        for (Clause clause : clauses) {
            count.put(clause, clause.Premise().size());
        }

        //inferred: "a table, where inferred[s] is initially false for all symbols"
        HashMap<PropositionalSymbol, Boolean> inferred = new HashMap<>();
        for (PropositionalSymbol symbol : kb.Symbols()) {
            inferred.put(symbol, false);
        }

        //agenda: "a queue of symbols, initially symbols known to be true in KB"
        List<PropositionalSymbol> agenda = kb.Facts();


        //p => q; l&m => p; b&l => m; a&p=>l; a&b => l; [a; b]
        while(!agenda.isEmpty()) {
            //pop (queue FIFO)
            PropositionalSymbol p = agenda.get(0);
            agenda.remove(0);

            if (p.Description().equals(query)) {
                doesEntail = true;
                entailed.add(p.Description());
                break;
            }

            if (!inferred.get(p)) {

                inferred.put(p, true);

                //add the symbol to the entailed string
                entailed.add(p.Description());

                for (Clause clause : clauses) {
                    if (clause.Premise().containsKey(p.Description())) {
                        count.put(clause, count.get(clause) - 1);
                        if (count.get(clause) == 0) {
                            for (Object value : clause.Conclusion().values()) {
                                agenda.add((PropositionalSymbol) value);
                            }
                        }
                    }
                }
            }
        }
    }

    void Result() {
        String printResult;
        if (doesEntail) {
            String entailedList = Arrays.toString(entailed.toArray()).replaceAll("\\[|\\]", "");
            printResult = "YES: " + entailedList;
        } else {
            printResult = "NO";
        }
        System.out.println(printResult);
    }
}