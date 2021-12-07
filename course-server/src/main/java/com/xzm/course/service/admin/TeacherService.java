package com.xzm.course.service.admin;

import com.xzm.course.manager.admin.TeacherManager;
import com.xzm.course.model.entity.TeacherEntity;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.BaseService;
import com.xzm.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService extends BaseService {

    @Autowired
    private TeacherManager teacherManager;
    
    public ResultVO getPageCount(String departmentName, String name) {
        return result(teacherManager.getPageCount(departmentName, name));
    }

    public ResultVO getPage(Integer index, String departmentName, String name) {
        return result(teacherManager.getPage(index, departmentName, name));
    }

    public ResultVO get(Integer id) {
        TeacherEntity entity = teacherManager.get(id);
        if (entity == null) {
            return failedResult("教师Id: " + id + "不存在!");
        }

        entity.setPassword("");

        return result(entity);
    }

    public ResultVO update(TeacherEntity entity) {
        TeacherEntity originEntity = teacherManager.get(entity.getId());
        if (originEntity == null) {
            return failedResult("教师Id: " + entity.getId() + "不存在!");
        }
        if (teacherManager.getDepartmentById(entity.getDepartmentId()) == null) {
            return failedResult("所属系Id: " + entity.getDepartmentId() + "不存在!");
        }

        if (entity.getPassword().equals("")) {
            entity.setPassword(originEntity.getPassword());
        } else {
            entity.setPassword(entity.getPassword());
        }

        teacherManager.update(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (teacherManager.get(id) == null) {
            return failedResult("教师Id: " + id + "不存在!");
        }
        if (teacherManager.hasCourse(id)) {
            return failedResult("此教师还有教授的课程未被删除");
        }

        teacherManager.delete(id);
        return result("删除成功");
    }

    public ResultVO create(TeacherEntity entity) {
        if (teacherManager.get(entity.getId()) != null) {
            return failedResult("教师Id: " + entity.getId() + "已存在!");
        }
        if (teacherManager.getDepartmentById(entity.getDepartmentId()) == null) {
            return failedResult("所属系Id: " + entity.getDepartmentId() + "不存在!");
        }

        entity.setPassword(entity.getPassword());

        teacherManager.create(entity);
        return result("添加成功");
    }

    public ResultVO listName() {
        return result(teacherManager.listName());
    }
}
