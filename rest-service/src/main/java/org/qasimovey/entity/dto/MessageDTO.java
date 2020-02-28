package org.qasimovey.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDTO {
    private long id;
    private String msg;
    private Date createdAt;

}
