package org.zerock.decommi.dto;
import java.time.LocalDateTime;

import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
    private Long dino;
    private String uuid;
    private String imgName;
    private String path;
    private LocalDateTime regDate;

    //file의 전체 경로를 알기 위한 메서드
    public String getImageURL(){
        try {
            return URLEncoder.encode(path+"/"+uuid+"_"+imgName, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    //thumbnail 파일의 전체 경로를 알기 위한 메서드
    public String getThumbnailURL(){
        try {
            return URLEncoder.encode(path+"/s_"+uuid+"_"+imgName, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
}
