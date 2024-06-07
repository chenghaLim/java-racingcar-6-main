package controller;

import model.Car;
import model.Cars;
import model.RandomNumber;

import java.util.List;

public class ResultGo {

    // 경주 차들에게 각각 전진인지 정지인지 position 변경
    public void go(Cars cars){
        List<Car> nameList = cars.getNameList();
        for(int i = 0; i<nameList.size();i++){
            RandomNumber randomNumber = new RandomNumber();
            if(randomNumber.isgo(randomNumber.getRandomNumber())){
                cars.updatePosition(nameList.get(i));
            }
        }
    }

}
