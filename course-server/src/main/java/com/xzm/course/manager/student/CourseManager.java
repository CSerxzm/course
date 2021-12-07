package com.xzm.course.manager.student;

import com.xzm.course.dao.CourseDAO;
import com.xzm.course.dao.StudentCourseDAO;
import com.xzm.course.manager.BaseManager;
import com.xzm.course.model.entity.StudentCourseEntity;
import com.xzm.course.model.vo.response.table.StudentCourseSelectedItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("student_CourseManager")
public class CourseManager extends BaseManager {

    @Autowired
    private StudentCourseDAO studentCourseDAO;

    @Autowired
    private CourseDAO courseDAO;

    public StudentCourseEntity getStudentCourseById(Integer studentCourseId) {
        return studentCourseDAO.get(studentCourseId);
    }

    @Transactional
    public int deleteStudentCourse(StudentCourseEntity studentCourseEntity) {
        courseDAO.decreaseSelectedCount(studentCourseEntity.getCourseId());
        return studentCourseDAO.delete(studentCourseEntity.getId());
    }

    public List<StudentCourseSelectedItemVO> listStudentCourseSelected(Integer studentId) {
        return studentCourseDAO.listStudentCourseSelected(studentId);
    }
}
