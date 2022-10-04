package org.zerock.decommi.service.diary;

import java.util.HashMap;
import java.util.List;

import org.zerock.decommi.dto.BookmarkDTO;
import org.zerock.decommi.entity.member.Bookmark;

public interface BookmarkService {
    HashMap<String, Object> getListDino(Long dino, String bfolderName);

    List<BookmarkDTO> getBookFolderList(Long mid);

    default Bookmark dtoToEntity(BookmarkDTO dto) {
        Bookmark entity = Bookmark.builder()
                .dino(dto.getDino())
                .bfolderName(dto.getBfolderName())
                .bid(dto.getBid())
                .mid(dto.getMid())
                .build();
        return entity;
    }

    default BookmarkDTO EntityToDto(Bookmark entity) {
        BookmarkDTO dto = BookmarkDTO.builder()
                .dino(entity.getDino())
                .bfolderName(entity.getBfolderName())
                .bid(entity.getBid())
                .mid(entity.getMid())
                .build();
        return dto;
    }

}
