package org.zerock.decommi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
    private Long todoId;

    private String todoTitle;
    private String todoContent;
    private boolean todoStatus;

    private String writer; // Member 엔티티의 PK email
}
