package programmers.course_30_lessons_388353;

import java.util.*;
// https://school.programmers.co.kr/learn/courses/30/lessons/388353
class Solution_Efficient {

    // A simple class to hold cell coordinates
    static class Cell {
        int r, c;
        Cell(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        // Example usage for testing
        Solution_Efficient solution = new Solution_Efficient();
        int result = solution.solution(
            new String[]{"AZWQY", "CAABX", "BBDDA", "ACACA"},
            new String[]{"A", "BB", "A"}
        );
        System.out.println("Efficient solution result = " + result); // Expected: 15
    }

    public int solution(String[] storage, String[] requests) {
        // 1단계: 요청을 크레인과 지게차 작업으로 분리
        Set<String> craneItems = new HashSet<>();
        Map<String, Integer> forkliftRequests = new HashMap<>();

        for (String req : requests) {
            if (req.length() == 2) { // Crane request like "AA"
                craneItems.add(String.valueOf(req.charAt(0)));
            } else { // Forklift request like "A"
                forkliftRequests.put(req, forkliftRequests.getOrDefault(req, 0) + 1);
            }
        }

        // 2단계: 크레인 작업을 먼저 처리하여 초기 그리드 생성
        int rows = storage.length;
        int cols = storage[0].length();
        String[][] grid = new String[rows][cols];
        Map<String, List<Cell>> itemLocations = new HashMap<>();
        int itemsLeftAfterCrane = 0;

        for (int r = 0; r < rows; r++) {
            String[] rowItems = storage[r].split("");
            for (int c = 0; c < cols; c++) {
                String item = rowItems[c];
                if (craneItems.contains(item)) {
                    grid[r][c] = "1"; // Mark as empty
                } else {
                    grid[r][c] = item;
                    itemsLeftAfterCrane++;
                    itemLocations.computeIfAbsent(item, k -> new ArrayList<>()).add(new Cell(r, c)); // item별로 어느 위치에 있는지 넣어둔다.
                    // A -> (0,0), (1,2), (3,0) 이런식으로
                }
            }
        }

        // 3단계: 접근 가능한 모든 빈 공간을 BFS로 한 번에 탐색 (가장자리에서 시작하는 Flood Fill)
        boolean[][] accessibleEmpties = new boolean[rows][cols];
        Queue<Cell> queue = new LinkedList<>(); // 접근가능한 비어있는 공간을 저장할 큐

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c].equals("1") && (r == 0 || r == rows - 1 || c == 0 || c == cols - 1)) { // 가장자리가 비어있는 경우
                    if (!accessibleEmpties[r][c]) {
                        queue.add(new Cell(r, c));
                        accessibleEmpties[r][c] = true;
                    }
                }
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Cell current = queue.poll(); // 비어있는 공간을 하나 꺼낸다
            for (int i = 0; i < 4; i++) { // 현재 위치에서 상하좌우로 비어있는 공간을 탐색
                int nr = current.r + dr[i];
                int nc = current.c + dc[i];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols &&
                    grid[nr][nc].equals("1") && !accessibleEmpties[nr][nc]) {
                    accessibleEmpties[nr][nc] = true;
                    queue.add(new Cell(nr, nc));
                }
            }
        }

        // 4단계: 지게차로 접근 가능한 아이템을 식별하고, 요청 수와 비교하여 제거할 개수 계산
        int forkliftRemovedCount = 0;
        for (Map.Entry<String, Integer> entry : forkliftRequests.entrySet()) {
            String itemType = entry.getKey();
            int numRequests = entry.getValue();
            
            if (!itemLocations.containsKey(itemType)) {
                continue;
            }

            int accessibleCount = 0;
            for (Cell itemCell : itemLocations.get(itemType)) {
                for (int i = 0; i < 4; i++) { // 해당 itemCell의 상하좌우에 accessible한 빈 공간이 있는지 확인
                    int nr = itemCell.r + dr[i];
                    int nc = itemCell.c + dc[i];

                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && accessibleEmpties[nr][nc]) {
                        accessibleCount++;
                        break; // This item is accessible, no need to check other neighbors
                    }
                }
            }
            forkliftRemovedCount += Math.min(numRequests, accessibleCount);
        }

        // 5단계: 최종 계산
        return itemsLeftAfterCrane - forkliftRemovedCount;
    }
}
