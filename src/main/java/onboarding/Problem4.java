package onboarding;

public class Problem4 {
    public static String solution(String word) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        for (char alphabet : word.toCharArray()) {
            if (alphabet == ' ') {
                sb.append(' ');
                continue;
            }

            if (Character.isLowerCase(alphabet)) alphabet = (char)('z' - alphabet + 'a');
            else alphabet = (char)('Z' - alphabet + 'A');
            sb.append(alphabet);
        }

        answer = sb.toString();
        return answer;
    }
}
