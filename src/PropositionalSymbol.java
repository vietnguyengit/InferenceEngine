/*
    Hoang Viet Nguyen (101272826)
    Introduction to AI: Assignment 02
*/

class PropositionalSymbol {

    private String symbol;
    private boolean isFact = false;
    private boolean partofConclusion = false;
    private Connective leftConnective;
    private Connective rightConnective;
    private boolean value;

    //a propositional symbol holds either true or false value
    //constructor with value
    PropositionalSymbol(String symbol) {
        //True and False symbols can only be created once
        if (symbol.contains("~")) {
            this.symbol = symbol.toLowerCase().replace("~", "");
            this.leftConnective = Connective.Negation;
        } else {
            this.symbol = symbol.toLowerCase();
        }
    }

    boolean getValue() {
        return value;
    }

    void setValue(boolean value) {
        this.value = value;
    }

    String Description() {
        return symbol;
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