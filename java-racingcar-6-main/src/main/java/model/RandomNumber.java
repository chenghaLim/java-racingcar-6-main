package model;

import camp.nextstep.edu.missionutils.Randoms;
import enums.FixedValue;

public class RandomNumber {
    private int randomNumber;

    // 랜덤 값 받기
    public RandomNumber() {
        int randomNumner = random();
        this.randomNumber = randomNumner;
    }

    public int getRandomNumber(){
        return randomNumber;
    }

    public int random(){
        int randomNumner = Randoms.pickNumberInRange(FixedValue.RANDOM_START_NUMBER.get(), FixedValue.RANDOM_END_NUMBER.get());
        if (!(FixedValue.RANDOM_START_NUMBER.get() <= randomNumner && randomNumner <= FixedValue.RANDOM_END_NUMBER.get())) {
            throw new IllegalArgumentException();
        }
        return randomNumner;
    }

    public boolean isgo(int randomNumber) {
        if (randomNumber >= FixedValue.MOVING_POINT_NUMBER.get()) {
            return true;
        } else {
            return false;
        }
    }
}
