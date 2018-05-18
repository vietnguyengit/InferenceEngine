import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ForwardChaining {

    private String printResult;
    private List<String> entailed = new ArrayList<>();

    ForwardChaining(KnowledgeBase kb, String query) {
        boolean doesEntail = false;

        int count;
        List<Clause> clauses = kb.Clauses();
        List<PropositionalSymbol> agenda = kb.Facts();
        List<PropositionalSymbol> inferred = new ArrayList<>();

        for (PropositionalSymbol symbol : kb.Symbols()) {
            symbol.setValue(false);
            inferred.add(symbol);
        }

        while(!agenda.isEmpty()) {
            //pop
            PropositionalSymbol p = agenda.get(0);
            agenda.remove(0);

            if (p.Description().equals(query)) {
                doesEntail = true;
                entailed.add(p.Description());
                break;
            }

            int index = inferred.indexOf(p);
            if (inferred.get(index).isFalse()) {
                inferred.get(index).setValue(true);
                entailed.add(p.Description());
                for (Clause clause : clauses) {
                    count = clause.Premise().size();
                    if (clause.Premise().contains(p.Description())) {
                        --count;
                        if (count == 0) {
                            agenda.addAll(clause.Conclusion());
                        }
                    }
                }
            }
        }

        printResult = doesEntail ? "YES" : "NO";
    }

    void Result() {
        System.out.print(printResult + ": ");
        String entailedList = Arrays.toString(entailed.toArray()).replaceAll("\\[|\\]", "");
        System.out.println(entailedList);
    }
}