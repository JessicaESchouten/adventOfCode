package com.adventofcode.aoc2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Day06 extends Day {

    public Day06() {
        super("day06");
    }

    @Override
    protected Answers solve(String input) {
        List<String> lines = unwrapWorksheet(List.of(input.split("\\R")));
        return new Answers(grandTotalPart1(lines), grandTotalPart2(lines));
    }

    static long grandTotalPart1(List<String> lines) {
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

    static long grandTotalPart2(List<String> lines) {
        List<String> numberLines = lines.subList(0, lines.size() - 1);
        String operatorLine = lines.getLast();

        int maxLen = 0;
        for (String line : lines) {
            if (line.length() > maxLen) maxLen = line.length();
        }

        boolean[] isBlankColumn = new boolean[maxLen];
        Arrays.fill(isBlankColumn, true);
        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) != ' ') isBlankColumn[i] = false;
            }
        }

        long[] columnNumbers = new long[maxLen];
        boolean[] hasDigitColumn = new boolean[maxLen];
        for (String numberLine : numberLines) {
            for (int i = 0; i < numberLine.length(); i++) {
                char character = numberLine.charAt(i);
                if (character == ' ') continue;
                hasDigitColumn[i] = true;
                columnNumbers[i] = columnNumbers[i] * 10 + (character - '0');
            }
        }

        long grandTotal = 0L;
        int col = 0;
        while (col < maxLen) {
            while (col < maxLen && isBlankColumn[col]) col++;
            if (col >= maxLen) break;

            int startColumnInclusive = col;
            while (col < maxLen && !isBlankColumn[col]) col++;
            int endColumnExclusive = col;

            boolean isAddition = readOperator(operatorLine, startColumnInclusive, endColumnExclusive) == '+';
            long problemAnswer = isAddition ? 0L : 1L;

            for (int digitColumn = endColumnExclusive - 1; digitColumn >= startColumnInclusive; digitColumn--) {
                if (!hasDigitColumn[digitColumn]) continue;
                long number = columnNumbers[digitColumn];
                problemAnswer = isAddition ? Math.addExact(problemAnswer, number) : Math.multiplyExact(problemAnswer, number);
            }

            grandTotal = Math.addExact(grandTotal, problemAnswer);
        }

        return grandTotal;
    }

    private static List<String> unwrapWorksheet(List<String> lines) {
        int firstOperatorLineIndex = -1;
        int operatorLineCount = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.indexOf('+') < 0 && line.indexOf('*') < 0) continue;
            operatorLineCount++;
            if (firstOperatorLineIndex < 0) firstOperatorLineIndex = i;
        }
        if (operatorLineCount <= 1) return lines;

        int blockSize = firstOperatorLineIndex + 1;
        List<String> unrolled = new ArrayList<>(blockSize);
        for (int row = 0; row < blockSize; row++) {
            StringBuilder lineBuilder = new StringBuilder();
            for (int block = 0; block < operatorLineCount; block++) {
                if (block != 0) lineBuilder.append(' ');
                lineBuilder.append(lines.get(block * blockSize + row));
            }
            unrolled.add(lineBuilder.toString());
        }
        return unrolled;
    }

    private static char readOperator(String operatorLine, int startColumnInclusive, int endColumnExclusive) {
        int operatorLimit = Math.min(endColumnExclusive, operatorLine.length());
        for (int col = startColumnInclusive; col < operatorLimit; col++) {
            char character = operatorLine.charAt(col);
            if (character == '+' || character == '*') return character;
        }
        return '+';
    }
}
