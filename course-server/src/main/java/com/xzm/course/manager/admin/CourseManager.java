package com.xzm.course.manager.admin;

import com.xzm.course.dao.CourseDAO;
import com.xzm.course.dao.StudentCourseDAO;
import com.xzm.course.dao.TeacherDAO;
import com.xzm.course.manager.BaseManager;
import com.xzm.course.model.bo.CourseItemBO;
import com.xzm.course.model.entity.CourseEntity;
import com.xzm.course.model.entity.TeacherEntity;
import com.xzm.course.model.vo.response.IdNameVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseManager extends BaseManager {

    @Autowired
    private TeacherDAO teacherDAO;

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private StudentCourseDAO studentCourseDAO;

    public Integer getPageCount(String departmentName, String teacherName, String name) {
        int count = courseDAO.count(departmentName, teacherName, name);
        return calcPageCount(count, CourseDAO.PAGE_SIZE);
    }

    public List<CourseItemBO> getPage(Integer index, String departmentName, String teacherName, String name) {
        return courseDAO.getPage(index, departmentName, teacherName, name);
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

    public TeacherEntity getTeacherById(Integer teacherId) {
        return teacherDAO.get(teacherId);
    }

    public boolean hasStudentCourse(Integer courseId) {
        return studentCourseDAO.countByCourseId(courseId) > 0;
    }

    public List<IdNameVO> listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<CourseEntity> entityList = courseDAO.listName();
        for (CourseEntity entity : entityList) {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        }

        return voList;
    }
}
