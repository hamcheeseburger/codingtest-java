package programmers.course_30_lessons_42579;

import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/42579

class Solution {

    public static void main(String[] args) {
        int[] result = new Solution().solution(
                new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 800, 2500}
        );
        System.out.println("result = " + Arrays.toString(result));
    }

    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> totalPlaysMap = new HashMap<>();
        Map<String, PriorityQueue<Album>> genreAlbumMap = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            int id = i;
            totalPlaysMap.put(genres[i], totalPlaysMap.getOrDefault(genres[i], 0) + plays[id]);

            genreAlbumMap.compute(genres[i], (key, value) -> {
                if (value == null) {
                    value = new PriorityQueue<>(new AlbumComparator());
                }
                value.add(new Album(id, plays[id]));
                return value;
            });
        }

        List<String> genreOrders = totalPlaysMap.entrySet()
                .stream().sorted(((o1, o2) -> o2.getValue() - o1.getValue()))
                .map(Map.Entry::getKey)
                .toList();

        for (String genre : genreOrders) {
            PriorityQueue<Album> queue = genreAlbumMap.get(genre);
            int count = 0;
            while (count < 2 && !queue.isEmpty()) {
                Album album = queue.poll();
                answer.add(album.id());
                count++;
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}

record Album(int id, int plays) {
}

class AlbumComparator implements Comparator<Album> {

    @Override
    public int compare(Album a1, Album a2) {
        if (a1.plays() == a2.plays()) {
            return a1.id() - a2.id();
        }
        return a2.plays() - a1.plays();
    }
}