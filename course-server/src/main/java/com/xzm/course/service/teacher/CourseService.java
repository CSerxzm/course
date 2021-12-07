package com.xzm.course.service.teacher;

import com.xzm.course.manager.teacher.CourseManager;
import com.xzm.course.model.entity.CourseEntity;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.model.vo.response.table.TeacherCourseItemVO;
import com.xzm.course.service.BaseService;
import com.xzm.course.util.LessonTimeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teacher_courseService")
public class CourseService extends BaseService {

    @Autowired
    private CourseManager courseManager;

    @Autowired
    private LessonTimeConverter lessonTimeConverter;

    public ResultVO get(Integer id) {
        CourseEntity entity = courseManager.get(id);
        if (entity == null) {
            return failedResult("课程Id: " + id + "不存在!");
        }

        return result(entity);
    }

    public ResultVO list() {
        Integer teacherId = getUserId();

        List<TeacherCourseItemVO> list = courseManager.listTeacherCourse(teacherId);
        for (TeacherCourseItemVO vo : list) {
            vo.setTime(lessonTimeConverter.covertTimePart(vo.getTime()));
        }

        return result(list);
    }

    public ResultVO create(CourseEntity entity) {
        if (courseManager.get(entity.getId()) != null) {
            return failedResult("课程Id: " + entity.getId() + "已存在!");
        }
        entity.setTeacherId(getUserId());
        courseManager.create(entity);
        return result("添加成功");
    }

    public ResultVO update(CourseEntity entity) {
        CourseEntity origin = courseManager.get(entity.getId());
        if (origin == null) {
            return failedResult("课程Id: " + entity.getId() + "不存在!");
        }
        entity.setSelectedCount(origin.getSelectedCount());

        courseManager.update(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (courseManager.get(id) == null) {
            return failedResult("课程Id: " + id + "不存在!");
        }
        if (courseManager.hasStudentCourse(id)) {
            return failedResult("还有学生未退选此课程");
        }

        courseManager.delete(id);
        return result("删除成功");
    }
}
