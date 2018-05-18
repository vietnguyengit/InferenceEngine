import java.util.ArrayList;
import java.util.List;

public class KnowledgeBase {

    private List<Clause> clauses;

    KnowledgeBase(List<Clause> percepts) {
        this.clauses = percepts;
    }

    //return the facts from the knowledge base
    List<PropositionalSymbol> Facts() {
        List<PropositionalSymbol> facts = new ArrayList<>();
        for (Clause clause : clauses) {
            if (clause.Facts() != null) {
                facts.add(clause.Facts());
            }
        }
        return facts;
    }

    //return all the propositional symbols in the knowledge base
    List<PropositionalSymbol> Symbols() {
        List<PropositionalSymbol> symbols = new ArrayList<>();
        for (Clause clause : clauses) {
            symbols.addAll(clause.Symbols());
        }
        return symbols;
    }

    //return all the clauses in the knowledge base
    List<Clause> Clauses() {
        return clauses;
    }
}
