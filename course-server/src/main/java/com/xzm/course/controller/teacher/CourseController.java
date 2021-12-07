package com.xzm.course.controller.teacher;

import com.xzm.course.config.themis.annotation.Teacher;
import com.xzm.course.controller.BaseController;
import com.xzm.course.model.entity.CourseEntity;
import com.xzm.course.model.vo.CourseEntityVO;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.teacher.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Teacher
@RequestMapping("/teacher/course")
@RestController("teacher_courseController")
public class CourseController extends BaseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return courseService.get(id);
    }

    @RequestMapping("/list")
    public ResultVO list() {
        return courseService.list();
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated CourseEntityVO entity) {
        CourseEntity course = new CourseEntity();
        course.setId(entity.getId());
        course.setSelectedCount(entity.getSelectedCount());
        course.setCredit(entity.getCredit());
        course.setName(entity.getName());
        course.setExamDate(entity.getExamDate());
        course.setGrade(entity.getGrade());
        course.setTime(entity.getTime());
        course.setLocation(entity.getLocation());
        course.setExamLocation(entity.getExamLocation());
        course.setMaxSize(entity.getMaxSize());
        return courseService.create(course);
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return courseService.delete(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated CourseEntityVO entity) {
        CourseEntity course = new CourseEntity();
        course.setId(entity.getId());
        course.setSelectedCount(entity.getSelectedCount());
        course.setCredit(entity.getCredit());
        course.setName(entity.getName());
        course.setExamDate(entity.getExamDate());
        course.setGrade(entity.getGrade());
        course.setTime(entity.getTime());
        course.setLocation(entity.getLocation());
        course.setExamLocation(entity.getExamLocation());
        course.setMaxSize(entity.getMaxSize());
        return courseService.update(course);
    }
}
