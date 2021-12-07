package com.xzm.course.service.admin;

import com.xzm.course.manager.admin.DepartmentManager;
import com.xzm.course.model.entity.DepartmentEntity;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService extends BaseService {

    @Autowired
    private DepartmentManager departmentManager;

    public ResultVO getPageCount(String name) {
        return result(departmentManager.getPageCount(name));
    }

    public ResultVO getPage(Integer index, String name) {
        return result(departmentManager.getPage(index, name));
    }

    public ResultVO get(Integer id) {
        DepartmentEntity entity = departmentManager.get(id);
        if (entity == null) {
            return failedResult("系Id: " + id + "不存在!");
        }

        return result(entity);
    }

    public ResultVO update(DepartmentEntity entity) {
        if (departmentManager.get(entity.getId()) == null) {
            return failedResult("系Id: " + entity.getId() + "不存在!");
        }

        departmentManager.update(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (departmentManager.get(id) == null) {
            return failedResult("系Id: " + id + "不存在!");
        }
        if (departmentManager.hasMajor(id)) {
            return failedResult("此系中还有专业未被删除");
        }
        if (departmentManager.hasTeacher(id)) {
            return failedResult("此系中还有教师未被删除");
        }

        departmentManager.delete(id);
        return result("删除成功");
    }

    public ResultVO create(DepartmentEntity entity) {
        if (departmentManager.get(entity.getId()) != null) {
            return failedResult("系Id: " + entity.getId() + "已存在!");
        }

        departmentManager.create(entity);
        return result("添加成功");
    }

    public ResultVO listName() {
        return result(departmentManager.listName());
    }
}
