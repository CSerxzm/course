package com.xzm.course.service.teacher;

import com.xzm.course.manager.teacher.GradeManager;
import com.xzm.course.model.entity.StudentCourseEntity;
import com.xzm.course.model.vo.TeacherGradeVO;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeService extends BaseService {

    @Autowired
    private GradeManager gradeManager;

    public ResultVO getPageCount(String courseName, String studentName) {
        Integer teacherId = getUserId();
        return result(gradeManager.getTeacherGradePageCount(teacherId, courseName, studentName));
    }

    public ResultVO getPage(Integer index, String courseName, String studentName) {
        Integer teacherId = getUserId();
        return result(gradeManager.getTeacherGradePage(index, teacherId, courseName, studentName));
    }

    public ResultVO update(TeacherGradeVO vo) {
        Integer teacherId = getUserId();
        StudentCourseEntity studentCourse = gradeManager.getStudentCourseById(vo.getStudentCourseId());
        if (studentCourse == null) {
            return failedResult("学生选课Id:" + vo.getStudentCourseId() + "不存在");
        }
        if (!gradeManager.getCourseById(studentCourse.getCourseId()).getTeacherId().equals(teacherId)) {
            return failedResult("此课程非您教授");
        }

        BeanUtils.copyProperties(vo, studentCourse);

        gradeManager.updateStudentCourse(studentCourse);
        return result("打分成功");
    }

    public ResultVO get(Integer studentCourseId) {
        Integer teacherId = getUserId();
        StudentCourseEntity studentCourse = gradeManager.getStudentCourseById(studentCourseId);
        if (studentCourse == null) {
            return failedResult("学生选课Id:" + studentCourseId + "不存在");
        }
        if (!gradeManager.getCourseById(studentCourse.getCourseId()).getTeacherId().equals(teacherId)) {
            return failedResult("此课程非您教授");
        }

        TeacherGradeVO vo = new TeacherGradeVO();
        BeanUtils.copyProperties(studentCourse, vo);
        vo.setStudentCourseId(studentCourseId);

        return result(vo);
    }
}
