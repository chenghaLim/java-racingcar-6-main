package model;

import enums.FixedValue;

import java.util.ArrayList;
import java.util.List;

public class Cars {
    private List<Car> nameList = new ArrayList<>();

    public Cars(String naems) {
        List<Car> list = splitNames(naems);
        validateNameList(list);
        this.nameList = list;
    }

    public List<Car> getNameList() {
        return nameList;
    }

    private List<Car> splitNames(String names) {
        validateCarNames(names);
        List<Car> list = new ArrayList<>();
        String[] temp = names.split(",");
        for (String name : temp) {
            Car car = new Car(name);
            list.add(car);
        }
        return list;
    }

    // 이름 검증
    private static void validateCarNames(String names) {
        if (!isvalidateCarNames(names)) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateNameList(List<Car> nameList) {
        if (!isvalidateNameList(nameList)) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isvalidateCarNames(String names) {
        if (names.contains("\"") || names.contains(",,")) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    private static boolean isvalidateNameList(List<Car> nameList) {
        for (Car car : nameList) {
            if (car.getName().length() > FixedValue.MAX_CAR_NAME_LENGTH.get()) {
                throw new IllegalArgumentException();
            }

            if (car.getName().length() == FixedValue.EMPTY_CAR_NAME_LENGTH.get()) {
                throw new IllegalArgumentException();
            }

            if (car.getName().matches("-?\\d+")) {
                throw new IllegalArgumentException();
            }
        }
        return true;
    }

    // 우승자 찾기
    public List<Car> findWinners() {
        Car maxPositionCar = nameList.get(0);
        for (Car car : nameList) {
            if (maxPositionCar.compareTo(car) < 0) {
                maxPositionCar = car;
            }
        }

        List<Car> winners = new ArrayList<>();
        for (Car car : nameList) {
            if (car.isSamePosition(maxPositionCar)) {
                winners.add(car);
            }
        }
        return winners;
    }

    public void updatePosition(Car car){
        int position = car.getPosition();
        car.setPosition(position+1);
        nameList.set(nameList.indexOf(car),car);
    }

}
