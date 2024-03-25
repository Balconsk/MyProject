package ru.balcon.MyProject.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.balcon.MyProject.model.*;

import java.util.List;

@Component
public class FolderDAO {
    private final JdbcTemplate jdbcTemplate;
//    private final ConsumerDAO consumerDAO;
    private final NoteDAO noteDAO;
    private final int consumerId = 5;
    @Autowired
    public FolderDAO(JdbcTemplate jdbcTemplate, ConsumerDAO consumerDAO, NoteDAO noteDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.noteDAO = noteDAO;
    }

    public Folder getFolder(int id){
        String sql = "select * from folder where folder_id=?";
        Folder folder = jdbcTemplate.query(sql, new FolderMapper(), id).get(0);

        sql =
                "select  folder_id, folder_name, coalesce(n.note_count,0) as note_count from folder left join " +
                "(SELECT parent_folder_id, count(*) as note_count " +
                "FROM note " +
                "GROUP BY parent_folder_id) " +
                "n on folder_id = n.parent_folder_id " +
                "WHERE folder.parent_folder_id=? and folder_id!=folder.parent_folder_id";
        List<MetaFolder> metaFolderList =
                jdbcTemplate.query(sql,
                        new MetaFolderMapper(),
                        folder.getId());
        sql = "select note_id, note_name, parent_folder_id from note where parent_folder_id=?";

        List<MetaNote> metaNoteList = jdbcTemplate.query(sql,
                new MetaNoteMapper(),
                folder.getId());
        folder.setItemsFolder(metaFolderList);
        folder.setItemsNote(metaNoteList);
        return folder;
    }

    public int getRootFolderIdByConsumer(Consumer consumer){
        return getRootFolderIdByConsumerId(consumer.getId());
    }
    public int getRootFolderIdByConsumerId(int id){
        String sql = "SELECT folder_id FROM folder WHERE (folder_id=parent_folder_id AND consumer_id=?)";
        return jdbcTemplate.queryForObject(sql, Integer.class, id);
    }
    public void renameFolderById(int id, String newName){
        Folder folder = this.getFolder(id);
        folder.setFolderName(newName);
        updateFolder(folder);
    }
    public void updateFolder(Folder folder){
        updateFolder(folder, folder.getId());
    }
    public void updateFolder(Folder folder, int id){
        jdbcTemplate.update("UPDATE folder SET consumer_id=?, parent_folder_id=?, folder_name=?  WHERE folder_id = ? ",
                folder.getConsumerId(),
                folder.getRootFolderId(),
                folder.getFolderName(),
                folder.getId());
    }
    public void updateFolderNameById(String folderName ,int folderId){
        jdbcTemplate.update("UPDATE folder SET folder_name=?  WHERE folder_id = ? ",
                folderName ,folderId);
    }
    public void addFolder(Folder folder){
        jdbcTemplate.update("INSERT INTO folder(consumer_id, parent_folder_id, folder_name) " +
                        "values (?,?,?)",
                folder.getConsumerId(),
                folder.getRootFolderId(),
                folder.getFolderName());
    }
    public void addFolder(FolderTemplate folderTemplate){
        jdbcTemplate.update("INSERT INTO folder(consumer_id, parent_folder_id, folder_name) " +
                        "values (?,?,?)",
                folderTemplate.getConsumerId(),
                folderTemplate.getRootFolderId(),
                folderTemplate.getFolderName());
    }
    public void deleteFolderById(int id){
//        Folder folder = getFolder(id);
//        for (MetaNote n: folder.getItemsNote()){
//            noteDAO.deleteNote(n);
//        }
//        for (MetaFolder f: folder.getItemsFolder()){
//            this.deleteFolder(f);
//        }
        jdbcTemplate.update("DELETE FROM folder WHERE folder_id = ?",id);
    }

    public void deleteFolder(Folder folder){
        deleteFolderById(folder.getId());
    }
    public void deleteFolder(MetaFolder folder){
        deleteFolderById(folder.getId());
    }

}