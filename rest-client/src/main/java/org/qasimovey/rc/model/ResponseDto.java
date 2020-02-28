package org.qasimovey.rc.model;


import java.util.List;


public class ResponseDto {
    private List<MessageDTO> messageDTO;
    private String acknowledgement;

    public ResponseDto(List<MessageDTO> messageDTO, String acknowledgement) {
        this.messageDTO = messageDTO;
        this.acknowledgement = acknowledgement;
    }

    public List<MessageDTO> getMessageDTO() {
        return messageDTO;
    }

    public void setMessageDTO(List<MessageDTO> messageDTO) {
        this.messageDTO = messageDTO;
    }

    public String getAcknowledgement() {
        return acknowledgement;
    }

    public void setAcknowledgement(String acknowledgement) {
        this.acknowledgement = acknowledgement;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "messageDTO=" + messageDTO +
                ", acknowledgement='" + acknowledgement + '\'' +
                '}';
    }
}
