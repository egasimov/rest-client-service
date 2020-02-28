package org.qasimovey.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.qasimovey.utils.ColumnUtils;

import java.util.Date;

@Data
@AllArgsConstructor
public class Message {
    private long id;
    private String msg;
    private Date createdAt;
    private ColumnUtils.STATE status;
}
