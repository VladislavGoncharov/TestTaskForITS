package com.vladislavgoncharov.testtaskforits.main_package;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class SearchSubstring {

    private String firstArrayString;
    private String secondArrayString;
    private String resultArrayString;


    public SearchSubstring() {
    }

    public SearchSubstring(StringBuilder firstArrayString, StringBuilder secondArrayString, StringBuilder resultArrayString) {
        this.firstArrayString = String.valueOf(firstArrayString);
        this.secondArrayString = String.valueOf(secondArrayString);
        this.resultArrayString = String.valueOf(resultArrayString);
    }

    public void getResult() {
        String[] firstArray = firstArrayString.split(" ");

        resultArrayString = (Arrays.stream(firstArray).distinct()
                .filter(word -> secondArrayString.contains(word))
                .sorted().reduce((accumulator, word) -> accumulator + " " + word + " ")).get();
    }


    public static SearchSubstring createObjectFromFile(List<String> futureObjectFromFile) throws Exception {
        StringBuilder firstArray = new StringBuilder(),
                        secondArray = new StringBuilder(),
                         resultArray = new StringBuilder();
        int switchArrays = 0;

        for (String line : futureObjectFromFile) {

            if (    "Search substring".equals(line.trim()) ||
                    "//----------//".equals(line.trim()) ||
                    "//------------/------------//".equals(line.trim())) {
                switchArrays++;
                continue;
            }

            if (switchArrays == 1) firstArray.append(line);
            else if (switchArrays == 2) secondArray.append(line);
            else resultArray.append(line);
        }

        if (switchArrays <= 2) throw new Exception("File error");

        return new SearchSubstring(firstArray,secondArray,resultArray);
    }

    public String getFirstArrayString() {
        return firstArrayString;
    }

    public void setFirstArrayString(String firstArrayString) {
        this.firstArrayString = firstArrayString;
    }

    public String getSecondArrayString() {
        return secondArrayString;
    }

    public void setSecondArrayString(String secondArrayString) {
        this.secondArrayString = secondArrayString;
    }

    public String getResultArrayString() {
        return resultArrayString;
    }

    public void setResultArrayString(String resultArrayString) {
        this.resultArrayString = resultArrayString;
    }
}