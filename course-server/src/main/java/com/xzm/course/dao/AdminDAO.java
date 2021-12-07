package com.xzm.course.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xzm.course.dao.mapper.AdminMapper;
import com.xzm.course.model.entity.AdminEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDAO extends BaseDAO {

    @Autowired
    private AdminMapper adminMapper;

    public AdminEntity getByUsername(String username) {
        LambdaQueryWrapper<AdminEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminEntity::getUsername, username);
        return adminMapper.selectOne(wrapper);
    }

    public int insert(AdminEntity entity) {
        return adminMapper.insert(entity);
    }

    public int delete(Integer id) {
        return adminMapper.deleteById(id);
    }

    public AdminEntity get(Integer id) {
        return adminMapper.selectById(id);
    }

    public int update(AdminEntity entity) {
        return adminMapper.updateById(entity);
    }

    public List<AdminEntity> list() {
        return adminMapper.selectList(new LambdaQueryWrapper<>());
    }
}
