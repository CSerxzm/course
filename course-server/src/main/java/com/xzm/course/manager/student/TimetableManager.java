package com.xzm.course.manager.student;

import com.xzm.course.dao.StudentCourseDAO;
import com.xzm.course.manager.BaseManager;
import com.xzm.course.model.vo.response.table.TimetableItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("student_timetableManager")
public class TimetableManager extends BaseManager {

    @Autowired
    private StudentCourseDAO studentCourseDAO;

    public List<TimetableItemVO> listStudentTimetable(Integer studentId) {
        return studentCourseDAO.listStudentTimetable(studentId);
    }
}
