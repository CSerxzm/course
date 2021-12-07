package com.xzm.course.service.admin;

import com.xzm.course.manager.admin.StudentManager;
import com.xzm.course.model.entity.StudentEntity;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.BaseService;
import com.xzm.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends BaseService {

    @Autowired
    private StudentManager studentManager;

    public ResultVO getPageCount(String majorName, String className, String name) {
        return result(studentManager.getPageCount(majorName, className, name));
    }

    public ResultVO getPage(Integer index, String majorName, String className, String name) {
        return result(studentManager.getPage(index, majorName, className, name));
    }

    public ResultVO get(Integer id) {
        StudentEntity entity = studentManager.get(id);
        if (entity == null) {
            return failedResult("学生Id: " + id + "不存在!");
        }

        entity.setPassword("");

        return result(entity);
    }

    public ResultVO update(StudentEntity entity) {
        StudentEntity origin = studentManager.get(entity.getId());
        if (origin == null) {
            return failedResult("学生Id: " + entity.getId() + "不存在!");
        }
        if (studentManager.getClassById(entity.getClassId()) == null) {
            return failedResult("所属班级Id: " + entity.getClassId() + "不存在!");
        }

        if (entity.getPassword().equals("")) {
            entity.setPassword(origin.getPassword());
        } else {
            entity.setPassword(entity.getPassword());
        }

        studentManager.update(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (studentManager.get(id) == null) {
            return failedResult("学生Id: " + id + "不存在!");
        }
        if (studentManager.hasStudentCourse(id)) {
            return failedResult("此学生还有未退选课程");
        }

        studentManager.delete(id);
        return result("删除成功");
    }

    public ResultVO create(StudentEntity entity) {
        if (studentManager.get(entity.getId()) != null) {
            return failedResult("学生Id: " + entity.getId() + "已存在!");
        }
        if (studentManager.getClassById(entity.getClassId()) == null) {
            return failedResult("所属班级Id: " + entity.getClassId() + "不存在!");
        }

        studentManager.create(entity);
        return result("添加成功");
    }

    public ResultVO listName() {
        return result(studentManager.listName());
    }
}
