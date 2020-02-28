package org.qasimovey.dao;

import org.qasimovey.entity.dto.MessageDTO;
import org.qasimovey.entity.model.Message;
import org.qasimovey.utils.ColumnUtils;
import org.qasimovey.utils.MessageMapper;
import org.qasimovey.utils.TableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class MessageJDBCTemplate implements  MessageDAO{
    private static Logger logger= LoggerFactory.getLogger(MessageJDBCTemplate.class);

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private  final String query="SELECT * FROM "+ TableUtils.MESSAGE_HISTORY+" mh WHERE mh."+ ColumnUtils.STATUS+"="+ColumnUtils.STATE.NOT_SENT.state ;

    public MessageJDBCTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate=new JdbcTemplate(dataSource);
    }

    public List<MessageDTO> getAllMessages(){
        logger.info("DAO [GET] is called");
        List<MessageDTO> dtos=jdbcTemplate.query(query,new MessageMapper());
        logger.info("DAO [GET ]responded");
        return dtos;
    }


    @Override
    public void saveAll(List<Message> messages) {
        batchInsert(messages);
    }

    private int[] batchInsert(final List<Message> messages) {

        int[] updateCounts = jdbcTemplate.batchUpdate(
                "INSERT INTO "+TableUtils.MESSAGE_HISTORY+"("+ColumnUtils.ID+ ","+ColumnUtils.MESSAGE+","+ColumnUtils.CREATED_DATE+","+ColumnUtils.STATUS+") values(?,?,?,?)",
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, messages.get(i).getId());
                        ps.setString(2, messages.get(i).getMsg());
                        ps.setDate(3, new Date(messages.get(i).getCreatedAt().getTime()));
                        ps.setInt(4, messages.get(i).getStatus().state);


                    }

                    public int getBatchSize() {
                        return messages.size();
                    }
                } );
        return updateCounts;
    }


    private int[] batchUpdate(final List<Long> messages_ids) {
        int[] updateCounts = jdbcTemplate.batchUpdate(
                "update "+TableUtils.MESSAGE_HISTORY+" SET "+ ColumnUtils.STATUS+"= ? where "+ColumnUtils.ID+" = ?",
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, ColumnUtils.STATE.SENT.state);
                        ps.setLong(2, messages_ids.get(i));
                    }

                    public int getBatchSize() {
                        return messages_ids.size();
                    }
                } );
        return updateCounts;
    }

    public void changeStateOfMessagesById(List<Long> need_to_updates){
        logger.info("MASS UPDATE STARTED");
        if (need_to_updates.size()==0){
            return;
        }
        batchUpdate(need_to_updates);
        logger.info("MASS UPDATE FINISHED");
    }

    public void cleanDB(){
        jdbcTemplate.execute("DELETE FROM "+TableUtils.MESSAGE_HISTORY);
    }


}
