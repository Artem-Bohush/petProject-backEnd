package com.gmail.bohush.art.petProjectBackEnd.rest;

import com.gmail.bohush.art.petProjectBackEnd.dto.PlanningDataDto;
import com.gmail.bohush.art.petProjectBackEnd.entity.Category;
import com.gmail.bohush.art.petProjectBackEnd.entity.Record;
import com.gmail.bohush.art.petProjectBackEnd.entity.User;
import com.gmail.bohush.art.petProjectBackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/planning")
@CrossOrigin(origins = {"http://localhost:3000"})
public class PlanningController {

    private final UserService userService;

    public PlanningController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getPlanningProgressData")
    public List<PlanningDataDto> getPlanningProgressData(@RequestHeader(value = "authorization") String token) {
        User user = userService.getByToken(token);
        List<Category> categories = user.getCategories();
        List<PlanningDataDto> planningDataDtos = new ArrayList<>();
        categories.forEach(category -> {
            if (category.getPlanningData() != null) {
                PlanningDataDto planningDataDto = new PlanningDataDto();
                planningDataDto.setCategoryName(category.getName());
                planningDataDto.setCategoryLimit(category.getLim());
                planningDataDto.setTotalCategoryOutcome(category.getPlanningData().getTotalOutcome());

                double percent = (planningDataDto.getTotalCategoryOutcome() * 100) / category.getLim();
                percent = (Math.round(percent * 10));
                planningDataDto.setPercent(percent / 10);

                planningDataDtos.add(planningDataDto);
            }
        });
        return planningDataDtos;
    }

    @GetMapping("/getPlanningTableData")
    public List<PlanningDataDto> getPlanningTableData(@RequestHeader(value = "authorization") String token) {
        User user = userService.getByToken(token);
        List<Record> records = user.getRecords();
        List<Record> planningRecords = new ArrayList<>();
        records.forEach(record -> {
            if (record.getType().getName().equals("planning")) {
                planningRecords.add(record);
            }
        });
        List<PlanningDataDto> planningDataDtos = new ArrayList<>();
        for (Record record : planningRecords) {
            PlanningDataDto planningDataDto = new PlanningDataDto();
            planningDataDto.setRecordId(record.getId());
            planningDataDto.setRecordOutcome(record.getSum());
            planningDataDto.setCategoryName(record.getCategory().getName());
            planningDataDto.setDescr(record.getDescription());
            planningDataDtos.add(planningDataDto);
        }
        return planningDataDtos;
    }
}
