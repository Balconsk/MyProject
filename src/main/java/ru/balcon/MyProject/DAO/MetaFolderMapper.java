package ru.balcon.MyProject.DAO;

import org.springframework.jdbc.core.RowMapper;
import ru.balcon.MyProject.model.folder.MetaFolder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MetaFolderMapper implements RowMapper<MetaFolder> {

    @Override
    public MetaFolder mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new MetaFolder(
                rs.getInt("folder_id"),
                rs.getString("folder_name"),
                rs.getInt("note_count"));
    }
}
