package org.qasimovey.service;

import org.qasimovey.entity.dto.MessageDTO;
import org.qasimovey.entity.dto.ResponseDto;

import java.util.List;

public interface MessageService {
    List<MessageDTO> getAllMessages();
    //void changeStateOfMessages(Object messages);

    String generateACKandStore(List<MessageDTO> dtos);
    ResponseDto prepareResponseDto();
    boolean processAck(String ack);
}
