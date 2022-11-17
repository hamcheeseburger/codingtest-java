package problem11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

// 못풀어서 답지 본 문제
// https://www.acmicpc.net/problem/11000
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        final int size = Integer.parseInt(bf.readLine());
        int[][] arrs = new int[size][2];
        for (int i = 0; i < size; i++) {
            final String[] ss = bf.readLine().split(" ");
            int[] arr = new int[2];
            for (int j = 0; j < 2; j++) {
                arr[j] = Integer.parseInt(ss[j]);
            }
            arrs[i] = arr;
        }

        final List<int[]> collect = Arrays.stream(arrs)
                .sorted((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0])
                .collect(Collectors.toList());
        int[][] newArrs = new int[size][2];
        for (int i = 0; i < size; i++) {
            newArrs[i] = collect.get(i);
        }

        System.out.println(Arrays.deepToString(newArrs));

        final PriorityQueue<Integer> integers = new PriorityQueue<>();
        integers.add(newArrs[0][1]);

        for (int i = 1; i < size; i++) {
            if (integers.peek() <= newArrs[i][0]) {
                integers.poll();
            }
            integers.add(newArrs[i][1]);
        }
        System.out.println(integers.size());
    }
}
