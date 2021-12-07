package com.xzm.course.controller.teacher;

import com.xzm.course.config.themis.annotation.Teacher;
import com.xzm.course.controller.BaseController;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.teacher.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Teacher
@RequestMapping("/teacher/timetable")
@RestController
public class TimetableController extends BaseController {

    @Autowired
    private TimetableService timetableService;

    @RequestMapping
    public ResultVO get() {
        return timetableService.get();
    }
}
