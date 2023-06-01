package onboarding;

public class Problem2 {
    public static String solution(String cryptogram) {
        String answer = "answer";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < cryptogram.length(); i++) {
            char currentCharacter = cryptogram.charAt(i);
            if (sb.length() == 0) {
                sb.append(currentCharacter);
                continue;
            }

            char previousCharacter = sb.charAt(sb.length() - 1);

            if (previousCharacter == currentCharacter) sb.deleteCharAt(sb.length() - 1);
            else sb.append(currentCharacter);
        }
        answer = sb.toString();

        return answer;
    }
}
