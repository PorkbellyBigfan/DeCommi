package org.zerock.decommi.service.diary;

import java.util.HashMap;

import org.zerock.decommi.dto.HeartDTO;
import org.zerock.decommi.entity.diary.Heart;

public interface HeartService {
    HashMap<String, Object> getListDino(Long dino);

    default Heart dtoToEntity(HeartDTO dto) {
        Heart entity = Heart.builder()
                .dino(dto.getDino())
                .hid(dto.getHid())
                .mid(dto.getMid())
                .build();
        return entity;
    }

    default HeartDTO EntityToDto(Heart entity) {
        HeartDTO dto = HeartDTO.builder()
                .dino(entity.getDino())
                .hid(entity.getHid())
                .mid(entity.getMid())
                .build();
        return dto;
    }
}
