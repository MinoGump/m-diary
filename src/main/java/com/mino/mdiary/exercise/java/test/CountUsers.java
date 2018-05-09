package com.mino.mdiary.exercise.java.test;

import org.springframework.util.CollectionUtils;

import java.util.*;

public class CountUsers {

    /**
     * input:
        5
        1 2 3 3 5
        3
        1 2 1
        2 4 5
        3 5 3
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userCount = scanner.nextInt();
        int[] users = new int[userCount];
        for (int i = 0; i < userCount; ++i) {
            users[i] = scanner.nextInt();
        }
        int q = scanner.nextInt();
        int[][] data = new int[q][3];
        for (int i = 0; i < q;++i) {
            for (int j = 0; j < 3; ++j) {
                data[i][j] = scanner.nextInt();
            }
        }
        Map<Integer, List<Integer>> favMap = convertToMap(users);
        for (int i = 0; i < q; ++i) {
            System.out.println(scanCount(favMap, data[i][0], data[i][1], data[i][2]));
        }
    }

    private static Map<Integer, List<Integer>> convertToMap(int[] users) {
        Map<Integer, List<Integer>> resultMap = new HashMap<>();
        for (int i = 0; i < users.length; ++i) {
            resultMap.computeIfAbsent(users[i], k -> new ArrayList<>());
            resultMap.get(users[i]).add(users[i]);
        }
        return resultMap;
    }

    private static int scanCount(Map<Integer, List<Integer>> userMap, int start, int end, int target) {
        int sum = 0;
        List<Integer> users = userMap.get(target);
        if (!CollectionUtils.isEmpty(users)) {
            sum = (int) users.stream().filter(k -> k>=start && k <= end).count();
        }
        return sum;
    }
}
