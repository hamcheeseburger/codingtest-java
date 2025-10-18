package programmers.course_30_lessons_42747;

import java.util.*;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.solution(new int[]{1, 1, 1, 1, 1});
        System.out.println("result = " + result);
    }

    public int solution(int[] citations) {

        Arrays.sort(citations);

        int h = citations[0];
        int currentIndex = 0;

        if (h >= citations.length) {
            return citations.length;
        }

        for (; h < citations[citations.length - 1]; h++) {
            if (citations.length - currentIndex < h) {
                break;
            }
            if (citations[currentIndex] <= h) {
                currentIndex++;
            }
        }
        return h == 0 ? 0 : h-1;
    }
}