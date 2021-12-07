package com.xzm.course.controller.admin;

import com.xzm.course.config.themis.annotation.Admin;
import com.xzm.course.controller.BaseController;
import com.xzm.course.model.entity.StudentCourseEntity;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.admin.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Admin(Admin.STUDENT_COURSE_MANAGE)
@RequestMapping("/admin/student/course")
@RestController
public class StudentCourseController extends BaseController {

    @Autowired
    private StudentCourseService studentCourseService;

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return studentCourseService.get(id);
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated StudentCourseEntity entity) {
        return studentCourseService.create(entity);
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return studentCourseService.delete(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated StudentCourseEntity entity) {
        return studentCourseService.update(entity);
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String className, String courseName, String studentName) {
        return studentCourseService.getPageCount(className, courseName, studentName);
    }

    @RequestMapping("/page")
    public ResultVO getPage(String className, String courseName, String studentName) {
        return studentCourseService.getPage(1, className, courseName, studentName);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String className, String courseName, String studentName) {
        return studentCourseService.getPage(index, className, courseName, studentName);
    }
}
