package model;

public class Car {
    private String name;
    private int position;

    public Car(String name) {
        this.name = name;
    }

    public Car(String naem, int position) {
        this.name = naem;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public boolean isSamePosition(Car car) {
        if (position == car.getPosition()) {
            return true;
        }
        return false;
    }

    public int compareTo(Car car) {
        if (this.position < car.position) {
            return -1;
        } else if (this.position > car.position) {
            return 1;
        } else {
            return 0;
        }
    }
}
