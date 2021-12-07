package com.xzm.course.service.admin;

import com.xzm.course.manager.admin.ClassManager;
import com.xzm.course.model.entity.ClassEntity;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService extends BaseService {

    @Autowired
    private ClassManager classManager;

    public ResultVO getPageCount(String departmentName, String majorName, String name) {
        return result(classManager.getPageCount(departmentName, majorName, name));
    }

    public ResultVO getPage(Integer index, String departmentName, String majorName, String name) {
        return result(classManager.getPage(index, departmentName, majorName, name));
    }

    public ResultVO get(Integer id) {
        ClassEntity entity = classManager.get(id);
        if (entity == null) {
            return failedResult("班级Id: " + id + "不存在!");
        }
        return result(entity);
    }

    public ResultVO update(ClassEntity entity) {
        if (classManager.get(entity.getId()) == null) {
            return failedResult("班级Id: " + entity.getId() + "不存在!");
        }
        if (classManager.getMajorById(entity.getMajorId()) == null) {
            return failedResult("所属专业Id: " + entity.getMajorId() + "不存在!");
        }

        classManager.update(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (classManager.get(id) == null) {
            return failedResult("班级Id: " + id + "不存在!");
        }
        if (classManager.hasStudent(id)) {
            return failedResult("此班级中还有学生未被删除");
        }

        classManager.delete(id);
        return result("删除成功");
    }

    public ResultVO create(ClassEntity entity) {
        if (classManager.get(entity.getId()) != null) {
            return failedResult("班级Id: " + entity.getId() + "已存在!");
        }
        if (classManager.getMajorById(entity.getMajorId()) == null) {
            return failedResult("所属专业Id: " + entity.getMajorId() + "不存在!");
        }

        classManager.create(entity);
        return result("添加成功");
    }

    public ResultVO listName() {
        return result(classManager.listName());
    }
}
