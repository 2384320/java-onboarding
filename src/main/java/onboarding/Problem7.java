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

        getTimeLineVisitCount(visitors, friendAlgorithmScore);
        getFriendRelation(friends, friendRelation);

        for (String friend : friendRelation.keySet()) {
            if (!isUserFriend(friendRelation, friend, user) || friend.equals(user)) continue;
            getFriendRelationCount(friendAlgorithmScore, friendRelation.get(friend));
        }

        for (String friend : friendAlgorithmScore.keySet()) {
            if (isUserFriend(friendRelation, friend, user) || friend.equals(user)) continue;
            friendAlgorithmScoreSortQueue.offer(new String[]{friend, Integer.toString(friendAlgorithmScore.get(friend))});
        }

        answer = friendAlgorithmScoreSelect(friendAlgorithmScoreSortQueue);
        return answer;
    }

    private static List<String> friendAlgorithmScoreSelect(
            PriorityQueue<String[]> friendAlgorithmScoreSortQueue
    ) {
        List<String> selectResult = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (friendAlgorithmScoreSortQueue.isEmpty()) break;
            selectResult.add(friendAlgorithmScoreSortQueue.poll()[0]);
        }
        return selectResult;
    }

    private static void getFriendRelationCount(
            HashMap<String, Integer> friendAlgorithmScore,
            List<String> friends
    ) {
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
        return friendRelation.get(user).contains(friend);
    }

    private static void saveFriendRelation(
            String friend1,
            String friend2,
            HashMap<String, List<String>> friendRelation
    ) {
        List<String> friendList = new ArrayList<>();

        if (friendRelation.containsKey(friend1)) friendList = friendRelation.get(friend1);
        friendList.add(friend2);
        friendRelation.put(friend1, friendList);
    }

    private static void getFriendRelation(
            List<List<String>> friends,
            HashMap<String, List<String>> friendRelation
    ) {
        for (List<String> friend : friends) {
            String friend1 = friend.get(0);
            String friend2 = friend.get(1);
            saveFriendRelation(friend1, friend2, friendRelation);
            saveFriendRelation(friend2, friend1, friendRelation);
        }
    }

    private static void giveTimeLineScore(
            HashMap<String, Integer> friendAlgorithmScore,
            String visitor
    ) {
        friendAlgorithmScore.put(
                visitor,
                friendAlgorithmScore.getOrDefault(visitor, 0) + 1
        );
    }

    private static void getTimeLineVisitCount(
            List<String> visitors,
            HashMap<String, Integer> friendAlgorithmScore
    ) {
        for (String visitor : visitors) {
            giveTimeLineScore(friendAlgorithmScore, visitor);
        }
    }
}
