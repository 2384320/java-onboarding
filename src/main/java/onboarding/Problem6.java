package onboarding;

import java.util.*;

public class Problem6 {
    private static HashMap<String, Integer> duplicateWordMap;
    private static HashMap<String, String> rightNicknameAndEmail;
    public static List<String> solution(List<List<String>> forms) {
        List<String> answer = new ArrayList<>();
        duplicateWordMap = new HashMap<>();
        rightNicknameAndEmail = new HashMap<>();

        // 이메일과 닉네임을 검사
        // 규격에 맞는 양식이면 rightNicknameAndEmail 해시맵에 저장
        for (int i = 0; i < forms.size(); i++) {
            String nickname = forms.get(i).get(1);
            String email = forms.get(i).get(0);
            if (!checkRightNickname(nickname)
                    || !checkRightEmail(email)) continue;
            rightNicknameAndEmail.put(nickname, email);
        }

        // 모든 닉네임에서의 부분 문자열을 구함.
        // 중복 부분 문자열이 나온다면 나온 수 만큼 카운팅
        getDuplicateWord();

        for (String nickname : rightNicknameAndEmail.keySet()) {
            if (isDuplicateNickname(nickname)) answer.add(rightNicknameAndEmail.get(nickname));
        }

        Collections.sort(answer);
        return answer;
    }

    public static boolean isDuplicateNickname(String nickname) {
        for (String duplicateWord : duplicateWordMap.keySet()) {
            if (duplicateWordMap.get(duplicateWord) > 1) {
                if (nickname.contains(duplicateWord)) return true;
            }
        }
        return false;
    }

    public static void getDuplicateWord() {
        for (String nickname : rightNicknameAndEmail.keySet()) {
            for (int i = 0; i < nickname.length() - 1; i++) {
                String duplicatePart = nickname.substring(i, i+2);
                duplicateWordMap.put(duplicatePart, duplicateWordMap.getOrDefault(duplicatePart, 0) + 1);
            }
        }
    }

    public static boolean checkRightNickname(String nickname) {
        int nicknameLength = nickname.length();
        //
        if (nicknameLength < 1 || nicknameLength >= 20) return false;
        else return nickname.matches("^[ㄱ-ㅎ가-힣]*$");
    }

    public static boolean checkRightEmail(String email) {
        int emailLength = email.length();
        if (!email.contains("@") || emailLength < 11 || emailLength >= 20) return false;

        String[] idAndDomain = email.split("@");
        if (!idAndDomain[1].equals("email.com")) return false;

        return true;
    }
}
