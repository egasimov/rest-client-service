package org.qasimovey.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDto {
    private List<MessageDTO> messageDTO;
    private String acknowledgement;

}
