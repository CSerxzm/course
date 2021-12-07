package com.xzm.course.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzm.course.dao.mapper.CourseMapper;
import com.xzm.course.model.bo.CourseItemBO;
import com.xzm.course.model.bo.StudentCourseSelectItemBO;
import com.xzm.course.model.entity.CourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDAO extends BaseDAO {

    public static final int PAGE_SIZE = 20;

    @Autowired
    private CourseMapper courseMapper;

    public int insert(CourseEntity entity) {
        return courseMapper.insert(entity);
    }

    public int delete(Integer id) {
        return courseMapper.deleteById(id);
    }

    public CourseEntity get(Integer id) {
        return courseMapper.selectById(id);
    }

    public int update(CourseEntity entity) {
        return courseMapper.updateById(entity);
    }

    public int count(String departmentName, String teacherName, String name) {
        return courseMapper.count(departmentName, teacherName, name);
    }

    public List<CourseItemBO> getPage(Integer index, String departmentName, String teacherName, String name) {
        Page<CourseItemBO> page = new Page<>(index, PAGE_SIZE);
        return courseMapper.getPage(page, departmentName, teacherName, name).getRecords();
    }

    public Integer countByTeacherId(Integer teacherId) {
        LambdaQueryWrapper<CourseEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CourseEntity::getTeacherId, teacherId);
        return courseMapper.selectCount(wrapper);
    }

    public List<CourseEntity> listName() {
        LambdaQueryWrapper<CourseEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(CourseEntity::getId, CourseEntity::getName);
        return courseMapper.selectList(wrapper);
    }

    public int increaseSelectedCount(Integer courseId) {
        CourseEntity course = courseMapper.selectById(courseId);
        course.setSelectedCount(course.getSelectedCount() + 1);
        return courseMapper.updateById(course);
    }

    public int decreaseSelectedCount(Integer courseId) {
        CourseEntity course = courseMapper.selectById(courseId);
        course.setSelectedCount(course.getSelectedCount() - 1);
        return courseMapper.updateById(course);
    }

    public Integer countStudentCanSelect(Integer studentId, Integer departmentId, Integer grade, String courseName, String teacherName) {
        return courseMapper.countStudentCanSelect(studentId, departmentId, grade, courseName, teacherName);
    }

    public List<StudentCourseSelectItemBO> getStudentCanSelectPage(Integer index, Integer departmentId, Integer studentId, Integer grade, String courseName, String teacherName) {
        Page<StudentCourseSelectItemBO> page = new Page<>(index, PAGE_SIZE);
        return courseMapper.getStudentCanSelectPage(page, studentId, departmentId, grade, courseName, teacherName).getRecords();
    }

    public Integer getDepartmentIdById(Integer courseId) {
        return courseMapper.getDepartmentIdById(courseId);
    }
}
