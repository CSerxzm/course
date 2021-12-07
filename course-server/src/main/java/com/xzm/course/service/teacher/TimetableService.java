package com.xzm.course.service.teacher;

import com.xzm.course.manager.teacher.TimetableManager;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimetableService extends BaseService {

    @Autowired
    private TimetableManager timetableManager;

    public ResultVO get() {
        Integer teacherId = getUserId();
        return result(timetableManager.listTeacherTimetable(teacherId));
    }
}
