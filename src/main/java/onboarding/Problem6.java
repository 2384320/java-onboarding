package onboarding;

import java.util.*;

public class Problem6 {
    public static List<String> solution(List<List<String>> forms) {
        List<String> answer = new ArrayList<>();
        HashMap<String, Integer> duplicateWordMap = new HashMap<>();

        // 모든 닉네임에서의 부분 문자열을 구함.
        // 중복 부분 문자열이 나온다면 나온 수 만큼 카운팅
        for (List<String> form : forms) {
            String nickname = form.get(1);
            getDuplicateWord(nickname, duplicateWordMap);
        }

        // 부분 문자열을 기준으로 탐색 시작
        for (String duplicatePart : duplicateWordMap.keySet()) {
            // 부분 문자열이 중복되었는지를 판단하며, 중복되지 않았다면 패스
            if (!isDuplicatePart(duplicateWordMap.get(duplicatePart))) continue;
            //
            for (List<String> form : forms) {
                // 닉네임이 해당 문자열을 포함한다면, 이메일을 리스트에 저장
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

    private static void getDuplicateWord(
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
}
