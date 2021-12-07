package com.xzm.course.controller.admin;

import com.xzm.course.config.themis.annotation.Admin;
import com.xzm.course.controller.BaseController;
import com.xzm.course.model.entity.TeacherEntity;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.admin.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Admin(Admin.TEACHER_MANAGE)
@RequestMapping("/admin/teacher")
@RestController
public class TeacherController extends BaseController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return teacherService.get(id);
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated TeacherEntity entity) {
        return teacherService.create(entity);
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return teacherService.delete(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated TeacherEntity entity) {
        return teacherService.update(entity);
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String departmentName, String name) {
        return teacherService.getPageCount(departmentName, name);
    }

    @RequestMapping("/page")
    public ResultVO getPage(String departmentName, String name) {
        return teacherService.getPage(1, departmentName, name);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String departmentName, String name) {
        return teacherService.getPage(index, departmentName, name);
    }

    @Admin
    @RequestMapping("/names")
    public ResultVO listName() {
        return teacherService.listName();
    }
}
