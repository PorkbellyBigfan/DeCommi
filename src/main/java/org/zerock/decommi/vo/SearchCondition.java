package org.zerock.decommi.vo;

import java.util.List;

import lombok.Data;

@Data
public class SearchCondition {
    String keyword;
    boolean tagOption;
    List<String> tagList;

    // /asa?keyword=sadasd&tagOption=true
    public SearchCondition(String keyword, boolean tagOptipon,
            List<String> tagList) {
        this.keyword = keyword;
        this.tagOption = tagOptipon;
        this.tagList = tagList;
    }
}
