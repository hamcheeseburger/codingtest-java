package programmers.course_30_lessons_42579;

import java.util.*;

public class Solution_Efficient {

    // 노래 정보를 담는 내부 클래스 (static으로 선언하여 Solution 인스턴스 없이 사용 가능)
    static class Song implements Comparable<Song> {
        int id;
        int play;

        public Song(int id, int play) {
            this.id = id;
            this.play = play;
        }

        @Override
        public int compareTo(Song other) {
            // 1. 재생 횟수가 같으면 고유번호가 낮은 순서 (오름차순)
            if (this.play == other.play) {
                return this.id - other.id;
            }
            // 2. 다르면 재생 횟수가 많은 순서 (내림차순)
            return other.play - this.play;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        // 1. 장르별 총 재생 횟수를 저장할 Map
        Map<String, Integer> genrePlaySum = new HashMap<>();
        // 2. 장르별 노래 목록(Song 객체)을 저장할 Map
        Map<String, List<Song>> songsByGenre = new HashMap<>();

        // 3. 데이터 집계
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            // 장르별 총 재생 횟수 누적
            genrePlaySum.put(genre, genrePlaySum.getOrDefault(genre, 0) + play);

            // 장르별 노래 목록 생성 및 추가
            songsByGenre.computeIfAbsent(genre, g -> new ArrayList<>()).add(new Song(i, play));
        }

        // 4. 장르 정렬: 총 재생 횟수가 많은 순으로 장르 이름 정렬
        List<String> sortedGenres = genrePlaySum.keySet().stream()
                .sorted((g1, g2) -> genrePlaySum.get(g2).compareTo(genrePlaySum.get(g1)))
                .toList();

        // 5. 결과 생성
        List<Integer> bestAlbum = new ArrayList<>();
        for (String genre : sortedGenres) {
            List<Song> songs = songsByGenre.get(genre);
            // 각 장르 내에서 노래들을 정렬 (Song 클래스의 compareTo 기준 사용)
            Collections.sort(songs);

            // 최대 2곡까지 앨범에 추가
            for (int i = 0; i < Math.min(songs.size(), 2); i++) {
                bestAlbum.add(songs.get(i).id);
            }
        }

        // 6. List<Integer>를 int[] 배열로 변환하여 반환
        return bestAlbum.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        Solution_Efficient sol = new Solution_Efficient();
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        int[] result = sol.solution(genres, plays);
        
        // 예상 결과: [4, 1, 3, 0]
        System.out.println("베스트 앨범: " + Arrays.toString(result));
    }
}

/***
PriorityQueue를 사용 vs List로 Collections.sort() 사용 비교

결론부터 말씀드리면, 이 문제에서는 두 방법의 시간 복잡도는 거의 동일하지만, 특정 조건에서는 우선순위 큐를 쓰는 것이 더 효율적일 수 있습니다.

두 가지 시나리오로 나누어 비교해 보겠습니다.

---

시나리오 1: 모든 노래를 다 넣고 정렬 vs. 모든 노래를 다 넣고 힙 구성

   - `Collections.sort()`: 장르별 List에 모든 노래(k개)를 넣은 후, Collections.sort()를 호출합니다. 이 과정의 시간 복잡도는 O(k log k)입니다.
   - `PriorityQueue`: 장르별 PriorityQueue에 모든 노래(k개)를 넣습니다. k개의 원소를 힙에 넣는 과정의 총 시간 복잡도 역시 O(k log k)입니다.

   이 경우, 두 방법의 이론적인 시간 복잡도는 같습니다. 실제 성능은 ArrayList를 한 번에 정렬하는 것이 힙을 여러 번 재구성하는 것보다 약간 더 빠를 수 있습니다. 제가 제안해 드린 코드는 이 방식에 해당합니다.

  ---

시나리오 2: "상위 2개만 유지하는" 우선순위 큐 (가장 효율적인 방법)

PriorityQueue의 진정한 강점은 "상위 k개"만 필요할 때 나타납니다. 이 문제에서는 장르별로 상위 2곡만 필요하므로, 이 특성을 활용할 수 있습니다.

로직:
        1. 장르별로 크기가 2로 고정된 PriorityQueue를 사용합니다.
        2. (중요) 이 PriorityQueue는 최소 힙(min-heap)으로 설정합니다. (Java의 PriorityQueue는 기본이 최소 힙입니다.)
        3. 새로운 노래를 큐에 넣을 때마다,
        - 큐의 크기가 2보다 작으면 그냥 넣습니다.
        - 큐의 크기가 2이면, 큐에서 가장 작은 값(peek())과 새 노래의 재생 수를 비교합니다.
        - 새 노래가 큐의 가장 작은 값보다 크면, 가장 작은 값을 빼고(poll()) 새 노래를 넣습니다.
       - 그렇지 않으면 새 노래는 버립니다.

효율성 분석:
        - 이 방식을 사용하면 각 노래를 처리할 때마다 큐를 재구성하는 비용이 O(log 2)로 고정됩니다. (큐의 크기가 2이므로)
        - 따라서 장르 내의 모든 노래 k개를 처리하는 시간 복잡도는 O(k log 2)가 되어, 사실상 `O(k)` 가 됩니다.
        - 이는 O(k log k)보다 훨씬 효율적입니다.

 어떤 것을 선택해야 할까요?

 - 가독성과 단순함을 우선한다면 제가 제안한 List + Collections.sort() 방식이 매우 좋은 선택입니다. 코딩 테스트에서는 시간 내에 정확하게 푸는 것이 중요하므로 이 방법도 훌륭합니다.
 - 성능 최적화에 초점을 맞춘다면 "상위 2개만 유지하는" PriorityQueue 방식이 기술적으로 더 우월한 접근법입니다. 데이터의 양이 매우 많아지면 이 방식의 효율성이 빛을 발하게 됩니다.
***/