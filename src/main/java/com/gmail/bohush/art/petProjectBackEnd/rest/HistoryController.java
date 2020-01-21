package com.gmail.bohush.art.petProjectBackEnd.rest;

import com.gmail.bohush.art.petProjectBackEnd.dto.CategoryDto;
import com.gmail.bohush.art.petProjectBackEnd.dto.ChartDataDto;
import com.gmail.bohush.art.petProjectBackEnd.dto.PlanningDataDto;
import com.gmail.bohush.art.petProjectBackEnd.dto.RecordDto;
import com.gmail.bohush.art.petProjectBackEnd.entity.*;
import com.gmail.bohush.art.petProjectBackEnd.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private final ChartDataService chartDataService;
    private final PlanningDataService planningDataService;

    public HistoryController(UserService userService, CategoryService categoryService, RecordService recordService,
                             RecordTypeService recordTypeService, ChartDataService chartDataService, PlanningDataService planningDataService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.recordService = recordService;
        this.recordTypeService = recordTypeService;
        this.chartDataService = chartDataService;
        this.planningDataService = planningDataService;
    }

    @GetMapping("/getRecords")
    public List<RecordDto> getAllRecords(@RequestHeader(value = "authorization") String token) {
        User user = userService.getByToken(token);
        List<Record> records = user.getRecords();
        List<RecordDto> recordDtos = new ArrayList<>();
        records.forEach(record -> {
            if (!record.getType().getName().equals("planning") && record.getCategory() != null) {
                recordDtos.add(RecordDto.toRecordDto(record));
            }
        });
        return recordDtos;
    }

    @GetMapping("/getChartData")
    public List<ChartDataDto> getChartData(@RequestHeader(value = "authorization") String token) {
        User user = userService.getByToken(token);
        List<Category> categories = user.getCategories();
        List<ChartDataDto> chartDataDtos = new ArrayList<>();
        categories.forEach(category -> {
            if (category.getChartData() != null) {
                chartDataDtos.add(ChartDataDto.toChartDataDto(category.getChartData()));
            }
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

    @DeleteMapping("/deleteRecord")
    public HttpStatus deleteRecord(@RequestHeader(value = "authorization") String token, @RequestBody RecordDto recordDto) {
        User user = userService.getByToken(token);
        Record record = recordService.findById(recordDto.getId());
        if (record.getType().getName().equals("outcome")) {
            ChartData chartData = record.getCategory().getChartData();
            chartData.setY(chartData.getY() - record.getSum());
            chartDataService.save(chartData);
            user.setBalance(user.getBalance() + record.getSum());
        } else if (record.getType().getName().equals("planning")) {
            PlanningData planningData = record.getCategory().getPlanningData();
            planningData.setTotalOutcome(planningData.getTotalOutcome() - record.getSum());
            planningDataService.save(planningData);
        } else {
            user.setBalance(user.getBalance() - record.getSum());
        }
        recordService.delete(recordDto.getId());
        userService.save(user);
        return HttpStatus.OK;
    }
}
