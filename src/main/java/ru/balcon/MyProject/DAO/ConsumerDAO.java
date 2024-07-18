package ru.balcon.MyProject.DAO;

import org.springframework.stereotype.Component;
import ru.balcon.MyProject.model.consumer.Consumer;

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

    public void addConsumer(){

    }

    public boolean isConsumerNameAvailable(String consumerName){
        return false;
    }


}
