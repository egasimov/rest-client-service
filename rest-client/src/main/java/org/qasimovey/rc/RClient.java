package org.qasimovey.rc;

import org.qasimovey.rc.model.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
public class RClient {
    private static final Logger logger= LoggerFactory.getLogger(RClient.class);
    private RestTemplate restTemplate;
    private String webUrl="http://localhost:8181/api/messages";

    public RClient(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    @Scheduled(fixedRate =2500)
    public void makeRequest(){
        logger.info("Client made GET request");
        ResponseEntity<ResponseDto> responseEntity=restTemplate.getForEntity(webUrl, ResponseDto.class);
        logger.info(responseEntity.toString());

        if(responseEntity.getStatusCode()== HttpStatus.OK){
            logger.info("Client made PUT request");

            String ack= responseEntity.getBody().getAcknowledgement();
            MultiValueMap<String,String> headers=new HttpHeaders();
            headers.add("acknowledgement",ack);
            HttpEntity entity = new HttpEntity(headers);

            restTemplate.put(webUrl,entity);
            logger.info("Client already sent acknowledgment about receving message");
        }
    }
}
