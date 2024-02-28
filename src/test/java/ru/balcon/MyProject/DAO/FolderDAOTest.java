package ru.balcon.MyProject.DAO;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.balcon.MyProject.model.Consumer;
import ru.balcon.MyProject.model.Folder;

import javax.sql.DataSource;
import java.util.List;

public class FolderDAOTest {
    static JdbcTemplate jdbcTemplate;
    static FolderDAO folderDAO;
    @BeforeAll
    static void setup(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/testDB");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @BeforeAll
    public void initData(){
        jdbcTemplate.update("insert into consumer(username) values ('username2');");
        jdbcTemplate.update("insert into consumer(username) values ('username3');");

        jdbcTemplate.update("insert into folder(consumer_id, root_folder_id, \"folderName\")" +
                "values (1,currval('folder_folder_id_seq'),'folder 1');");
        jdbcTemplate.update("insert into folder(consumer_id, root_folder_id, \"folderName\")" +
                "values (1,currval('folder_folder_id_seq'),'folder 2');");
        jdbcTemplate.update("insert into folder(consumer_id, root_folder_id, \"folderName\")" +
                "values (1,currval('folder_folder_id_seq'),'folder 3');");
        folderDAO = new FolderDAO(jdbcTemplate,new ConsumerDAO(),new NoteDAO(jdbcTemplate));
    }

    @Test
    public void getRootFolderIdTest(){

    }


    @Test
    public void addFolderTest(){
        jdbcTemplate.update("insert into consumer(consumer_id,username) " +
                "values (1,'username1');");

        Folder folder = new Folder();
        folder.setFolderName("Test Folder 3");
        folder.setRootFolderId(1);
        folder.setConsumerId(1);
        folderDAO.addFolder(folder);


        List<Folder> ListFolder =  jdbcTemplate.query("SELECT * FROM folder WHERE folder_id=?",new FolderMapper(),3);

    }

    @Test
    public void getFolderTest(){
    }

}
