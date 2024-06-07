package controller;

import model.Car;
import model.Cars;

import java.util.List;

public class Print {
    public void inputCarNamesMessage() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
    }

    public void inputNumberOfRacesMessage() {
        System.out.println("시도할 회수는 몇회인가요?");
    }

    public void resultTitleMessage() {
        System.out.println("실행 결과");
    }

    public static void printWinners(List<Car> winners){
        System.out.print("최종 우승자 : ");
        for(int i = 0; i<winners.size(); i++){
            System.out.print(winners.get(i).getName());
            if(i!=winners.size()-1){
                System.out.print(",");
            }
        }
    }

    // 전진 결과 출력
    public void printResult(Cars cars){
        for(int i = 0; i<cars.getNameList().size();i++){
            System.out.println(printCarNameAndPosition(cars.getNameList().get(i)));
        }
    }
    public String printCarNameAndPosition(Car car) {
        StringBuilder slash = new StringBuilder();
        for (int i = 0; i < car.getPosition(); i++) {
            slash.append("-");
        }
        return car.getName() + " : " + slash.toString();
    }

}
