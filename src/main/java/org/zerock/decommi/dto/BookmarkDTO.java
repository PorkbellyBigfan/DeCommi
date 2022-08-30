package org.zerock.decommi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkDTO {
    private Long bookmarkId;
    private String bfolderName;
    private boolean isBookmark;

    private String useremail;
    private Long diarynum;
}
