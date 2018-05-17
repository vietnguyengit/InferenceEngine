public class Clause {

    private PropositionalSymbol leftSymbol, rightSymbol;
    private Connective connective;

    //e.g p1&p2
    Clause(PropositionalSymbol lhs, Connective connective, PropositionalSymbol rhs) {
        this.leftSymbol = lhs;
        this.rightSymbol = rhs;
        this.connective = connective;
    }

    //e.g. ~p1
    //e.g. =>p1 - conclusion
    Clause(Connective connective, PropositionalSymbol symbol) {
        this.connective = connective;
        this.rightSymbol = symbol;
    }
}
