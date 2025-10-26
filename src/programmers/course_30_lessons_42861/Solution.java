package programmers.course_30_lessons_42861;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        int result = new Solution().solution(5, new int[][]{{0, 4, 8}, {0, 1, 1}, {1, 3, 20}, {2, 3, 1}, {2, 4, 10}, {0, 3, 1}, {0, 2, 2}});
        System.out.println("result = " + result);
    }

    // Union-Find를 위한 부모 배열
    static int[] parent;

    // 특정 노드의 루트 찾기 (경로 압축 적용)
    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    // 두 노드를 같은 집합으로 합치기
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
        }
    }

    public int solution(int n, int[][] costs) {
        int answer = 0;

        // 부모 배열 초기화 (자기 자신을 부모로)
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 비용 기준으로 오름차순 정렬 (크루스칼 알고리즘)
        Arrays.sort(costs, Comparator.comparingInt(a -> a[2]));

        // 간선을 하나씩 확인하며 최소 신장 트리 구성
        for (int[] edge : costs) {
            int island1 = edge[0];
            int island2 = edge[1];
            int cost = edge[2];

            // 두 섬이 아직 연결되지 않았다면 (사이클이 생기지 않는다면)
            if (find(island1) != find(island2)) {
                union(island1, island2);
                answer += cost;
            }
            System.out.println("island1 : " + island1 + ", island2: " + island2 + ", cost: " + cost + " " + Arrays.toString(parent));
        }

        return answer;
    }
}
