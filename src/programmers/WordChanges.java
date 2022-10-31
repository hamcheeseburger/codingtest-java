package programmers;

import java.util.List;
// https://school.programmers.co.kr/learn/courses/30/lessons/43163
// DFS
public class WordChanges {

    public static void main(String[] args) {
        String[] words = {"hot", "dot", "dog", "lot", "log"};
        System.out.println(solution("hit", "cog", words));
    }

    private static int count = 0;

    public static int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        final List<String> list = List.of(words);
        final int index = list.indexOf(target);
        if (index == -1) {
            return 0;
        }
        dfs(begin, target, words, visited);
        return count;
    }

    private static void dfs(String begin, String target, String[] words, boolean[] visited) {
        if (begin.equals(target)) {
            return;
        }

        int hitCount = 0;
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) == begin.charAt(i)) {
                hitCount++;
            }
        }
        if (hitCount == target.length() - 1) {
            count++;
            return;
        }
        String xx = null;
        for (int j = 0; j < words.length; j++) {
            if (visited[j]) {
                continue;
            }
            hitCount = 0;
            for (int i = 0; i < words[j].length(); i++) {
                if (words[j].charAt(i) == begin.charAt(i)) {
                    hitCount++;
                }
            }
            if (hitCount == words[j].length() - 1) {
                xx = words[j];
                visited[j] = true;
                break;
            }
        }
        if (xx != null) {
            count++;
            dfs(xx, target, words, visited);
        }
    }
}
