package programmers.course_30_lessons_42583;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/42583?language=java
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.solution(2, 10, new int[]{1, 1, 1, 1, 1});
        System.out.println("result = " + result);
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 1;

        // truck_weights Stack으로 만든다.
        Stack<Integer> stack = new Stack<>();
        for (int i = truck_weights.length - 1; i >= 0; i--) {
            stack.push(truck_weights[i]);
        }

        Queue<BridgeTruck> currentBridge = new LinkedList<>();

        int currentBrideWeight = 0;
        do {
            if (!currentBridge.isEmpty()) {
                BridgeTruck bridgeTruck = currentBridge.peek();
                if (time - bridgeTruck.inTime >= bridge_length) { // 큐에서 나올 트럭이 있는지 체크
                    BridgeTruck arrivalTruck = currentBridge.poll();
                    currentBrideWeight -= arrivalTruck.weight;
                }
            }
            if (!stack.empty()) {
                int peeked = stack.peek();
                if (currentBrideWeight + peeked <= weight && currentBridge.size() < bridge_length) { // 큐에 넣을 수 있는 트럭이 있는가?
                    Integer leavedTruck = stack.pop();
                    currentBrideWeight += leavedTruck;
                    currentBridge.add(new BridgeTruck(leavedTruck, time));
                }
            }

            System.out.println("time: " + time + " stack: " + stack + " currentBridge: " + currentBridge + " currentBridgeWeight: " + currentBrideWeight);
            time++;
        } while (!currentBridge.isEmpty() || time <= 0);

        return time-1;
    }

    class BridgeTruck {
        int weight;
        int inTime;

        public BridgeTruck(int weight, int inTime) {
            this.weight = weight;
            this.inTime = inTime;
        }

        @Override
        public String toString() {
            return "BridgeTruck{" +
                    "weight=" + weight +
                    ", inTime=" + inTime +
                    '}';
        }
    }
}
