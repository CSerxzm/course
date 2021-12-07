package com.xzm.course.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzm.course.dao.mapper.MajorMapper;
import com.xzm.course.model.entity.MajorEntity;
import com.xzm.course.model.vo.response.table.MajorItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MajorDAO extends BaseDAO {
    
    public static final int PAGE_SIZE = 20;

    @Autowired
    private MajorMapper majorMapper;

    public int insert(MajorEntity entity) {
        return majorMapper.insert(entity);
    }

    public int delete(Integer id) {
        return majorMapper.deleteById(id);
    }

    public MajorEntity get(Integer id) {
        return majorMapper.selectById(id);
    }

    public int update(MajorEntity entity) {
        return majorMapper.updateById(entity);
    }

    public int count(String departmentName, String name) {
        return majorMapper.count(departmentName, name);
    }

    public List<MajorItemVO> getPage(Integer index, String departmentName, String name) {
        Page<MajorItemVO> page = new Page<>(index, PAGE_SIZE);
        return majorMapper.getPage(page, departmentName, name).getRecords();
    }

    public Integer countByDepartmentId(Integer departmentId) {
        LambdaQueryWrapper<MajorEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MajorEntity::getDepartmentId, departmentId);
        return majorMapper.selectCount(wrapper);
    }

    public List<MajorEntity> listName() {
        LambdaQueryWrapper<MajorEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(MajorEntity::getId, MajorEntity::getName);
        return majorMapper.selectList(wrapper);
    }
}
