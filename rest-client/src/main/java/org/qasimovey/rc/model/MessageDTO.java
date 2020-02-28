package org.qasimovey.rc.model;


import java.util.Date;

public class MessageDTO {
    private long id;
    private String msg;
    private Date createdAt;


    public MessageDTO(long id, String msg, Date createdAt) {
        this.id = id;
        this.msg = msg;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
