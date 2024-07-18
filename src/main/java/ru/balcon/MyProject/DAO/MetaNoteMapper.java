package ru.balcon.MyProject.DAO;

import org.springframework.jdbc.core.RowMapper;
import ru.balcon.MyProject.model.note.MetaNote;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MetaNoteMapper implements RowMapper<MetaNote> {

    @Override
    public MetaNote mapRow(ResultSet rs, int rowNum) throws SQLException {
        MetaNote metaNote = new MetaNote(
                rs.getInt("note_id"),
                rs.getString("note_name"));
        metaNote.setRootFolderId(rs.getInt("parent_folder_id"));
        return metaNote;
    }
}
