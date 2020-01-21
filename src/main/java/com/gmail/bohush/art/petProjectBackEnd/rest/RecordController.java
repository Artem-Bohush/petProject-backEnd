package com.gmail.bohush.art.petProjectBackEnd.rest;

import com.gmail.bohush.art.petProjectBackEnd.dto.CategoryDto;
import com.gmail.bohush.art.petProjectBackEnd.dto.RecordDto;
import com.gmail.bohush.art.petProjectBackEnd.entity.*;
import com.gmail.bohush.art.petProjectBackEnd.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/record")
@CrossOrigin(origins={ "http://localhost:3000"})
//@Slf4j
public class RecordController {

    private final UserService userService;
    private final CategoryService categoryService;
    private final RecordService recordService;
    private final RecordTypeService recordTypeService;
    private final ChartDataService chartDataService;
    private final PlanningDataService planningDataService;


    public RecordController(UserService userService, CategoryService categoryService, RecordService recordService,
                            RecordTypeService recordTypeService, ChartDataService chartDataService, PlanningDataService planningDataService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.recordService = recordService;
        this.recordTypeService = recordTypeService;
        this.chartDataService = chartDataService;
        this.planningDataService = planningDataService;
    }

    @GetMapping("/getCategories")
    public List<CategoryDto> getAllCategories(@RequestHeader(value="authorization") String token) {
        User user = userService.getByToken(token);
        List<Category> categories = user.getCategories();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        categories.forEach(category -> {
            categoryDtos.add(CategoryDto.toCategoryDto(category));
        });
        return categoryDtos;
    }

    @PostMapping("/addRecord")
    public HttpStatus addNewRecord(@RequestHeader(value="authorization") String token, @RequestBody RecordDto recordDto) {
        User user = userService.getByToken(token);
        Record record = RecordDto.toRecord(recordDto);
        record.setType(recordTypeService.findByName(recordDto.getType()));
        Category category = categoryService.findById(recordDto.getCategoryId());
        record.setCategory(category);
        record.setUser(user);
        recordService.save(record);
        if (record.getType().getName().equals("outcome")) {
            ChartData chartData = category.getChartData();
            if (chartData != null) {
                chartData.setY(chartData.getY() + record.getSum());
            } else {
                chartData = new ChartData();
                chartData.setLabel(record.getCategory().getName());
                chartData.setY(record.getSum());
                chartData.setCategory(category);
            }
            chartDataService.save(chartData);
            user.setBalance(user.getBalance() - record.getSum());
            userService.save(user);
        } else if (record.getType().getName().equals("income")) {
            user.setBalance(user.getBalance() + record.getSum());
            userService.save(user);
        } else {
            PlanningData planningData = category.getPlanningData();
            if (planningData != null) {
                planningData.setTotalOutcome(planningData.getTotalOutcome() + record.getSum());
            } else {
                planningData = new PlanningData();
                planningData.setCategoryName(record.getCategory().getName());
                planningData.setTotalOutcome(record.getSum());
                planningData.setCategory(category);
            }
            planningDataService.save(planningData);
        }
        return HttpStatus.OK;
    }

    @PostMapping("/addCategory")
    public HttpStatus addNewCategory(@RequestHeader(value="authorization") String token, @RequestBody CategoryDto categoryDto) {
        User user = userService.getByToken(token);
        Category ct = new Category();
        ct.setName(categoryDto.getName());
        ct.setLim(categoryDto.getLim());
        ct.setUser(user);
        categoryService.save(ct);
        return HttpStatus.OK;
    }

    @PutMapping("/editCategory")
    public HttpStatus editCategory(@RequestHeader(value="authorization") String token, @RequestBody CategoryDto categoryDto) {
        Category category = categoryService.findById(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setLim(categoryDto.getLim());
        categoryService.save(category);
        return HttpStatus.OK;
    }

    @DeleteMapping("/deleteCategory")
    public HttpStatus deleteCategory(@RequestHeader(value="authorization") String token, @RequestBody CategoryDto categoryDto) {
        User user = userService.getByToken(token);
        Category category = categoryService.findById(categoryDto.getId());
        List<Record> records = category.getRecords();
        double totalIncome = 0;
        double totalOutcome = 0;
        for (Record record: records) {
            if (record.getType().getName().equals("income")) {
                totalIncome += record.getSum();
            } else {
                totalOutcome += record.getSum();
            }
        }
        user.setBalance(user.getBalance() - totalIncome + totalOutcome);
        userService.save(user);
        categoryService.delete(categoryDto.getId());
        return HttpStatus.OK;
    }
}
