package com.xzm.course.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzm.course.dao.mapper.ClassMapper;
import com.xzm.course.model.entity.ClassEntity;
import com.xzm.course.model.vo.response.table.MajorItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClassDAO extends BaseDAO {

    public static final int PAGE_SIZE = 20;

    @Autowired
    private ClassMapper classMapper;

    public int insert(ClassEntity entity) {
        return classMapper.insert(entity);
    }

    public int delete(Integer id) {
        return classMapper.deleteById(id);
    }

    public ClassEntity get(Integer id) {
        return classMapper.selectById(id);
    }

    public int update(ClassEntity entity) {
        return classMapper.updateById(entity);
    }

    public int count(String departmentName, String majorName, String name) {
        return classMapper.count(departmentName, majorName, name);
    }

    public List<MajorItemVO> getPage(Integer index, String departmentName, String majorName, String name) {
        Page<MajorItemVO> page = new Page<>(index, PAGE_SIZE);
        return classMapper.getPage(page, departmentName, majorName, name).getRecords();
    }

    public Integer countByMajorId(Integer id) {
        LambdaQueryWrapper<ClassEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClassEntity::getMajorId, id);
        return classMapper.selectCount(wrapper);
    }

    public List<ClassEntity> listName() {
        LambdaQueryWrapper<ClassEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(ClassEntity::getId, ClassEntity::getName);
        return classMapper.selectList(wrapper);
    }
}
