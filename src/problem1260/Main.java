package problem1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Integer> dfsResult = new ArrayList<>();
    static List<Integer> bfsResult = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final String[] firstLine = bufferedReader.readLine().split(" ");
        final int nodeSize = Integer.parseInt(firstLine[0]);
        final int edgeSize = Integer.parseInt(firstLine[1]);
        final int startNodeValue = Integer.parseInt(firstLine[2]);

        int[][] matrix = new int[nodeSize + 1][nodeSize + 1];
        int[][] matrix2 = new int[nodeSize + 1][nodeSize + 1];
        for (int i = 0; i < edgeSize; i++) {
            final String[] s = bufferedReader.readLine().split(" ");
            final int node1 = Integer.parseInt(s[0]);
            final int node2 = Integer.parseInt(s[1]);
            matrix[node1][node2] = 1;
            matrix[node2][node1] = 1;
            if (node2 != startNodeValue) {
                matrix2[node1][node2] = 1;
            }
            if (node1 != startNodeValue) {
                matrix2[node2][node1] = 1;
            }
        }
        dfs(matrix, startNodeValue, nodeSize);

        final Deque<Integer> deq = new ArrayDeque<>();
        deq.add(startNodeValue);
        bfs(matrix2, deq, nodeSize);

        for (int i : dfsResult) {
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i : bfsResult) {
            System.out.print(i + " ");
        }
    }

    private static void dfs(int[][] matrix, int visitNode, int nodeSize) {
        // 방문 처리
        dfsResult.add(visitNode);
        for (int i = 1; i <= nodeSize; i++) {
            matrix[i][visitNode] = 0;
        }
        for (int i = 1; i <= nodeSize; i++) {
            if (matrix[visitNode][i] == 1) {
                dfs(matrix, i, nodeSize);
            }
        }
    }

    private static void bfs(int[][] matrix, Deque<Integer> deque, int nodeSize) {
        while (!deque.isEmpty()) {
            final Integer visited = deque.pollFirst();
            bfsResult.add(visited);
            for (int i = 1; i <= nodeSize; i++) {
                if (matrix[visited][i] == 1) {
                    deque.add(i);
                    for (int j = 1; j <= nodeSize; j++) {
                        matrix[j][i] = 0;
                    }
                }
            }
        }
    }
}
