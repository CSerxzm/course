package com.xzm.course.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xzm.course.dao.mapper.DepartmentMapper;
import com.xzm.course.model.entity.DepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDAO extends BaseDAO {

    public static final int PAGE_SIZE = 20;

    @Autowired
    private DepartmentMapper departmentMapper;

    public int insert(DepartmentEntity entity) {
        return departmentMapper.insert(entity);
    }

    public int delete(Integer id) {
        return departmentMapper.deleteById(id);
    }

    public DepartmentEntity get(Integer id) {
        return departmentMapper.selectById(id);
    }

    public int update(DepartmentEntity entity) {
        return departmentMapper.updateById(entity);
    }

    public int count(String name) {
        LambdaQueryWrapper<DepartmentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, DepartmentEntity::getName, name);
        return departmentMapper.selectCount(wrapper);
    }

    public List<DepartmentEntity> getPage(Integer index, String name) {
        LambdaQueryWrapper<DepartmentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, DepartmentEntity::getName, name);
        return selectPage(departmentMapper, wrapper, index, PAGE_SIZE);
    }

    public List<DepartmentEntity> listName() {
        LambdaQueryWrapper<DepartmentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(DepartmentEntity::getId, DepartmentEntity::getName);
        return departmentMapper.selectList(wrapper);
    }
}
