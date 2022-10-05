package org.zerock.decommi.service.diary;

import java.util.HashMap;
import java.util.List;

import org.zerock.decommi.dto.BookmarkDTO;
import org.zerock.decommi.entity.member.Bookmark;

import lombok.val;

public interface BookmarkService {

    //폴더 안 북마크 리스트
    List<BookmarkDTO> getListDino(Long dino, String bfolderName);

    //폴더 리스트
    List<BookmarkDTO> getBookFolderList(Long mid);

    //폴더간 게시물 이동
    // void 


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
