package programmers.course_30_lessons_42861;

import java.util.*;

public class SolutionBFS {

    public static void main(String[] args) {
        int result = new SolutionBFS().solution(4, new int[][]{{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}});
        System.out.println("result = " + result);

        int result2 = new SolutionBFS().solution(5, new int[][]{{0, 4, 8}, {0, 1, 1}, {1, 3, 20}, {2, 3, 1}, {2, 4, 10}, {0, 3, 1}, {0, 2, 2}});
        System.out.println("result2 = " + result2);
    }

    public int solution(int n, int[][] costs) {
        int answer = 0;

        // 그래프를 인접 리스트로 표현 (현재 선택된 간선들만 포함)
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // 비용 기준으로 오름차순 정렬
        Arrays.sort(costs, Comparator.comparingInt(a -> a[2]));

        // 간선을 하나씩 확인하며 최소 신장 트리 구성
        for (int[] edge : costs) {
            int island1 = edge[0];
            int island2 = edge[1];
            int cost = edge[2];

            // BFS로 두 섬이 이미 연결되어 있는지 확인
            if (!isConnected(graph, island1, island2, n)) {
                // 연결되어 있지 않다면 간선 추가 (사이클이 생기지 않음)
                graph.get(island1).add(island2);
                graph.get(island2).add(island1);
                answer += cost;

                System.out.println("간선 추가: " + island1 + " - " + island2 + " (비용: " + cost + ")");
            } else {
                System.out.println("간선 건너뜀: " + island1 + " - " + island2 + " (비용: " + cost + ") - 이미 연결됨");
            }
        }

        return answer;
    }

    /**
     * BFS를 사용하여 두 노드가 연결되어 있는지 확인
     * @param graph 현재까지 구성된 그래프
     * @param start 시작 노드
     * @param end 도착 노드
     * @param n 전체 노드 개수
     * @return 연결되어 있으면 true, 아니면 false
     */
    private boolean isConnected(List<List<Integer>> graph, int start, int end, int n) {
        // 그래프가 비어있으면 연결되어 있지 않음
        if (graph.get(start).isEmpty() && graph.get(end).isEmpty()) {
            return false;
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 목표 노드에 도달했으면 연결되어 있음
            if (current == end) {
                return true;
            }

            // 인접한 노드들을 큐에 추가
            for (int next : graph.get(current)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

        // BFS가 끝났는데도 도달하지 못했으면 연결되어 있지 않음
        return false;
    }
}