package com.app.warehouse.controller;

import com.app.warehouse.model.物料表;
import com.app.warehouse.service.I物料表Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 魏陈露
 * @since 2024-09-24
 */
@RestController
@RequestMapping("/物料表")
public class 物料表Controller {

    @Autowired
    private I物料表Service 物料表Service;

    @PostMapping("/add")
    public ResponseEntity<String> add物料(@RequestBody 物料表 物料) {
        // 调用服务层添加物料
        物料表Service.add物料(物料);
        return ResponseEntity.ok("物料添加成功");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete物料(@PathVariable int id) {
        // 调用服务层删除物料
        物料表Service.delete物料(id);
        return ResponseEntity.ok("物料删除成功");
    }

    @PutMapping("/update")
    public ResponseEntity<String> update物料(@RequestBody 物料表 物料) {
        // 调用服务层更新物料
        物料表Service.update物料(物料);
        return ResponseEntity.ok("物料更新成功");
    }

    @GetMapping("/search")
    public ResponseEntity<List<物料表>> search物料(@RequestParam String keyword) {
        // 调用服务层搜索物料
        List<物料表> results = 物料表Service.search物料(keyword);
        return ResponseEntity.ok(results);
    }
}