package racingcar;

import controller.Input;
import controller.Print;
import controller.ResultGo;
import model.Car;
import model.Cars;
import model.NumberOfRaces;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        runApplication();
    }

    public static void runApplication() {
        Input input = new Input();
        Print print = new Print();
        ResultGo resultGo = new ResultGo();

        print.inputCarNamesMessage();
        Cars cars = new Cars(input.inputCarNames());

        print.inputNumberOfRacesMessage();
        NumberOfRaces numberOfRaces = new NumberOfRaces(input.inputNumberOfRaces());

        print.resultTitleMessage();

        int count = 1;
        while (numberOfRaces.getNumberOfRaces() >= count) {
            resultGo.go(cars);
            print.printResult(cars);
            count++;
        }

        List<Car> winners = cars.findWinners();

        print.printWinners(winners);

    }
}
