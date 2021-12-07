package com.xzm.course.controller.admin;

import com.xzm.course.config.themis.annotation.Admin;
import com.xzm.course.controller.BaseController;
import com.xzm.course.model.entity.ClassEntity;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.admin.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Admin(Admin.CLASS_MANAGE)
@RequestMapping("/admin/class")
@RestController
public class ClassController extends BaseController {

    @Autowired
    private ClassService classService;

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return classService.get(id);
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated ClassEntity entity) {
        return classService.create(entity);
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return classService.delete(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated ClassEntity entity) {
        return classService.update(entity);
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String departmentName, String majorName, String name) {
        return classService.getPageCount(departmentName, majorName, name);
    }

    @RequestMapping("/page")
    public ResultVO getPage(String departmentName, String majorName, String name) {
        return classService.getPage(1, departmentName, majorName, name);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String departmentName, String majorName, String name) {
        return classService.getPage(index, departmentName, majorName, name);
    }

    @Admin
    @RequestMapping("/names")
    public ResultVO listName() {
        return classService.listName();
    }

}
