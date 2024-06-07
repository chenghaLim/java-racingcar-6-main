package model;

public class NumberOfRaces {

    private static final int ZERO = 0;
    private int numberOfRaces;

    public int getNumberOfRaces() {
        return numberOfRaces;
    }

    public NumberOfRaces(String inputNumberOfRaces) {
        validateNumberOfRaces(inputNumberOfRaces);
        this.numberOfRaces = Integer.parseInt(inputNumberOfRaces);
    }

    // 시도 횟수 오류검증
    private static void validateNumberOfRaces(String inputNumberOfRaces) {
        if (!isValidNumberOfRaces(inputNumberOfRaces)) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isValidNumberOfRaces(String inputNumberOfRaces) {
        try {
            return Integer.parseInt(inputNumberOfRaces) > ZERO;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }
}
