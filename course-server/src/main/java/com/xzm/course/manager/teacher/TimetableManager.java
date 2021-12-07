package com.xzm.course.manager.teacher;

import com.xzm.course.dao.TeacherDAO;
import com.xzm.course.manager.BaseManager;
import com.xzm.course.model.vo.response.table.TimetableItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimetableManager extends BaseManager {

    @Autowired
    private TeacherDAO teacherDAO;

    public List<TimetableItemVO> listTeacherTimetable(Integer teacherId) {
        return teacherDAO.listTeacherTimetable(teacherId);
    }
}
