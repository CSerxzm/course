package com.xzm.course.controller.student;

import com.xzm.course.config.themis.annotation.Student;
import com.xzm.course.controller.BaseController;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.student.CourseSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Student
@RequestMapping("/student/course/select")
@RestController
public class CourseSelectController extends BaseController {

    @Autowired
    private CourseSelectService courseSelectService;

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String courseName, String teacherName) {
        return courseSelectService.getPageCount(courseName, teacherName);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String courseName, String teacherName) {
        return courseSelectService.getPage(index, courseName, teacherName);
    }

    @PostMapping("/{id}")
    public ResultVO create(@PathVariable Integer id) {
        return courseSelectService.create(id);
    }
}
