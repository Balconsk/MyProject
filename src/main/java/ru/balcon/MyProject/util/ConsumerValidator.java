package ru.balcon.MyProject.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.balcon.MyProject.DAO.ConsumerDAO;
import ru.balcon.MyProject.model.consumer.ConsumerTemplate;
@Component
public class ConsumerValidator implements Validator {
    ConsumerDAO consumerDAO;
    @Autowired
    public ConsumerValidator(ConsumerDAO consumerDAO) {
        this.consumerDAO = consumerDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ConsumerTemplate.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ConsumerTemplate consumer = (ConsumerTemplate) target;

    }
}
