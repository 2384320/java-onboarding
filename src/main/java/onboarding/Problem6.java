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
            if (!isDuplicatePart(duplicateWordMap.get(duplicatePart))) continue;
            for (List<String> form : forms) {
                if (isDuplicateNickname(form.get(1), duplicatePart)) answer.add(form.get(0));
            }
        }
        Collections.sort(answer);

        return answer;
    }

    private static boolean isDuplicateNickname(String nickname, String duplicatePart) {
        // 닉네임이 부분 문자열을 포함한다면 true
        return nickname.contains(duplicatePart);
    }

    private static boolean isDuplicatePart(int duplicateCount) {
        // 부분 문자열 중복 숫자가 1 초과라면 true 반환
        return duplicateCount > 1;
    }

    private static void saveDuplicateWord(
            String nickname,
            HashMap<String, Integer> duplicateWordMap
    ) {
        // 닉네임을 두 글자씩 나눠 부분 문자열 생성
        // 부분 문자열은 중복 숫자와 함께 duplicateWordMap에 저장
        for (int i = 0; i < nickname.length() - 1; i++) {
            String duplicatePart = nickname.substring(i, i+2);
            duplicateWordMap.put(
                    duplicatePart,
                    duplicateWordMap.getOrDefault(
                            duplicatePart,
                            0
                    ) + 1
            );
        }
    }

    private static void getDuplicateWord(
            List<String> form,
            HashMap<String, Integer> duplicateWordMap
    ) {
        String nickname = form.get(1);
        saveDuplicateWord(nickname, duplicateWordMap);
    }
}
