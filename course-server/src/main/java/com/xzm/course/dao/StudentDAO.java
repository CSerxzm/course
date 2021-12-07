package com.xzm.course.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzm.course.dao.mapper.StudentMapper;
import com.xzm.course.model.entity.StudentEntity;
import com.xzm.course.model.vo.response.StudentInfoVO;
import com.xzm.course.model.vo.response.table.StudentItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAO extends BaseDAO {
    
    public static final int PAGE_SIZE = 10;
    
    @Autowired
    private StudentMapper studentMapper;

    public StudentEntity getByNumber(String number) {
        LambdaQueryWrapper<StudentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentEntity::getNumber, number);

        return studentMapper.selectOne(wrapper);
    }


    public int insert(StudentEntity entity) {
        return studentMapper.insert(entity);
    }

    public int delete(Integer id) {
        return studentMapper.deleteById(id);
    }

    public StudentEntity get(Integer id) {
        return studentMapper.selectById(id);
    }

    public int update(StudentEntity entity) {
        return studentMapper.updateById(entity);
    }

    public int count(String majorName, String className, String name) {
        return studentMapper.count(majorName, className, name);
    }

    public List<StudentItemVO> getPage(Integer index, String majorName, String className, String name) {
        Page<StudentItemVO> page = new Page<>(index, PAGE_SIZE);

        return studentMapper.getPage(page, majorName, className, name).getRecords();
    }

    public Integer countByClassId(Integer id) {
        LambdaQueryWrapper<StudentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentEntity::getClassId, id);

        return studentMapper.selectCount(wrapper);
    }

    public List<StudentEntity> listName() {
        LambdaQueryWrapper<StudentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(StudentEntity::getId, StudentEntity::getName);

        return studentMapper.selectList(wrapper);
    }

    public Integer getDepartmentIdById(Integer studentId) {
        return studentMapper.getDepartmentIdById(studentId);
    }

    public Integer getGradeById(Integer studentId) {
        return studentMapper.getGradeById(studentId);
    }

    public StudentInfoVO getStudentInfoById(Integer studentId) {
        return studentMapper.getStudentInfoById(studentId);
    }
}
