package com.xzm.course.controller.admin;

import com.xzm.course.config.themis.annotation.Admin;
import com.xzm.course.controller.BaseController;
import com.xzm.course.model.entity.MajorEntity;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.admin.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Admin(Admin.MAJOR_MANAGE)
@RequestMapping("/admin/major")
@RestController
public class MajorController extends BaseController {
    
    @Autowired
    private MajorService majorService;

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return majorService.get(id);
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated MajorEntity entity) {
        return majorService.create(entity);
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return majorService.delete(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated MajorEntity entity) {
        return majorService.update(entity);
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String departmentName, String name) {
        ResultVO pageCount = majorService.getPageCount(departmentName, name);
        System.out.println(pageCount);
        return majorService.getPageCount(departmentName, name);
    }

    @RequestMapping("/page")
    public ResultVO getPage(String departmentName, String name) {
        return majorService.getPage(1, departmentName, name);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String departmentName, String name) {
        return majorService.getPage(index, departmentName, name);
    }

    @Admin
    @RequestMapping("/names")
    public ResultVO listName() {
        return majorService.listName();
    }
}
