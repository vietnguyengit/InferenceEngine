import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //variable to hold any available error messages
        List<String> errorMessages = new ArrayList<>();

        //if the syntax is incorrect
        if (args.length != 2) {
            String[] usageMessages = { "Incorrect console command",
                    "Usage: iengine <algorithm keyword> <filename with extension>",
                    "Sample: iengine FC file.txt",
                    "Available algorithms: TT, FC, BC"
            };

            errorMessages.addAll(Arrays.asList(usageMessages));
        } else {
            //validate algorithm keyword
            switch (args[0]) {
                case "TT":
                    break;
                case "FC":
                    break;
                case "BC":
                    break;
                default:
                    errorMessages.add("Incorrect algorithm keyword. Available algorithms: TT (TruthTable), " +
                            "FC (ForwardChaining), " +
                            "BC (BackwardChaining)");
                    break;
            }

            //validate file name with extension, only allow txt file.
            if (!args[1].matches("^[a-zA-Z0-9.-_]*\\.txt$")) {
                errorMessages.add("Incorrect file name. File name should be under the format: *.txt");
                errorMessages.add("Where * is the actual file name. Only alphabet, digit, - , _ and . characters are allowed.");
            }
        }

        //print error if available
        if (!errorMessages.isEmpty()) {
            for (String errorMessage : errorMessages) {
                System.out.println(errorMessage);
            }
        } else {

            FileParser parseFile = new FileParser(args[1]);

            //used as param for creating KB
            if (!parseFile.rawClauses().isEmpty()) {
                for (String rawClause : parseFile.rawClauses()) {
                    SymbolParser symbolParser = new SymbolParser(rawClause);
                    for (PropositionalSymbol symbol: symbolParser.Symbols()) {
                        System.out.println(symbolParser.Clause() + " : " + symbol.Description() + " " + symbol.isTrue() + " " + symbol.isPositive());
                    }
                }
            }

        }
    }
}