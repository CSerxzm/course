package com.xzm.course.manager;

import com.xzm.course.dao.AdminDAO;
import com.xzm.course.dao.StudentDAO;
import com.xzm.course.dao.TeacherDAO;
import com.xzm.course.model.bo.AuthInfoBO;
import com.xzm.course.model.constant.UserType;
import com.xzm.course.model.entity.StudentEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserManager extends BaseManager {
    private final AdminDAO adminDAO;
    private final TeacherDAO teacherDAO;
    private final StudentDAO studentDAO;

    public UserManager(AdminDAO adminDAO, TeacherDAO teacherDAO, StudentDAO studentDAO) {
        this.adminDAO = adminDAO;
        this.teacherDAO = teacherDAO;
        this.studentDAO = studentDAO;
    }

    public AuthInfoBO getAuthInfoByUsername(String username, Integer userType) {
        if (userType == UserType.STUDENT) {
            return AuthInfoBO.fromStudent(studentDAO.getByNumber(username));
        } else if (userType == UserType.TEACHER) {
            return AuthInfoBO.fromTeacher(teacherDAO.getByNumber(username));
        } else if (userType == UserType.ADMIN) {
            return AuthInfoBO.fromAdmin(adminDAO.getByUsername(username));
        }

        return null;
    }

    public void updateStudentLastLoginTime(String number) {
        StudentEntity entity = studentDAO.getByNumber(number);
        if (entity == null) {
            return;
        }

        entity.setLastLoginTime(new Date());
        studentDAO.update(entity);
    }
}
