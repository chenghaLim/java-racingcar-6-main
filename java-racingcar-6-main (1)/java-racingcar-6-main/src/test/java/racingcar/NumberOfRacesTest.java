package racingcar;

import model.NumberOfRaces;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberOfRacesTest {
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3"})
    @DisplayName("정상적인_입력_처리_테스트")
    void Not_Throw_When_isValid_NumberOfRaces(String inputNumberOfRaces) {
        Assertions.assertThatCode(() -> new NumberOfRaces(inputNumberOfRaces)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "-2", "-3"})
    @DisplayName("정상적인_입력이_아닐때_예외처리")
    void Throw_When_Not_Valid_NumberOfRaces(String inputNumberOfRaces) {
        Assertions.assertThatThrownBy(() -> new NumberOfRaces(inputNumberOfRaces))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.1", "-2.2", "3.3"})
    @DisplayName("정상적인_입력이_아닐때_예외처리")
    void Throw_When_Not_Integer_NumberOfRaces(String inputNumberOfRaces) {
        Assertions.assertThatThrownBy(() -> new NumberOfRaces(inputNumberOfRaces))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
