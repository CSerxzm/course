package com.xzm.course.service.admin;

import com.xzm.course.manager.admin.AdminManager;
import com.xzm.course.model.entity.AdminEntity;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.BaseService;
import com.xzm.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends BaseService {

    @Autowired
    private AdminManager adminManager;

    public ResultVO get(Integer id) {
        AdminEntity entity = adminManager.get(id);
        if (entity == null) {
            return failedResult("管理员Id: " + id + "不存在!");
        }
        entity.setPassword("");
        return result(entity);
    }

    public ResultVO update(AdminEntity entity) {
        AdminEntity originEntity = adminManager.get(entity.getId());
        if (originEntity == null) {
            return failedResult("管理员Id: " + entity.getId() + "不存在!");
        }
        if (entity.getPassword().equals("")) {
            entity.setPassword(originEntity.getPassword());
        } else {
            entity.setPassword(entity.getPassword());
        }
        adminManager.update(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (adminManager.get(id) == null) {
            return failedResult("管理员Id: " + id + "不存在!");
        }

        adminManager.delete(id);
        return result("删除成功");
    }

    public ResultVO create(AdminEntity entity) {
        if (adminManager.get(entity.getId()) != null) {
            return failedResult("管理员Id: " + entity.getId() + "已存在!");
        }

        adminManager.create(entity);
        return result("添加成功");
    }

    public ResultVO list() {
        return result(adminManager.list());
    }
}
