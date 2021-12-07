package com.xzm.course.service.student;

import com.xzm.course.manager.student.ExamManager;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService extends BaseService {

    @Autowired
    private ExamManager examManager;

    public ResultVO list() {
        return result(examManager.listStudentExam(getUserId()));
    }
}
