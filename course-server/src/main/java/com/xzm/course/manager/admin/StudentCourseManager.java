package com.xzm.course.manager.admin;

import com.xzm.course.dao.CourseDAO;
import com.xzm.course.dao.StudentCourseDAO;
import com.xzm.course.dao.StudentDAO;
import com.xzm.course.manager.BaseManager;
import com.xzm.course.model.entity.CourseEntity;
import com.xzm.course.model.entity.StudentCourseEntity;
import com.xzm.course.model.entity.StudentEntity;
import com.xzm.course.model.vo.response.table.StudentCourseItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class StudentCourseManager extends BaseManager {

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private StudentCourseDAO studentCourseDAO;

    @Autowired
    private StudentDAO studentDAO;

    public Integer getPageCount(String className, String courseName, String studentName) {
        int count = studentCourseDAO.count(className, courseName, studentName);
        return calcPageCount(count, StudentCourseDAO.PAGE_SIZE);
    }

    public List<StudentCourseItemVO> getPage(Integer index, String className, String courseName, String studentName) {
        return studentCourseDAO.getPage(index, className, courseName, studentName);
    }

    public StudentCourseEntity get(Integer id) {
        return studentCourseDAO.get(id);
    }

    @Transactional
    public int create(StudentCourseEntity entity) {
        courseDAO.increaseSelectedCount(entity.getCourseId());
        return studentCourseDAO.insert(entity);
    }

    public int update(StudentCourseEntity entity) {
        return studentCourseDAO.update(entity);
    }

    @Transactional
    public int delete(StudentCourseEntity entity) {
        courseDAO.decreaseSelectedCount(entity.getCourseId());
        return studentCourseDAO.delete(entity.getId());
    }

    public CourseEntity getCourseById(Integer courseId) {
        return courseDAO.get(courseId);
    }

    public StudentEntity getStudentById(Integer studentId) {
        return studentDAO.get(studentId);
    }

    public StudentCourseEntity getByCourseIdAndStudentId(Integer courseId, Integer studentId) {
        return studentCourseDAO.getByCourseIdAndStudentId(courseId, studentId);
    }

    public Integer getStudentGradeById(Integer studentId) {
        return studentDAO.getGradeById(studentId);
    }

    public boolean inSameDepartment(Integer courseId, Integer studentId) {
        return courseDAO.getDepartmentIdById(courseId)
                .equals(studentDAO.getDepartmentIdById(studentId));
    }
}
