class PropositionalSymbol {
    private String symbol;
    private boolean value;
    private boolean isPositive;
    private boolean isFact = false;
    private boolean partofConclusion = false;
    private Connective leftConnective;
    private Connective rightConnective;

    //a propositional symbol holds either true or false value
    //constructor with value
    PropositionalSymbol(String symbol, boolean value) {
        //True and False symbols can only be created once
        if (symbol.contains("~")) {
            this.symbol = symbol.toLowerCase().replace("~", "");
            this.leftConnective = Connective.Negation;
            this.isPositive = false;
        } else {
            this.symbol = symbol.toLowerCase();
            this.isPositive = true;
        }

        this.value = value;
    }

    //constructor without value
    //all symbols should be initialised with false value
    PropositionalSymbol(String symbol) {
        this(symbol, false);
    }

    String Description() {
        return symbol;
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
    void setValue(boolean value) {
        this.value = value;
    }

    boolean partofConclusion() {
        return this.partofConclusion;
    }
    void partofConclusion(boolean value) { this.partofConclusion = value; }

    boolean isFact() { return isFact; }
    void setFact(boolean value) { this.isFact = value; }

    Connective leftConnective() {
        return leftConnective;
    }

    Connective rightConnective() {
        return rightConnective;
    }
    void setRightConnective(Connective value) {
        this.rightConnective = value;
    }
}