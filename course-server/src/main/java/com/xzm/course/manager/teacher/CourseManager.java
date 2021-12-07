package com.xzm.course.manager.teacher;

import com.xzm.course.dao.CourseDAO;
import com.xzm.course.dao.StudentCourseDAO;
import com.xzm.course.dao.TeacherDAO;
import com.xzm.course.manager.BaseManager;
import com.xzm.course.model.entity.CourseEntity;
import com.xzm.course.model.vo.response.table.TeacherCourseItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("teacher_CourseManager")
public class CourseManager extends BaseManager {

    @Autowired
    private TeacherDAO teacherDAO;

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private StudentCourseDAO studentCourseDAO;

    public List<TeacherCourseItemVO> listTeacherCourse(Integer teacherId) {
        return teacherDAO.listTeacherCourse(teacherId);
    }

    public CourseEntity get(Integer id) {
        return courseDAO.get(id);
    }

    public int create(CourseEntity entity) {
        return courseDAO.insert(entity);
    }

    public int update(CourseEntity entity) {
        return courseDAO.update(entity);
    }

    public int delete(Integer id) {
        return courseDAO.delete(id);
    }

    public boolean hasStudentCourse(Integer courseId) {
        return studentCourseDAO.countByCourseId(courseId) > 0;
    }
}
