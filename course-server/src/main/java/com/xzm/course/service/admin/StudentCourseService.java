package com.xzm.course.service.admin;

import com.xzm.course.manager.admin.StudentCourseManager;
import com.xzm.course.model.entity.CourseEntity;
import com.xzm.course.model.entity.StudentCourseEntity;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseService extends BaseService {

    @Autowired
    private StudentCourseManager studentCourseManager;

    public ResultVO getPageCount(String className, String courseName, String studentName) {
        return result(studentCourseManager.getPageCount(className, courseName, studentName));
    }

    public ResultVO getPage(Integer index, String className, String courseName, String studentName) {
        return result(studentCourseManager.getPage(index, className, courseName, studentName));
    }

    public ResultVO get(Integer id) {
        StudentCourseEntity entity = studentCourseManager.get(id);
        if (entity == null) {
            return failedResult("学生选课Id: " + id + "不存在!");
        }

        return result(entity);
    }

    public ResultVO update(StudentCourseEntity entity) {
        StudentCourseEntity originEntity = studentCourseManager.get(entity.getId());
        if (originEntity == null) {
            return failedResult("学生选课Id: " + entity.getId() + "不存在!");
        }
        if (!originEntity.getCourseId().equals(entity.getCourseId())) {
            return failedResult("课程Id被篡改");
        }
        if (!originEntity.getStudentId().equals(entity.getStudentId())) {
            return failedResult("学生Id被篡改");
        }

        studentCourseManager.update(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer id) {
        StudentCourseEntity entity = studentCourseManager.get(id);
        if (entity == null) {
            return failedResult("学生选课Id: " + id + "不存在!");
        }

        studentCourseManager.delete(entity);
        return result("删除成功");
    }

    public ResultVO create(StudentCourseEntity entity) {
        if (studentCourseManager.get(entity.getId()) != null) {
            return failedResult("学生选课Id: " + entity.getId() + "已存在!");
        }
        if (studentCourseManager.getStudentById(entity.getStudentId()) == null) {
            return failedResult("所属学生Id: " + entity.getStudentId() + "不存在!");
        }
        if (studentCourseManager.getByCourseIdAndStudentId(entity.getCourseId(), entity.getStudentId()) != null) {
            return failedResult("学生已经选修此课程");
        }
        CourseEntity course = studentCourseManager.getCourseById(entity.getCourseId());
        if (course == null) {
            return failedResult("所属课程Id: " + entity.getCourseId() + "不存在!");
        }
        if (course.getSelectedCount() >= course.getMaxSize()) {
            return failedResult("课容量已满");
        }
        if (!studentCourseManager.inSameDepartment(entity.getCourseId(), entity.getStudentId())) {
            return failedResult("课程与学生不在同一教学系");
        }
        if (!course.getGrade().equals(studentCourseManager.getStudentGradeById(entity.getStudentId()))) {
            return failedResult("课程与学生不在同一年级");
        }

        studentCourseManager.create(entity);
        return result("添加成功");
    }
}
