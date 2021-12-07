package com.xzm.course.service.admin;

import com.xzm.course.manager.admin.CourseManager;
import com.xzm.course.model.bo.CourseItemBO;
import com.xzm.course.model.entity.CourseEntity;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.model.vo.response.table.CourseItemVO;
import com.xzm.course.service.BaseService;
import com.xzm.course.util.LessonTimeConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService extends BaseService {

    @Autowired
    private CourseManager courseManager;

    @Autowired
    private LessonTimeConverter lessonTimeConverter;

    public ResultVO getPageCount(String departmentName, String teacherName, String name) {
        return result(courseManager.getPageCount(departmentName, teacherName, name));
    }

    public ResultVO getPage(Integer index, String departmentName, String teacherName, String name) {
        List<CourseItemBO> boList = courseManager.getPage(index, departmentName, teacherName, name);
        List<CourseItemVO> voList = new ArrayList<>(boList.size());

        for (CourseItemBO bo : boList) {
            CourseItemVO vo = new CourseItemVO();
            BeanUtils.copyProperties(bo, vo);
            vo.setTime(lessonTimeConverter.covertTimePart(bo.getTime()));
            voList.add(vo);
        }

        return result(voList);
    }

    public ResultVO get(Integer id) {
        CourseEntity entity = courseManager.get(id);
        if (entity == null) {
            return failedResult("课程Id: " + id + "不存在!");
        }

        return result(entity);
    }

    public ResultVO update(CourseEntity entity) {
        CourseEntity origin = courseManager.get(entity.getId());
        if (origin == null) {
            return failedResult("课程Id: " + entity.getId() + "不存在!");
        }
        if (courseManager.getTeacherById(entity.getTeacherId()) == null) {
            return failedResult("授课教师Id: " + entity.getTeacherId() + "不存在!");
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

    public ResultVO create(CourseEntity entity) {
        if (courseManager.get(entity.getId()) != null) {
            return failedResult("课程Id: " + entity.getId() + "已存在!");
        }
        if (courseManager.getTeacherById(entity.getTeacherId()) == null) {
            return failedResult("授课教师Id: " + entity.getTeacherId() + "不存在!");
        }

        courseManager.create(entity);
        return result("添加成功");
    }

    public ResultVO listName() {
        return result(courseManager.listName());
    }
}
