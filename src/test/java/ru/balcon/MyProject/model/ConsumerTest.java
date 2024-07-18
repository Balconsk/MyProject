package ru.balcon.MyProject.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.balcon.MyProject.model.consumer.Consumer;

public class ConsumerTest {
    @Test
    public void testId(){
        int id= 1;
        Consumer consumer = new Consumer(id,"name");
        Assertions.assertEquals(consumer.getId(),id);
    }
}
