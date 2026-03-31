package com.adventofcode.aoc2025;

import java.util.ArrayList;
import java.util.List;

public final class Day06 extends Day {

    public Day06() {
        super("day06");
    }

    @Override
    protected Answers solve(String input) {
        return new Answers(grandTotal(input), null);
    }

    static long grandTotal(String input) {
        List<String> lines = unwrapWorksheet(List.of(input.split("\\R")));

        List<String> numberLines = lines.subList(0, lines.size() - 1);
        String[] operatorTokens = lines.getLast().trim().split("\\s+");
        boolean[] isAddition = new boolean[operatorTokens.length];
        long[] problemAnswers = new long[operatorTokens.length];
        for (int i = 0; i < operatorTokens.length; i++) {
            isAddition[i] = operatorTokens[i].charAt(0) == '+';
            problemAnswers[i] = isAddition[i] ? 0L : 1L;
        }

        for (String numberLine : numberLines) {
            String[] rowNumbers = numberLine.trim().split("\\s+");
            for (int i = 0; i < problemAnswers.length; i++) {
                long number = Long.parseLong(rowNumbers[i]);
                problemAnswers[i] = isAddition[i]
                        ? Math.addExact(problemAnswers[i], number)
                        : Math.multiplyExact(problemAnswers[i], number);
            }
        }

        long grandTotal = 0L;
        for (long problemAnswer : problemAnswers) {
            grandTotal = Math.addExact(grandTotal, problemAnswer);
        }
        return grandTotal;
    }

    private static List<String> unwrapWorksheet(List<String> lines) {
        List<Integer> operatorLines = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            if (isOperatorLine(lines.get(i))) operatorLines.add(i);
        }
        if (operatorLines.size() == 1) return lines;

        int blockSize = operatorLines.getFirst() + 1;
        List<List<String>> blocks = new ArrayList<>(operatorLines.size());

        int start = 0;
        for (int operatorIndex : operatorLines) {
            blocks.add(lines.subList(start, operatorIndex + 1));
            start = operatorIndex + 1;
        }

        List<String> unrolled = new ArrayList<>(blockSize);
        for (int row = 0; row < blockSize; row++) {
            StringBuilder lineBuilder = new StringBuilder();
            for (int i = 0; i < blocks.size(); i++) {
                lineBuilder.append(blocks.get(i).get(row));
                if (i != blocks.size() - 1) lineBuilder.append(' ');
            }
            unrolled.add(lineBuilder.toString());
        }
        return unrolled;
    }

    private static boolean isOperatorLine(String line) {
        boolean hasOperator = false;
        for (int i = 0; i < line.length(); i++) {
            char character = line.charAt(i);
            if (character == '+' || character == '*') {
                hasOperator = true;
            } else if (character != ' ') {
                return false;
            }
        }
        return hasOperator;
    }
}
