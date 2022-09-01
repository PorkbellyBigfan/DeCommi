package org.zerock.decommi.dto;

import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaryFileDTO {
    private String uuid;
    private String fileName;
    private String filePath;

    public String getFileURL() {
        try {
            return URLEncoder.encode(filePath + "/" + uuid + "_" + fileName, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // file의 전체 경로를 알기 위한 메서드, Method to get the full path of a file
    public String getThumbnailURL() {
        try {
            return URLEncoder.encode(filePath + "/s_" + uuid + "_" + fileName, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
