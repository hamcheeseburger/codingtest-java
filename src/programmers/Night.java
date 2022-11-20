package programmers;
// https://school.programmers.co.kr/learn/courses/30/lessons/133501
public class Night {

    public static void main(String[] args) {
        int distance = 12;
        int[][] scope = {{7, 8}, {4, 6}, {11, 10}};
        int[][] times = {{2, 2}, {2, 4}, {3, 3}};
        System.out.println(solution(distance, scope, times));
    }

    public static int solution(int distance, int[][] scope, int[][] times) {
        int count = scope.length;

        for (int currentDistance = 1; currentDistance <= distance; currentDistance++) {
            boolean caught = false;
            for (int j = 0; j < count; j++) {
                if (currentDistance >= scope[j][0] && currentDistance <= scope[j][1]
                || currentDistance <= scope[j][0] && currentDistance >= scope[j][1]) {
                    int totalTime = times[j][0] + times[j][1];
                    final int cycle = currentDistance % totalTime;
                    if (cycle != 0 && cycle - times[j][0] <= 0) {
                        caught = true;
                    }
                    break;
                }
            }
            if(caught) {
                return currentDistance;
            }
        }
        return distance;
    }
}
