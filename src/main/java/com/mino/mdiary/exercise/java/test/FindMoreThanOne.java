package com.mino.mdiary.exercise.java.test;

import java.util.*;

public class FindMoreThanOne {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ballCount = scanner.nextInt();
        int maxLength = scanner.nextInt();
        int colorCount = scanner.nextInt();
        int notMatchCount = 0;
        Map<Integer, List<Integer>> colorPositionMap = new HashMap<>();
        for (int i = 0; i < ballCount; ++i) {
            int eachBallColorCount= scanner.nextInt();
            for (int j = 0; j < eachBallColorCount; ++i) {
                int color = scanner.nextInt();
                colorPositionMap.computeIfAbsent(color, k -> new ArrayList<>());
                colorPositionMap.get(color).add(i);
            }
        }

        for (int i = 1; i <= colorCount; ++i) {
            boolean flag = false;
            List<Integer> positions = colorPositionMap.get(i);
            if (positions == null || positions.size() == 1) {
                continue;
            }
            for (int j = 0; j < positions.size() - 1; ++j) {
                if (positions.get(j+1) - positions.get(j) < maxLength) {
                    notMatchCount++;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                if (ballCount - positions.get(positions.size()-1) + positions.get(0) < maxLength) {
                    notMatchCount++;
                }
            }
        }

        System.out.println(notMatchCount);
    }

    private int sumNotMatch(List<List<Integer>> colors, int ballCount, int maxLength, int colorCount) {
        int sum = 0;
        for (int i = 0; i < ballCount; ++i) {
            for (int j = 1; j <= maxLength; ++j) {
                List<Integer> thisBall = colors.get(i);

            }
            colors.get(i);
        }

        return sum;
    }
}
