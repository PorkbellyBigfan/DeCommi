package org.zerock.decommi.service.diary;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.zerock.decommi.dto.BookmarkDTO;
import org.zerock.decommi.entity.member.Bookmark;
import org.zerock.decommi.repository.diary.BookmarkRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    @Override
    public List<BookmarkDTO> getBookFolderList(Long mid) {
        List<Bookmark> result = bookmarkRepository.getFolderList(mid);
        return result.stream().map(new Function<Bookmark, BookmarkDTO>() {
            @Override
            public BookmarkDTO apply(Bookmark t) {
                return EntityToDto(t);
            }
        }).collect(Collectors.toList());
    }

  @Override
  public List<BookmarkDTO> getListDino(Long dino, String bfolderName) {
     List<Bookmark> result = bookmarkRepository.getBookmarList(dino, bfolderName);
     return result.stream().map(new Function<Bookmark, BookmarkDTO>(){
        @Override
        public BookmarkDTO apply(Bookmark t) {    
            return EntityToDto(t);
        }
      }).collect(Collectors.toList());
  }
}