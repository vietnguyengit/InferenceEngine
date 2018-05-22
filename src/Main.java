/*
    Hoang Viet Nguyen (101272826)
    Introduction to AI: Assignment 02
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum Method {
    TT, FC, BC
}

public class Main {

    private static Method method;

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
                    method = Method.TT;
                    break;
                case "FC":
                    method = Method.FC;
                    break;
                case "BC":
                    method = Method.BC;
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

        //print error related to input command if available
        if (!errorMessages.isEmpty()) {
            for (String errorMessage : errorMessages) {
                System.out.println(errorMessage);
            }
        } else {

            FileParser parseFile = new FileParser(args[1]);
            //input command has no errors, but now check if any error when reading file
            if (!parseFile.error()) {
                //used as param for creating KB
                List<String> preparedRawClauses = new ArrayList<>();
                if (!parseFile.rawClauses().isEmpty()) {
                    for (String rawClause : parseFile.rawClauses()) {
                        //reconstruct biconditional clause into 2 implication clause
                        if (rawClause.contains("<=>")) {
                            String[] temp = rawClause.split("<=>");
                            String premise = temp[0];
                            String conclusion = temp[1];

                            String forwardClause = premise + "=>" + conclusion;
                            String backwardClause = conclusion + "=>" + premise;

                            preparedRawClauses.addAll(Arrays.asList(forwardClause, backwardClause));
                        } else {
                            preparedRawClauses.add(rawClause);
                        }
                    }
                }

                List<Clause> clauses = new ArrayList<>();
                for (String preparedRawClause : preparedRawClauses) {
                    SymbolParser symbolParser = new SymbolParser(preparedRawClause);
                    Clause newClause = new Clause(symbolParser.Symbols());
                    clauses.add(newClause);
                }

                //System.out.println("Author: Hoang Viet Nguyen");
                //System.out.println("-------------------------");

                KnowledgeBase kb = new KnowledgeBase(clauses);

                switch (method) {
                    case FC:
                        ForwardChaining fc = new ForwardChaining(kb, parseFile.Query());
                        fc.Result();
                        break;
                    case BC:
                        BackwardChaining bc = new BackwardChaining(kb, parseFile.Query());
                        bc.Result();
                        break;
                    case TT:
                        TruthTable tt = new TruthTable(kb, parseFile.Query());
                        tt.Result();
                        break;
                }
            }
        }
    }
}