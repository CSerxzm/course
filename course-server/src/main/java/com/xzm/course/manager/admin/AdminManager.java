package com.xzm.course.manager.admin;

import com.xzm.course.dao.AdminDAO;
import com.xzm.course.manager.BaseManager;
import com.xzm.course.model.entity.AdminEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminManager extends BaseManager {

    @Autowired
    private AdminDAO adminDAO;

    public AdminEntity get(Integer id) {
        return adminDAO.get(id);
    }

    public int create(AdminEntity entity) {
        return adminDAO.insert(entity);
    }

    public int update(AdminEntity entity) {
        return adminDAO.update(entity);
    }

    public int delete(Integer id) {
        return adminDAO.delete(id);
    }

    public List<AdminEntity> list() {
        return adminDAO.list();
    }
}
