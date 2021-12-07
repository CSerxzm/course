package com.xzm.course.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzm.course.dao.mapper.StudentCourseMapper;
import com.xzm.course.model.entity.StudentCourseEntity;
import com.xzm.course.model.vo.response.table.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentCourseDAO extends BaseDAO {
    
    public static final int PAGE_SIZE = 20;
    
    @Autowired
    private StudentCourseMapper studentCourseMapper;

    public int insert(StudentCourseEntity entity) {
        return studentCourseMapper.insert(entity);
    }

    public int delete(Integer id) {
        return studentCourseMapper.deleteById(id);
    }

    public StudentCourseEntity get(Integer id) {
        return studentCourseMapper.selectById(id);
    }

    public int update(StudentCourseEntity entity) {
        return studentCourseMapper.updateById(entity);
    }

    public int count(String className, String courseName, String studentName) {
        return studentCourseMapper.count(className, courseName, studentName);
    }

    public List<StudentCourseItemVO> getPage(Integer index, String className, String courseName, String studentName) {
        Page<StudentCourseItemVO> page = new Page<>(index, PAGE_SIZE);

        return studentCourseMapper.getPage(page, className, courseName, studentName).getRecords();
    }

    public int countByCourseId(Integer courseId) {
        LambdaQueryWrapper<StudentCourseEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentCourseEntity::getCourseId, courseId);

        return studentCourseMapper.selectCount(wrapper);
    }

    public int countByStudentId(Integer studentId) {
        LambdaQueryWrapper<StudentCourseEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentCourseEntity::getStudentId, studentId);

        return studentCourseMapper.selectCount(wrapper);
    }

    public StudentCourseEntity getByCourseIdAndStudentId(Integer courseId, Integer studentId) {
        LambdaQueryWrapper<StudentCourseEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(StudentCourseEntity::getId)
                .eq(StudentCourseEntity::getCourseId, courseId)
                .eq(StudentCourseEntity::getStudentId, studentId);

        return studentCourseMapper.selectOne(wrapper);
    }

    public List<StudentCourseSelectedItemVO> listStudentCourseSelected(Integer studentId) {
        return studentCourseMapper.listStudentCourseSelected(studentId);
    }

    public List<StudentExamItemVO> listStudentExam(Integer studentId) {
        return studentCourseMapper.listStudentExam(studentId);
    }

    public Integer countStudentCourseSelectedByTimePart(Integer studentId, String timePart) {
        return studentCourseMapper.countStudentCourseSelectedByTimePart(studentId, timePart);
    }

    public List<TimetableItemVO> listStudentTimetable(Integer studentId) {
        return studentCourseMapper.listStudentTimetable(studentId);
    }

    public Integer countTeacherGrade(Integer teacherId, String courseName, String studentName) {
        return studentCourseMapper.countTeacherGrade(teacherId, courseName, studentName);
    }

    public List<TeacherGradeItemVO> getTeacherGradePage(Integer index, Integer teacherId, String courseName, String studentName) {
        Page<TeacherGradeItemVO> page = new Page<>(index, PAGE_SIZE);
        return studentCourseMapper.getTeacherGradePage(page, teacherId, courseName, studentName).getRecords();
    }
}
