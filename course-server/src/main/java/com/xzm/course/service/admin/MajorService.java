package com.xzm.course.service.admin;

import com.xzm.course.manager.admin.MajorManager;
import com.xzm.course.model.entity.MajorEntity;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MajorService extends BaseService {

    @Autowired
    private MajorManager majorManager;

    public ResultVO getPageCount(String departmentName, String name) {
        return result(majorManager.getPageCount(departmentName, name));
    }

    public ResultVO getPage(Integer index, String departmentName, String name) {
        return result(majorManager.getPage(index, departmentName, name));
    }

    public ResultVO get(Integer id) {
        MajorEntity entity = majorManager.get(id);
        if (entity == null) {
            return failedResult("专业Id: " + id + "不存在!");
        }
        return result(entity);
    }

    public ResultVO update(MajorEntity entity) {
        if (majorManager.get(entity.getId()) == null) {
            return failedResult("专业Id: " + entity.getId() + "不存在!");
        }
        if (majorManager.getDepartmentById(entity.getDepartmentId()) == null) {
            return failedResult("所属系Id: " + entity.getDepartmentId() + "不存在!");
        }
        majorManager.update(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (majorManager.get(id) == null) {
            return failedResult("专业Id: " + id + "不存在!");
        }
        if (majorManager.hasClass(id)) {
            return failedResult("此专业中还有班级未被删除");
        }
        majorManager.delete(id);
        return result("删除成功");
    }

    public ResultVO create(MajorEntity entity) {
        if (majorManager.get(entity.getId()) != null) {
            return failedResult("专业Id: " + entity.getId() + "已存在!");
        }
        if (majorManager.getDepartmentById(entity.getDepartmentId()) == null) {
            return failedResult("所属系Id: " + entity.getDepartmentId() + "不存在!");
        }
        majorManager.create(entity);
        return result("添加成功");
    }

    public ResultVO listName() {
        return result(majorManager.listName());
    }
}
