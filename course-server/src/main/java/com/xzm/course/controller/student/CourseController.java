package com.xzm.course.controller.student;

import com.xzm.course.config.themis.annotation.Student;
import com.xzm.course.controller.BaseController;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.student.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Student
@RequestMapping("/student/course")
@RestController("student_courseController")
public class CourseController extends BaseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/list")
    public ResultVO list() {
        return courseService.list();
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return courseService.delete(id);
    }
}
