package programmers.course_30_lessons_388353;

import java.util.Arrays;
// https://school.programmers.co.kr/learn/courses/30/lessons/388353
class Solution {

    public static void main(String[] args) {

        Solution solution = new Solution();

        int solution1 = solution.solution(new String[]{"AZWQY", "CAABX", "BBDDA", "ACACA"}, new String[]{"A", "BB", "A"});
        System.out.println("solution1 = " + solution1);
    }

    public int solution(String[] storage, String[] requests) {
        // 현재 index가
        // 1. 가장자리이거나
        // 2. DFS로 가장자리까지 비어있는 경로인지
        String[][] storage2D = new String[storage.length][];

        for (int i = 0; i < storage.length; i++) {
            storage2D[i] = storage[i].split("");
        }


        int answer = 0;

        for(int z = 0; z < requests.length; z++) {
            if(requests[z].length() == 1) {
                // 지게차
                storage2D = forkLift(storage2D, String.valueOf(requests[z].charAt(0)));
            } else if(requests[z].length() == 2) {
                // 크레인
                storage2D = crain(storage2D, String.valueOf(requests[z].charAt(0)));
            }
        }

        System.out.println("RESULT " + Arrays.deepToString(storage2D));

        for(int i = 0; i < storage2D.length; i++) {
            for(int j=0; j < storage2D[i].length; j++) {
                if(!storage2D[i][j].equals("1")) {
                    answer++;
                }
            }
        }

        return answer;
    }

    private String[][] forkLift(String[][] storage, String request) {
        String[][] deepCopy = new String[storage.length][storage[0].length];

        for(int i = 0; i < storage.length; i++) {
            for(int j=0; j < storage[i].length; j++) {
                if (i == 0 || j == 0 || i == storage.length-1 || j == storage[i].length-1)  { // 내가 가장자리 index인가?
                    if(storage[i][j].equals(request)) {
                        deepCopy[i][j] = "1";
                    } else {
                        deepCopy[i][j] = storage[i][j];
                    }
                }
                else if (isBoundary(storage, i, j)) { // 내가 가장자리 index는 아니지만 테두리인가?
                    if(storage[i][j].equals(request)) {
                        deepCopy[i][j] = "1";
                    }else {
                        deepCopy[i][j] = storage[i][j];
                    }
                } else {
                    deepCopy[i][j] = storage[i][j];
                }
            }
        }
        return deepCopy;
    }

    private static boolean isBoundary(String[][] storage, int i, int j) {
        int rows = storage.length;
        int cols = storage[0].length;
        boolean[][] visited;

        // Check path starting from the cell above
        if (i > 0 && storage[i - 1][j].equals("1")) {
            visited = new boolean[rows][cols];
            if (canReachEdge(storage, i - 1, j, visited)) return true;
        }

        // Check path starting from the cell below
        if (i < rows - 1 && storage[i + 1][j].equals("1")) {
            visited = new boolean[rows][cols];
            if (canReachEdge(storage, i + 1, j, visited)) return true;
        }

        // Check path starting from the cell to the left
        if (j > 0 && storage[i][j - 1].equals("1")) {
            visited = new boolean[rows][cols];
            if (canReachEdge(storage, i, j - 1, visited)) return true;
        }

        // Check path starting from the cell to the right
        if (j < cols - 1 && storage[i][j + 1].equals("1")) {
            visited = new boolean[rows][cols];
            if (canReachEdge(storage, i, j + 1, visited)) return true;
        }

        return false;
    }

    // Recursive DFS helper to find a path of "1"s to the edge
    private static boolean canReachEdge(String[][] storage, int r, int c, boolean[][] visited) {
        int rows = storage.length;
        int cols = storage[0].length;

        // Base case: Out of bounds, already visited, or a wall
        if (r < 0 || r >= rows || c < 0 || c >= cols || visited[r][c] || !storage[r][c].equals("1")) {
            return false;
        }

        // Base case: Reached a physical edge
        if (r == 0 || r == rows - 1 || c == 0 || c == cols - 1) {
            return true;
        }

        // Mark as visited for this path search
        visited[r][c] = true;

        // Recursive step: explore neighbors
        if (canReachEdge(storage, r + 1, c, visited)) return true;
        if (canReachEdge(storage, r - 1, c, visited)) return true;
        if (canReachEdge(storage, r, c + 1, visited)) return true;
        if (canReachEdge(storage, r, c - 1, visited)) return true;

        return false;
    }

    private String[][] crain(String[][] storage, String request) {
        for(int i = 0; i < storage.length; i++) {
            for(int j=0; j < storage[i].length; j++) {
                if(storage[i][j].equals(request)) {
                    storage[i][j] = "1";
                }
            }
        }
        return storage;
    }
}