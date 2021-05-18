package model;

public class CounterModel {
    private int counter;

    public CounterModel() {
        this.counter = 0;
    }

    public int getCounter() {
        return counter;
    }

    public void incrementCounter() {
        counter++;
    }

    public void incrementCounterBy(int x) {
        counter += x;
    }

    public void decrementCounter() {
        counter--;
    }
}
