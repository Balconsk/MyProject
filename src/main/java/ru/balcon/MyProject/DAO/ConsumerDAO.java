package ru.balcon.MyProject.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.balcon.MyProject.model.Consumer;

@Component
public class ConsumerDAO {
//    JdbcTemplate jdbcTemplate;
//    @Autowired
//    public ConsumerDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//    public Consumer getConsumerById(int id){
//        return null;
//    }
    public Consumer getConsumer(){
       return new Consumer(5,"UserName");
    }

}
