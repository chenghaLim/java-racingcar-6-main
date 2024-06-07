package enums;

public enum FixedValue {
    MAX_CAR_NAME_LENGTH(5),
    EMPTY_CAR_NAME_LENGTH(0),
    RANDOM_START_NUMBER(0),
    RANDOM_END_NUMBER(9),
    MOVING_POINT_NUMBER(4);

    private final int value;

    FixedValue(int value){
        this.value = value;
    }

    public int get(){
        return value;
    }
}
