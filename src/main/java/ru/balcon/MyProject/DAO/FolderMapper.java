package ru.balcon.MyProject.DAO;

import org.springframework.jdbc.core.RowMapper;
import ru.balcon.MyProject.model.Folder;

import java.sql.ResultSet;
import java.sql.SQLException;

class FolderMapper implements RowMapper<Folder> {

    @Override
    public Folder mapRow(ResultSet rs, int rowNum) throws SQLException {
        Folder folder = new Folder();
        folder.setId(rs.getInt("folder_id"));
        folder.setFolderName(rs.getString("folder_name"));
        folder.setRootFolderId(rs.getInt("parent_folder_id"));
        folder.setConsumerId(rs.getInt("consumer_id"));
        return folder;
    }
}
