package racingcar;

import model.Cars;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CarsTest {
    @ParameterizedTest
    @ValueSource(strings = {"pobi,june"})
    @DisplayName("정상적인_입력_처리_테스트")
    void Not_Throw_When_isValid_CarNames(String names) {
        Assertions.assertThatCode(() -> new Cars(names)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobiww,june"})
    @DisplayName("정상적인_입력이_아닐때_예외처리")
    void Throw_When_Not_isValid_CarNames(String names) {
        Assertions.assertThatCode(() -> new Cars(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {",-june"})
    @DisplayName("정상적인_입력이_아닐때_예외처리")
    void Throw_When_Empty_isValid_CarNames(String names) {
        Assertions.assertThatCode(() -> new Cars(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"111,222"})
    @DisplayName("정상적인_입력이_아닐때_예외처리")
    void Throw_When_Number_isValid_CarNames(String names) {
        Assertions.assertThatCode(() -> new Cars(names))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
