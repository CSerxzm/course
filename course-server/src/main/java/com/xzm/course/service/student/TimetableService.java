package com.xzm.course.service.student;

import com.xzm.course.manager.student.TimetableManager;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("student_timetableService")
public class TimetableService extends BaseService {

    @Autowired
    private TimetableManager timetableManager;

    public ResultVO get() {
        Integer studentId = getUserId();
        return result(timetableManager.listStudentTimetable(studentId));
    }
}
