package org.qasimovey.controller;

import org.qasimovey.entity.dto.MessageDTO;
import org.qasimovey.entity.dto.ResponseDto;
import org.qasimovey.service.MessageService;
import org.qasimovey.utils.TempStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private static Logger logger= LoggerFactory.getLogger(MessageController.class);
    private final  MessageService messageService;

    public MessageController(MessageService messageService){
        this.messageService=messageService;
    }


    @GetMapping
    public ResponseEntity<ResponseDto> getMessages(){
        logger.info("GET Method is triggered by Client");
        ResponseDto responseDto=messageService.prepareResponseDto();
        logger.info("Controller responded answer to client");

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping
    public ResponseEntity<String> getAcknowledgement(HttpServletRequest request){
        String ack=request.getHeader("acknowledgement");
        logger.info("GOT ACK from Client: " +ack);
        logger.info(""+ TempStorage.getInstance().get(ack));

        //do some process based on that acknowledgment
        messageService.processAck(ack);
        return ResponseEntity.ok("OK");
    }

}
