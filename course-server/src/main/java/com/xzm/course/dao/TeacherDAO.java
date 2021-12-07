package com.xzm.course.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzm.course.dao.mapper.TeacherMapper;
import com.xzm.course.model.entity.TeacherEntity;
import com.xzm.course.model.vo.response.table.TeacherCourseItemVO;
import com.xzm.course.model.vo.response.table.TeacherItemVO;
import com.xzm.course.model.vo.response.table.TimetableItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.ws.soap.Addressing;
import java.util.List;

@Repository
public class TeacherDAO extends BaseDAO {
    
    public static final int PAGE_SIZE = 20;

    @Autowired
    private TeacherMapper teacherMapper;

    public int insert(TeacherEntity entity) {
        return teacherMapper.insert(entity);
    }

    public int delete(Integer id) {
        return teacherMapper.deleteById(id);
    }

    public TeacherEntity get(Integer id) {
        return teacherMapper.selectById(id);
    }

    public int update(TeacherEntity entity) {
        return teacherMapper.updateById(entity);
    }

    public int count(String departmentName, String name) {
        return teacherMapper.count(departmentName, name);
    }

    public List<TeacherItemVO> getPage(Integer index, String departmentName, String name) {
        Page<TeacherItemVO> page = new Page<>(index, PAGE_SIZE);

        return teacherMapper.getPage(page, departmentName, name).getRecords();
    }

    public TeacherEntity getByNumber(String number) {
        LambdaQueryWrapper<TeacherEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TeacherEntity::getNumber, number);

        return teacherMapper.selectOne(wrapper);
    }

    public Integer countByDepartmentId(Integer departmentId) {
        LambdaQueryWrapper<TeacherEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TeacherEntity::getDepartmentId, departmentId);

        return teacherMapper.selectCount(wrapper);
    }

    public List<TeacherEntity> listName() {
        LambdaQueryWrapper<TeacherEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(TeacherEntity::getId, TeacherEntity::getName);

        return teacherMapper.selectList(wrapper);
    }

    public List<TimetableItemVO> listTeacherTimetable(Integer teacherId) {
        return teacherMapper.listTeacherTimetable(teacherId);
    }

    public List<TeacherCourseItemVO> listTeacherCourse(Integer teacherId) {
        return teacherMapper.listTeacherCourse(teacherId);
    }
}
