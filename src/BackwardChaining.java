/*
    Hoang Viet Nguyen (101272826)
    Introduction to AI: Assignment 02
*/

import java.util.*;

//This class was written using the pseudo code from Figure 7.15 (page 258) AI - A Modern Approach 3rd edition
class BackwardChaining {

    private boolean doesEntail;
    private Set<String> entailed = new LinkedHashSet<>();

    BackwardChaining(KnowledgeBase kb, String query) {
        List<Clause> clauses = kb.Clauses();

        //initialise count, agenda & inferred
        //count: "a table, where count[c] is the number of symbols in c’s premise"
        HashMap<Clause, Integer> count = new HashMap<>();
        for (Clause clause : clauses) {
            count.put(clause, clause.Conclusion().size());
        }

        //inferred: "a table, where inferred[s] is initially false for all symbols"
        HashMap<PropositionalSymbol, Boolean> inferred = new HashMap<>();

        //agenda
        List<PropositionalSymbol> agenda = new ArrayList<>();

        for (PropositionalSymbol symbol : kb.Symbols()) {
            inferred.put(symbol, false);
            if (symbol.Description().equals(query)) {
                agenda.add(symbol);
            }
        }

        while(!agenda.isEmpty()) {
            //pop (stack LIFO)
            PropositionalSymbol p = agenda.get(agenda.size() - 1);
            agenda.remove(agenda.size() - 1);

            for (PropositionalSymbol symbol : kb.Facts()) {
                if (p.Description().equals(symbol.Description())) {
                    doesEntail = true;
                    break;
                }
            }

            if (!inferred.get(p)) {
                inferred.put(p, true);

                //add the symbol to the entailed string
                entailed.add(p.Description());

                for (Clause clause : clauses) {
                    if (clause.Conclusion().containsKey(p.Description())) {
                        count.put(clause, count.get(clause) - 1);
                        if (count.get(clause) == 0) {
                            agenda.add(clause.PremiseForBC().get(0));
                        }
                    }
                }
            }
        }
    }

    void Result() {
        String printResult;
        if (doesEntail) {
            List<String> result = new ArrayList<>(entailed);
            Collections.reverse(result);
            String entailedList = Arrays.toString(result.toArray()).replaceAll("\\[|\\]", "");
            printResult = "YES: " + entailedList;
        } else {
            printResult = "NO";
        }
        System.out.println(printResult);
    }
}