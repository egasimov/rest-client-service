package org.qasimovey.service;

import org.qasimovey.dao.MessageDAO;
import org.qasimovey.entity.dto.MessageDTO;
import org.qasimovey.entity.dto.ResponseDto;
import org.qasimovey.utils.TempStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private static Logger logger= LoggerFactory.getLogger(MessageServiceImpl.class);
    //private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    private final MessageDAO messageDAO;

    private final ApplicationEventPublisher applicationEventPublisher;

    public MessageServiceImpl(MessageDAO  messageDAO ,ApplicationEventPublisher applicationEventPublisher){
        this.applicationEventPublisher=applicationEventPublisher;
        this.messageDAO=messageDAO;
    }


    @Override
    public List<MessageDTO> getAllMessages() {
       List<MessageDTO> dtos=messageDAO.getAllMessages();
       //some business logic might be here
       //publish event in order to change state of messages that already fetched
//        if(dtos!=null && !dtos.isEmpty()) {
//            logger.info("MessageConsumedEvent published ");
//            applicationEventPublisher.publishEvent(new MessageConsumedEvent(dtos));
//
//        }

        logger.info("Service is done its work [GetAllMessages]");
        return dtos;
    }

    public ResponseDto prepareResponseDto(){
        List<MessageDTO> dtos=getAllMessages();

        String ack=dtos.isEmpty()?"":generateACKandStore(dtos);
        return new ResponseDto(dtos,ack);
    }

    @Override
    public String generateACKandStore(List<MessageDTO> dtos) {
        logger.info("Inside generateACK()");
        List<Long> l_ids=dtos.stream().map(dto->dto.getId()).collect(Collectors.toList());
        String acknowledgement=UUID.randomUUID().toString()+new Date().toString();
        logger.info("Generated ack: " +acknowledgement);

        //save acknowledge and related list of messages`s ids
        TempStorage.getInstance().put(acknowledgement,l_ids);
        return acknowledgement;

    }
    
    

    public boolean processAck(String ack){
        logger.info("process Ack() is called");
        //be sure that ack is correct
        if(ack.equals("")||!TempStorage.getInstance().containsKey(ack)){
            //no need for update because there is no unsent message in database
            return false;
        }
        List<Long> need_to_updates=TempStorage.getInstance().get(ack);
        messageDAO.changeStateOfMessagesById(need_to_updates);

        //remove key from temp storage
        TempStorage.getInstance().remove(ack);

        return true;
    }

}
