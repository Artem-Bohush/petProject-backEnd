package com.gmail.bohush.art.petProjectBackEnd.dto;

import com.gmail.bohush.art.petProjectBackEnd.entity.Record;
import lombok.Data;

import java.util.Date;

@Data
public class RecordDto {
    private Long id;
    private String description;
    private double sum;
    private Long categoryId;
    private String type;
    private String planningDate;
    private long created;
    private String categoryName;

    public static Record toRecord(RecordDto recordDto) {
        Record record = new Record();
        record.setDescription(recordDto.getDescription());
        record.setSum(recordDto.getSum());
        record.setPlanningDate(recordDto.getPlanningDate());
        record.setCreated(new Date());
        return record;
    }

    public static RecordDto toRecordDto(Record record) {
        RecordDto recordDto = new RecordDto();
        recordDto.setId(record.getId());
        recordDto.setDescription(record.getDescription());
        recordDto.setSum(record.getSum());
        recordDto.setCreated(record.getCreated().getTime());
        recordDto.setCategoryName(record.getCategory().getName());
        recordDto.setType(record.getType().getName());
        return recordDto;
    }
}
