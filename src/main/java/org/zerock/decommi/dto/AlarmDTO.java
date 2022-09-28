package org.zerock.decommi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmDTO {
    private Long alarmId;
    private String alarmContent;
    private Long dino; // diary 의 PK

    @Override
    public String toString() {
        return "AlarmDTO{" +
                "alarmId=" + alarmId +
                ", alarmContent='" + alarmContent + '\'' +
                '}';
    }
}
