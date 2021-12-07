package com.xzm.course.service.student;

import com.xzm.course.manager.student.CourseManager;
import com.xzm.course.model.entity.StudentCourseEntity;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("student_courseService")
public class CourseService extends BaseService {

    @Autowired
    private CourseManager courseManager;

    public ResultVO list() {
        Integer studentId = getUserId();
        return result(courseManager.listStudentCourseSelected(studentId));
    }

    public ResultVO delete(Integer studentCourseId) {
        Integer studentId = getUserId();
        StudentCourseEntity studentCourse = courseManager.getStudentCourseById(studentCourseId);
        if (studentCourse == null) {
            return failedResult("学生选课Id:" + studentCourseId + "不存在");
        }
        if (!studentCourse.getStudentId().equals(studentId)) {
            return failedResult("此课程非此学生所选!");
        }
        if (studentCourse.getDailyScore() != null || studentCourse.getExamScore() != null || studentCourse.getScore() != null) {
            return failedResult("学生已获得成绩, 不能退选");
        }

        courseManager.deleteStudentCourse(studentCourse);
        return result("退选成功");
    }
}
