package onboarding;

import java.util.*;

public class Problem7 {
    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> answer = new ArrayList<>();
        HashMap<String, Integer> friendAlgorithmScore = new HashMap<>();
        HashMap<String, Integer> duplicateFriendHashMap = new HashMap<>();

        friendAlgorithmScore = getTimeLineVisitCount(friendAlgorithmScore, visitors);
        List<String> userFriends = getUserFriend(user, friends);

        for (String userFriend : userFriends) {
            List<String> friendsList = getUserFriend(userFriend, friends);

            for (String friendsFriend : friendsList) {
                duplicateFriendHashMap.put(friendsFriend, duplicateFriendHashMap.getOrDefault(friendsFriend, 0) + 1);
            }
        }

        friendAlgorithmScore = getTogetherFriendCount(friendAlgorithmScore, duplicateFriendHashMap);

        for (String friend : friendAlgorithmScore.keySet()) {
            if (friend.equals(user)) friendAlgorithmScore.remove(friend);
            else if (userFriends.contains(friend)) friendAlgorithmScore.remove(friend);
        }
        System.out.println(friendAlgorithmScore);

        return answer;
    }

    public static HashMap<String, Integer> getTogetherFriendCount(
            HashMap<String, Integer> friendAlgorithmScore,
            HashMap<String, Integer> duplicateFriendHashMap
    ) {

        for (String duplicateFriend : duplicateFriendHashMap.keySet()) {
            friendAlgorithmScore.put(
                    duplicateFriend,
                    friendAlgorithmScore.getOrDefault(
                            duplicateFriend,
                            duplicateFriendHashMap.get(duplicateFriend)
                    ) * 10
            );
        }

        return friendAlgorithmScore;
    }

    public static List<String> getUserFriend(String user, List<List<String>> friends) {
        List<String> userFriend = new ArrayList<>();
        for (int i = 0; i < friends.size(); i++) {
            if (friends.get(i).contains(user)) {
                int friendIndex = friends.get(i).indexOf(user) == 0 ? 1 : 0;
                userFriend.add(friends.get(i).get(friendIndex));
            }
        }
        return userFriend;
    }

    public static HashMap<String, Integer> getTimeLineVisitCount(
            HashMap<String, Integer> friendAlgorithmScore,
            List<String> visitors
    ) {

        for (String visitor : visitors) {
            friendAlgorithmScore.put(
                    visitor,
                    friendAlgorithmScore.getOrDefault(visitor, 0) + 1
            );
        }

        return friendAlgorithmScore;
    }
}
