package controller;

import camp.nextstep.edu.missionutils.Console;

public class Input {

    // 자동차이름들 입력 받기
    public String inputCarNames() {
        String input = Console.readLine();
        return input;
    }

    // 레이싱 횟수 입력 받기
    public String inputNumberOfRaces() {
        String temp = Console.readLine();
        return temp;
    }
}
