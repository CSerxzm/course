package com.xzm.course.manager.teacher;

import com.xzm.course.dao.CourseDAO;
import com.xzm.course.dao.StudentCourseDAO;
import com.xzm.course.manager.BaseManager;
import com.xzm.course.model.entity.CourseEntity;
import com.xzm.course.model.entity.StudentCourseEntity;
import com.xzm.course.model.vo.response.table.TeacherGradeItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GradeManager extends BaseManager {

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private StudentCourseDAO studentCourseDAO;

    public Integer getTeacherGradePageCount(Integer teacherId, String courseName, String studentName) {
        return calcPageCount(
                studentCourseDAO.countTeacherGrade(teacherId, courseName, studentName),
                StudentCourseDAO.PAGE_SIZE);
    }

    public List<TeacherGradeItemVO> getTeacherGradePage(Integer index, Integer teacherId, String courseName, String studentName) {
        return studentCourseDAO.getTeacherGradePage(index, teacherId, courseName, studentName);
    }

    public StudentCourseEntity getStudentCourseById(Integer studentCourseId) {
        return studentCourseDAO.get(studentCourseId);
    }

    public CourseEntity getCourseById(Integer courseId) {
        return courseDAO.get(courseId);
    }


    public int updateStudentCourse(StudentCourseEntity entity) {
        return studentCourseDAO.update(entity);
    }
}
