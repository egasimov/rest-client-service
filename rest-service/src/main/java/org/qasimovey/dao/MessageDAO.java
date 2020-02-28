package org.qasimovey.dao;

import org.qasimovey.entity.dto.MessageDTO;
import org.qasimovey.entity.model.Message;

import java.util.List;

public interface MessageDAO {
     List<MessageDTO> getAllMessages();
     void saveAll(List<Message> messages);
     void cleanDB();
     void changeStateOfMessagesById(List<Long> need_to_updates);
}
