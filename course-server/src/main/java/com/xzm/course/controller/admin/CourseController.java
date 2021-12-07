package com.xzm.course.controller.admin;

import com.xzm.course.config.themis.annotation.Admin;
import com.xzm.course.controller.BaseController;
import com.xzm.course.model.entity.CourseEntity;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.admin.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Admin(Admin.COURSE_MANAGE)
@RequestMapping("/admin/course")
@RestController
public class CourseController extends BaseController {
    
    @Autowired
    private CourseService courseService;

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return courseService.get(id);
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated CourseEntity entity) {
        return courseService.create(entity);
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return courseService.delete(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated CourseEntity entity) {
        return courseService.update(entity);
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String departmentName, String teacherName, String name) {
        return courseService.getPageCount(departmentName, teacherName, name);
    }

    @RequestMapping("/page")
    public ResultVO getPage(String departmentName, String teacherName, String name) {
        return courseService.getPage(1, departmentName, teacherName, name);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String departmentName, String teacherName, String name) {
        return courseService.getPage(index, departmentName, teacherName, name);
    }

    @Admin
    @RequestMapping("/names")
    public ResultVO listName() {
        return courseService.listName();
    }
}
