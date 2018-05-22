/*
    Hoang Viet Nguyen (101272826)
    Introduction to AI: Assignment 02
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FileParser {

    private List<String> file_read_lines = new ArrayList<>();
    private List<String> rawClauses = new ArrayList<>();
    private String query;
    private File source_file;
    private boolean error = false;

    //constructor
    FileParser(String file_path) {
        source_file = new File(file_path);
        this.extractFile();
    }

    private void extractFile() {
        //Read and process inputs from file
        try (BufferedReader buffered_reader = new BufferedReader(new FileReader(source_file))) {
            String line;
            while((line = buffered_reader.readLine()) != null) {
                String spaceEscaped = line.replace(" ", "");
                //file was read, each line is one of the elements of the file_read_lines list
                //line 0 is keyword "TELL"
                //line 1 is the knowledge base
                //line 2 is the keyword "ASK"
                //line 3 is the query
                file_read_lines.add(spaceEscaped);
            }

            //generate list of Horn clauses (raw) from the KB raw sentence
            //replace \/ by |
            String kbLine = file_read_lines.get(1).replace("\\/", "|");
            rawClauses = Arrays.asList(kbLine.split(";"));
            //query - a propositional symbol
            query = file_read_lines.get(3);
        } catch (IOException e) {
            //Return error if file cannot be opened
            error = true;
            System.out.println(source_file.toString() + " is not found!");
        }
    }

    List<String> rawClauses() {
        return rawClauses;
    }

    String Query() {
        return query;
    }

    //used by Main, to check if there is a file not found error
    boolean error() {
        return error;
    }
}