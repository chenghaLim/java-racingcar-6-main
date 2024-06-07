package racingcar;

import controller.Print;
import enums.FixedValue;
import model.Car;
import model.Cars;
import model.RandomNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionTest {
    @Test
    @DisplayName("랜덤_숫자의_범위가_다를때_예외처리")
    void testRandomNumberInRange() {
        RandomNumber randomNumber = new RandomNumber();
        int randomNum = randomNumber.random();
        assertTrue(FixedValue.RANDOM_START_NUMBER.get() <= randomNum && randomNum <= FixedValue.RANDOM_END_NUMBER.get());
    }

    @Test
    @DisplayName("우승자_찾기")
    void findWinners() {
        Cars cars = new Cars("a,b");
        List<Car> list = cars.findWinners();
        assertThat(list.get(0).getPosition()).isEqualTo(list.get(1).getPosition());
    }

    @Test
    @DisplayName("postition 갯수 만큼 - 출력")
    void Print_Slash_By_Postition() {
        Car car = new Car("a", 4);
        Print print = new Print();
        String message = print.printCarNameAndPosition(car);
        assertThat(message).isEqualTo("a : ----");
    }
}
