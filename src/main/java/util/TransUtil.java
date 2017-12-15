package util;

public class TransUtil {
    public static int getNumFromChar(String correctAnswer) {
        switch (correctAnswer) {
            case "A":
                return 1;
            case "B":
                return 2;
            case "C":
                return 3;
            case "D":
                return 4;
            default:
                return Integer.parseInt(null);
        }

    }
}
