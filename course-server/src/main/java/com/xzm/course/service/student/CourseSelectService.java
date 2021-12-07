package com.xzm.course.service.student;

import com.xzm.course.manager.student.CourseSelectManager;
import com.xzm.course.model.bo.StudentCourseSelectItemBO;
import com.xzm.course.model.entity.CourseEntity;
import com.xzm.course.model.entity.StudentCourseEntity;
import com.xzm.course.model.entity.StudentEntity;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.model.vo.response.table.StudentCourseSelectItemVO;
import com.xzm.course.service.BaseService;
import com.xzm.course.util.LessonTimeConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseSelectService extends BaseService {
    
    @Autowired
    private CourseSelectManager courseSelectManager;

    @Autowired
    private LessonTimeConverter lessonTimeConverter;

    public ResultVO getPageCount(String courseName, String teacherName) {
        Integer studentId = getUserId();
        return result(courseSelectManager.getPageCount(studentId, courseName, teacherName));
    }

    public ResultVO getPage(Integer index, String courseName, String teacherName) {
        Integer studentId = getUserId();

        List<StudentCourseSelectItemBO> boList = courseSelectManager.getPage(index, studentId, courseName, teacherName);
        List<StudentCourseSelectItemVO> voList = new ArrayList<>(boList.size());

        for (StudentCourseSelectItemBO bo : boList) {
            StudentCourseSelectItemVO vo = new StudentCourseSelectItemVO();
            BeanUtils.copyProperties(bo, vo);
            vo.setTime(lessonTimeConverter.covertTimePart(bo.getTime()));
            voList.add(vo);
        }

        return result(voList);
    }

    public ResultVO create(Integer courseId) {
        Integer studentId = getUserId();
        StudentEntity student = courseSelectManager.getStudentById(studentId);
        CourseEntity course = courseSelectManager.getCourseById(courseId);
        if (student == null) {
            return failedResult("学生Id:" + studentId + "不存在!");
        }
        if (course == null) {
            return failedResult("课程Id:" + courseId + "不存在!");
        }
        if (!courseSelectManager.inSameDepartment(courseId, studentId)) {
            return failedResult("学生不能选择非教学系的课程!");
        }
        if (course.getSelectedCount() >= course.getMaxSize()) {
            return failedResult("课容量已满!");
        }
        if (courseSelectManager.getStudentCourseByCourseIdAndStudentId(courseId, studentId) != null) {
            return failedResult("学生已选修此课程!");
        }
        if (!courseSelectManager.getStudentGradeById(student.getId()).equals(course.getGrade())) {
            return failedResult("学生与课程不在同一年级");
        }
        String timePart = splitTimePart(course.getTime());
        if (courseSelectManager.countStudentCourseSelectedByTimePart(studentId, timePart) > 0) {
            return failedResult("上课时间冲突!");
        }

        StudentCourseEntity studentCourse = new StudentCourseEntity();
        studentCourse.setCourseId(courseId);
        studentCourse.setStudentId(studentId);
        courseSelectManager.create(studentCourse);

        return result("选课成功");
    }

    private String splitTimePart(String time) {
        String[] spilt = time.split("-");
        return spilt[0] + "-" + spilt[1];
    }
}
