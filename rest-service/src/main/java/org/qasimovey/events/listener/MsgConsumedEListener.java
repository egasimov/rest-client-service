package org.qasimovey.events.listener;
/*
import org.qasimovey.dao.MessageJDBCTemplate;
import org.qasimovey.entity.dto.MessageDTO;
import org.qasimovey.events.MessageConsumedEvent;
import org.qasimovey.service.MessageServiceImpl;
import org.qasimovey.utils.TempStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class MsgConsumedEListener {
    private static Logger logger= LoggerFactory.getLogger(MsgConsumedEListener.class);

    @Autowired
    MessageServiceImpl messageService;

    @EventListener
    public void reservationEventHandler(MessageConsumedEvent event) throws InterruptedException{
        logger.info("Listener caught event");
        messageService.changeStateOfMessages(event.getSource());
        logger.info("Listener is finished");

    }
}
*/