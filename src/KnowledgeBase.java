/*
    Hoang Viet Nguyen (101272826)
    Introduction to AI: Assignment 02
*/

import java.util.ArrayList;
import java.util.List;

class KnowledgeBase {

    private List<Clause> clauses;
    private List<PropositionalSymbol> symbols = new ArrayList<>();
    private List<PropositionalSymbol> facts = new ArrayList<>();

    KnowledgeBase(List<Clause> percepts) {
        this.clauses = percepts;
    }

    List<Clause> joinedClauseKB() {
        List<Clause> joinedClauses = new ArrayList<>();
        for (int i = 0; i < clauses.size(); i++) {
            Clause newClause = clauses.get(i);
            if (i < clauses.size() - 1) {
                //Conjuction
                newClause.connect();
            }
            joinedClauses.add(newClause);
        }
        return joinedClauses;
    }

    //return the facts from the knowledge base
    List<PropositionalSymbol> Facts() {
        for (Clause clause : clauses) {
            if (clause.Facts() != null) {
                facts.add(clause.Facts());
            }
        }
        return facts;
    }

    //return all the propositional symbols in the knowledge base
    List<PropositionalSymbol> Symbols() {
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