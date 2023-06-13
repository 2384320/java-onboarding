package onboarding;

public class Problem2 {
    public static String solution(String cryptogram) {
        while (hasDuplicateWord(cryptogram)) {
            boolean[] checkDuplicateWordArray = checkDuplicateWord(cryptogram);
            cryptogram = deleteDuplicateWord(cryptogram, checkDuplicateWordArray);
        }

        return cryptogram;
    }

    private static String deleteDuplicateWord(
            String cryptogram,
            boolean[] checkDuplicateWordArray) {
        StringBuilder deleteDuplicateCryptogram = new StringBuilder();
        int checkDuplicateWordArrayLength = checkDuplicateWordArray.length;

        for (int i = 0; i < checkDuplicateWordArrayLength; i++) {
            if (checkDuplicateWordArray[i]) continue;
            deleteDuplicateCryptogram.append(cryptogram.charAt(i));
        }
        return deleteDuplicateCryptogram.toString();
    }

    private static boolean[] checkDuplicateWord(String cryptogram) {
        int cryptogramLength = cryptogram.length();
        boolean[] checkDuplicateWord = new boolean[cryptogramLength];

        for (int i = 0; i < cryptogramLength - 1; i++) {
            if (cryptogram.charAt(i) == cryptogram.charAt(i+1)) {
                checkDuplicateWord[i] = true;
                checkDuplicateWord[i+1] = true;
                i++;
            }
        }
        return checkDuplicateWord;
    }

    private static boolean hasDuplicateWord(String cryptogram) {
        for (int i = 0; i < cryptogram.length() - 1; i++) {
            if (cryptogram.charAt(i) == cryptogram.charAt(i+1)) return true;
        }
        return false;
    }
}
