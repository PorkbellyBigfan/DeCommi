package org.zerock.decommi.dto;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
    private int fileId;
    private String fileType;
    private String fileName;
    private String fileOrgName;
    private String filePath;
    private int fileSize;

    private LocalDateTime regDate, modDate;
}
