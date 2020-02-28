package org.qasimovey;

import lombok.RequiredArgsConstructor;
import org.qasimovey.dao.MessageDAO;
import org.qasimovey.dao.MessageJDBCTemplate;
import org.qasimovey.utils.ColumnUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.qasimovey.entity.model.Message;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Component
@RequiredArgsConstructor
public class AppStartUpRunner implements CommandLineRunner {
    private static Logger logger= LoggerFactory.getLogger(AppStartUpRunner.class);

    private final MessageDAO messageDAO;

    public void run(String... args) throws Exception{
        messageDAO.cleanDB();
        logger.info("STARTUP RUNNER BASLADI");
       List<Message> mlist= Arrays.asList(new Message(1,"qqqq .", new Date(), ColumnUtils.STATE.NOT_SENT),
                new Message(2,"AAAAA.", new Date(), ColumnUtils.STATE.NOT_SENT),
                new Message(3,"bbbb .", new Date(), ColumnUtils.STATE.NOT_SENT),
                new Message(4,"cccc .", new Date(), ColumnUtils.STATE.NOT_SENT));
       messageDAO.saveAll(mlist);
       logger.info("Message ler bazaya elave olundu");

    }

}
