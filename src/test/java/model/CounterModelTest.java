package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CounterModelTest {
    private CounterModel model;

    @BeforeEach
    public void beforeEach() {
        model = new CounterModel();
    }

    @Test
    public void testIncrementDecrement() {
        model.incrementCounter();
        assertEquals(1, model.getCounter());

        model.incrementCounterBy(2);
        assertEquals(3, model.getCounter());

        model.decrementCounter();
        assertEquals(2, model.getCounter());
    }

}