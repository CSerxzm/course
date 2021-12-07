package com.xzm.course.manager.student;

import com.xzm.course.dao.StudentCourseDAO;
import com.xzm.course.manager.BaseManager;
import com.xzm.course.model.vo.response.table.StudentExamItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamManager extends BaseManager {

    @Autowired
    private StudentCourseDAO studentCourseDAO;

    public List<StudentExamItemVO> listStudentExam(Integer studentId) {
        return studentCourseDAO.listStudentExam(studentId);
    }
}
