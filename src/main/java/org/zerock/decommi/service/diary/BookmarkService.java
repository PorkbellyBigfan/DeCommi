package org.zerock.decommi.service.diary;

import java.util.HashMap;

import org.zerock.decommi.dto.BookmarkDTO;
import org.zerock.decommi.entity.member.Bookmark;

public interface BookmarkService {
    HashMap<String, Object> getListDino(Long dino);

    default Bookmark dtoToEntity(BookmarkDTO dto) {
        Bookmark entity = Bookmark.builder()
                .dino(dto.getDino())
                .bid(dto.getBid())
                .mid(dto.getMid())
                .build();
        return entity;
    }

    default BookmarkDTO EntityToDto(Bookmark entity) {
        BookmarkDTO dto = BookmarkDTO.builder()
                .dino(entity.getDino())
                .bid(entity.getBid())
                .mid(entity.getMid())
                .build();
        return dto;
    }

}
