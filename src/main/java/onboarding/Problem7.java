package onboarding;

import java.util.*;

public class Problem7 {
    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> answer;
        HashMap<String, Integer> friendAlgorithmScore = new HashMap<>();
        HashMap<String, List<String>> friendRelation = new HashMap<>();
        PriorityQueue<String[]> friendAlgorithmScoreSortQueue = new PriorityQueue<>((o1, o2) -> {
            if (Integer.parseInt(o1[1]) == Integer.parseInt(o2[1])) return o1[0].compareTo(o2[0]);
            return Integer.parseInt(o2[1]) - Integer.parseInt(o1[1]);
        });

        // visitors를 통해 방문 점수 파악하기
        for (String visitor : visitors) {
            getTimeLineVisitCount(friendAlgorithmScore, visitor);
        }

        // friends를 통해 친구 관계 파악하기
        // 이 결과는 friendRelation에 저장됨.
        for (List<String> friend : friends) {
            String friend1 = friend.get(0);
            String friend2 = friend.get(1);
            getFriendRelation(friend1, friend2, friendRelation);
            getFriendRelation(friend2, friend1, friendRelation);
        }

        for (String friend : friendRelation.keySet()) {
            // 해당 친구가 user와 친구라면 getFriendRelationCount()를 거치도록 함.
            // getFriendRelationCount(): 그의 친구에게 + 10 점수를 부여함.
            if (!isUserFriend(friendRelation, friend, user) || friend.equals(user)) continue;
            getFriendRelationCount(friendAlgorithmScore, friendRelation.get(friend));
        }

        for (String friend : friendAlgorithmScore.keySet()) {
            // 해당 친구가 user 본인이거나, user와 친구라면 패스하도록 함.
            if (isUserFriend(friendRelation, friend, user) || friend.equals(user)) continue;
            // 위의 조건에 충족하지 않으면
            // 정렬을 위해 friendAlgorithmScoreSortQueue에 추가
            friendAlgorithmScoreSortQueue.offer(new String[] {friend, Integer.toString(friendAlgorithmScore.get(friend))});
        }

        answer = friendAlgorithmScoreSelect(friendAlgorithmScoreSortQueue);
        return answer;
    }

    private static List<String> friendAlgorithmScoreSelect(
            PriorityQueue<String[]> friendAlgorithmScoreSortQueue
    ) {
        // friendAlgorithmScoreSortQueue에서 정렬되었던 값 중
        // 상위 5개만 selectResult에 저장되도록 함.
        List<String> selectResult = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            // friendAlgorithmScoreSortQueue가 비었다면 break;
            if (friendAlgorithmScoreSortQueue.isEmpty()) break;
            selectResult.add(friendAlgorithmScoreSortQueue.poll()[0]);
        }
        return selectResult;
    }

    private static void getFriendRelationCount(
            HashMap<String, Integer> friendAlgorithmScore,
            List<String> friends
    ) {
        // friends(친구 목록)에 있는 모든 친구에게 + 10
        for (String friend : friends) {
            friendAlgorithmScore.put(
                    friend,
                    friendAlgorithmScore.getOrDefault(friend, 0) + 10
            );
        }
    }

    private static boolean isUserFriend(
            HashMap<String, List<String>> friendRelation,
            String friend,
            String user
    ) {
        // friend가 user의 친구인지 확인 -> 친구가 맞으면 true
        return friendRelation.get(user).contains(friend);
    }

    private static void getFriendRelation(
            String friend1,
            String friend2,
            HashMap<String, List<String>> friendRelation
    ) {
        // friend2를 friend1의 친구 목록에 저장함.
        List<String> friendList = new ArrayList<>();
        // 친구목록이 존재하지 않았던 friend1이라면 friend1 항목 초기화
        if (friendRelation.containsKey(friend1)) friendList = friendRelation.get(friend1);
        // friend2 친구를 친구목록에 저장
        friendList.add(friend2);
        friendRelation.put(friend1, friendList);
    }

    private static void getTimeLineVisitCount(
            HashMap<String, Integer> friendAlgorithmScore,
            String visitor
    ) {
        // 타임라인 방문자에게 + 1
        friendAlgorithmScore.put(
                visitor,
                friendAlgorithmScore.getOrDefault(visitor, 0) + 1
        );
    }
}
