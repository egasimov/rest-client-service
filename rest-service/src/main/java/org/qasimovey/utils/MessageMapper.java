package org.qasimovey.utils;

import org.qasimovey.entity.dto.MessageDTO;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;
public class MessageMapper implements RowMapper<MessageDTO> {
    @Override
    public MessageDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        MessageDTO dto=new MessageDTO();
        dto.setId(rs.getLong(ColumnUtils.ID)); //OK
        dto.setMsg(rs.getString(ColumnUtils.MESSAGE));
        dto.setCreatedAt(rs.getDate(ColumnUtils.CREATED_DATE));
        return dto;
    }
}
