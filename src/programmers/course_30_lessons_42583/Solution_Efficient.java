package programmers.course_30_lessons_42583;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_Efficient {

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>();
        int current_weight = 0;
        int time = 0;

        // 다리를 bridge_length 만큼 0으로 초기화
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

        int truck_index = 0;
        // 모든 트럭이 다리에 오를 때까지 반복
        while (truck_index < truck_weights.length) {
            time++;

            // 다리에서 트럭(또는 0)이 하나 빠져나감
            current_weight -= bridge.poll();

            // 다음 트럭이 진입할 수 있는지 확인
            if (current_weight + truck_weights[truck_index] <= weight) {
                // 트럭 진입
                bridge.offer(truck_weights[truck_index]);
                current_weight += truck_weights[truck_index];
                truck_index++;
            } else {
                // 트럭이 진입할 수 없으면 0을 추가하여 시간만 흐르게 함
                bridge.offer(0);
            }
        }

        // 마지막 트럭이 다리에 올라탄 후, 다리를 완전히 건너는 시간 추가
        time += bridge_length;

        return time;
    }

    public static void main(String[] args) {
        Solution_Efficient sol = new Solution_Efficient();
        
        // 1. 요청하신 테스트 케이스
        int bridge_length1 = 2;
        int weight1 = 10;
        int[] truck_weights1 = {1, 1, 1, 1, 1};
        int result1 = sol.solution(bridge_length1, weight1, truck_weights1);
        System.out.println("테스트 1 결과: " + result1); // 예상 결과: 7

        // 2. 프로그래머스 예제 1
        int bridge_length2 = 2;
        int weight2 = 10;
        int[] truck_weights2 = {7, 4, 5, 6};
        int result2 = sol.solution(bridge_length2, weight2, truck_weights2);
        System.out.println("테스트 2 결과: " + result2); // 예상 결과: 8

        // 3. 프로그래머스 예제 2
        int bridge_length3 = 100;
        int weight3 = 100;
        int[] truck_weights3 = {10};
        int result3 = sol.solution(bridge_length3, weight3, truck_weights3);
        System.out.println("테스트 3 결과: " + result3); // 예상 결과: 101

        // 4. 프로그래머스 예제 3
        int bridge_length4 = 100;
        int weight4 = 100;
        int[] truck_weights4 = {10,10,10,10,10,10,10,10,10,10};
        int result4 = sol.solution(bridge_length4, weight4, truck_weights4);
        System.out.println("테스트 4 결과: " + result4); // 예상 결과: 110
    }
}
/*
inTime 같은 진입 시간을 트럭마다 저장하지 않고도 풀 수 있는 아주 효율적인 방법이 있습니다.

힌트는 다리의 길이를 '고정된 크기'로 생각하는 것입니다. (다리의 길이 = 다리를 건너는데 걸리는 시간 이므로)

   1. bridge_length와 동일한 크기를 갖는 큐(Queue)를 하나 만듭니다. 이 큐가 다리 자체를 의미합니다.
   2. 처음에 이 큐를 0으로 모두 채워넣습니다. (예: bridge_length가 2이면 [0, 0])
        3. 이 큐에는 트럭의 무게(정수)를 직접 저장합니다. 트럭이 없는 빈 공간은 0이 차지합니다.

이제 시간을 1초씩 증가시키면서 다음 로직을 반복합니다.

   1. 큐에서 항상 하나의 요소를 꺼냅니다 (`poll`).
        * 이것이 bridge_length초 전에 다리에 진입한 요소(트럭 또는 0)입니다. 즉, 다리를 완전히 건넌 것을 의미합니다.
       * 꺼낸 요소의 무게만큼 다리의 현재 총 무게에서 빼줍니다.

    2. 새로운 요소를 큐에 추가합니다 (`offer`).
    * 다리에 새로 진입할 트럭과 현재 다리 위 총 무게의 합이 weight를 초과하지 않는지 확인합니다.
    * 진입 가능하면, 대기 중인 트럭의 무게를 큐에 추가하고, 다리의 현재 총 무게에 더해줍니다.
   * 진입 불가능하면, 트럭 대신 빈 공간을 의미하는 0을 큐에 추가합니다.

이렇게 하면 큐의 크기가 항상 bridge_length로 유지되고, 큐에 들어간 트럭은 정확히 bridge_length 만큼의 시간이 흐른 뒤에 큐에서 나오게 됩니다. 따라서 트럭마다 별도의 시간을 기록할 필요가 없어집니다.

 */