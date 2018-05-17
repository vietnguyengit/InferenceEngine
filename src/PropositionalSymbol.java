class PropositionalSymbol {
    private String symbol;
    private boolean value;
    private boolean isPositive;
    private Connective connective;

    //a propositional symbol holds either true or false value
    //constructor with value
    PropositionalSymbol(String symbol, boolean value) {
        //True and False symbols can only be created once
        this.symbol = symbol.toLowerCase();
        this.value = value;
    }

    //constructor without value
    //all symbols should be initialised with false value
    PropositionalSymbol(String symbol) {
        this(symbol, false);
    }

    void setConnective(Connective connective) {
        this.connective = connective;
    }

    Connective Connective() {
        return connective;
    }

    void setPositive(boolean isPositive) {
        this.isPositive = isPositive;
    }

    boolean isPositive() {
        return isPositive;
    }

    boolean isNegative() {
        return !isPositive;
    }

    boolean isTrue() {
        return this.value;
    }

    boolean isFalse() {
        return !this.value;
    }

    String Description() {
        return symbol;
    }
}