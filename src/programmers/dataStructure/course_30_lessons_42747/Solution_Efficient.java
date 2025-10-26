package programmers.dataStructure.course_30_lessons_42747;

import java.util.Arrays;
//https://school.programmers.co.kr/learn/courses/30/lessons/42747#
class Solution_Efficient {
    public static void main(String[] args) {
        Solution_Efficient solution = new Solution_Efficient();
        int result = solution.solution(new int[]{1, 1, 1, 1, 1});
        System.out.println("result = " + result);
    }

    public int solution(int[] citations) {
        Arrays.sort(citations);

        int max = 0;
        for(int i = citations.length-1; i > -1; i--){
            int min = (int)Math.min(citations[i], citations.length - i);
            if(max < min) max = min;
        }

        return max;
    }
}