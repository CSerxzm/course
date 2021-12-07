package com.xzm.course.manager.student;

import com.xzm.course.dao.StudentDAO;
import com.xzm.course.manager.BaseManager;
import com.xzm.course.model.entity.StudentEntity;
import com.xzm.course.model.vo.response.StudentInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InfoManager extends BaseManager {

    @Autowired
    private StudentDAO studentDAO;

    public StudentInfoVO getStudentInfoByStudentId(Integer studentId) {
        return studentDAO.getStudentInfoById(studentId);
    }

    public StudentEntity getStudentById(Integer studentId) {
        return studentDAO.get(studentId);
    }

    public int updateStudent(StudentEntity entity) {
        return studentDAO.update(entity);
    }
}
