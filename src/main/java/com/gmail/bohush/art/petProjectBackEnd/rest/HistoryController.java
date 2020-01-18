package com.gmail.bohush.art.petProjectBackEnd.rest;

import com.gmail.bohush.art.petProjectBackEnd.dto.ChartDataDto;
import com.gmail.bohush.art.petProjectBackEnd.dto.RecordDto;
import com.gmail.bohush.art.petProjectBackEnd.entity.Category;
import com.gmail.bohush.art.petProjectBackEnd.entity.ChartData;
import com.gmail.bohush.art.petProjectBackEnd.entity.Record;
import com.gmail.bohush.art.petProjectBackEnd.entity.User;
import com.gmail.bohush.art.petProjectBackEnd.service.CategoryService;
import com.gmail.bohush.art.petProjectBackEnd.service.RecordService;
import com.gmail.bohush.art.petProjectBackEnd.service.RecordTypeService;
import com.gmail.bohush.art.petProjectBackEnd.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/history")
@CrossOrigin(origins = {"http://localhost:3000"})
public class HistoryController {

    private final UserService userService;
    private final CategoryService categoryService;
    private final RecordService recordService;
    private final RecordTypeService recordTypeService;


    public HistoryController(UserService userService, CategoryService categoryService, RecordService recordService,
                             RecordTypeService recordTypeService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.recordService = recordService;
        this.recordTypeService = recordTypeService;
    }

    @GetMapping("/getRecords")
    public List<RecordDto> getAllRecords(@RequestHeader(value = "authorization") String token) {
        User user = userService.getByToken(token);
        List<Record> records = user.getRecords();
        List<RecordDto> recordDtos = new ArrayList<>();
        records.forEach(record -> {
            recordDtos.add(RecordDto.toRecordDto(record));
        });
        return recordDtos;
    }

    @GetMapping("/getChartData")
    public List<ChartDataDto> getChartData(@RequestHeader(value = "authorization") String token) {
        User user = userService.getByToken(token);
        List<Category> categories = user.getCategories();
        List<ChartDataDto> chartDataDtos = new ArrayList<>();
        categories.forEach(category -> {
            chartDataDtos.add(ChartDataDto.toChartDataDto(category.getChartData()));
        });
        double totalOutcome = 0;
        for (ChartDataDto chartDataDto : chartDataDtos) {
            totalOutcome = totalOutcome + chartDataDto.getY();
        }
        double percent;
        for (ChartDataDto chartDataDto : chartDataDtos) {
            percent = (chartDataDto.getY() * 100) / totalOutcome;
            percent = (Math.round(percent * 10));
            chartDataDto.setPercent(percent / 10);
        }
        return chartDataDtos;
    }
}
