package com.xzm.course.controller.teacher;

import com.xzm.course.config.themis.annotation.Teacher;
import com.xzm.course.model.vo.TeacherGradeVO;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.teacher.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Teacher
@RequestMapping("/teacher/grade")
@RestController
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String courseName, String studentName) {
        return gradeService.getPageCount(courseName, studentName);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String courseName, String studentName) {
        return gradeService.getPage(index, courseName, studentName);
    }

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return gradeService.get(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated TeacherGradeVO vo) {
        return gradeService.update(vo);
    }
}
