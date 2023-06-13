package onboarding;

import java.util.*;

public class Problem6 {
    public static List<String> solution(List<List<String>> forms) {
        List<String> answer = new ArrayList<>();
        HashMap<String, Integer> duplicateWordMap = new HashMap<>();

        for (List<String> form : forms) {
            getDuplicateWord(form, duplicateWordMap);
        }

        for (String duplicatePart : duplicateWordMap.keySet()) {
            searchDuplicate(duplicatePart, duplicateWordMap, forms, answer);
        }
        Collections.sort(answer);

        return answer;
    }

    private static boolean isDuplicateNickname(String nickname, String duplicatePart) {
        return nickname.contains(duplicatePart);
    }

    private static boolean isDuplicatePart(int duplicateCount) {
        return duplicateCount > 1;
    }

    private static void searchDuplicate(
            String duplicatePart,
            HashMap<String, Integer> duplicateWordMap,
            List<List<String>> forms,
            List<String> answer
    ) {
        if (!isDuplicatePart(duplicateWordMap.get(duplicatePart))) return;

        for (List<String> form : forms) {
            if (isDuplicateNickname(form.get(1), duplicatePart)) answer.add(form.get(0));
        }
    }

    private static void saveDuplicateWord(
            String nickname,
            HashMap<String, Integer> duplicateWordMap,
            int index
    ) {
        String duplicatePart = nickname.substring(index, index + 2);
        duplicateWordMap.put(
                duplicatePart,
                duplicateWordMap.getOrDefault(
                        duplicatePart,
                        0
                ) + 1
        );
    }

    private static void getDuplicateWord(
            List<String> form,
            HashMap<String, Integer> duplicateWordMap
    ) {
        String nickname = form.get(1);

        for (int i = 0; i < nickname.length() - 1; i++) {
            saveDuplicateWord(nickname, duplicateWordMap, i);
        }
    }
}
