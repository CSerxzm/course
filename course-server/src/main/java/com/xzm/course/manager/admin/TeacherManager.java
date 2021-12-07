package com.xzm.course.manager.admin;

import com.xzm.course.dao.CourseDAO;
import com.xzm.course.dao.DepartmentDAO;
import com.xzm.course.dao.TeacherDAO;
import com.xzm.course.manager.BaseManager;
import com.xzm.course.model.entity.DepartmentEntity;
import com.xzm.course.model.entity.TeacherEntity;
import com.xzm.course.model.vo.response.IdNameVO;
import com.xzm.course.model.vo.response.table.TeacherItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherManager extends BaseManager {

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private TeacherDAO teacherDAO;

    @Autowired
    private CourseDAO courseDAO;

    public Integer getPageCount(String departmentName, String name) {
        int count = teacherDAO.count(departmentName, name);
        return calcPageCount(count, TeacherDAO.PAGE_SIZE);
    }

    public List<TeacherItemVO> getPage(Integer index, String departmentName, String name) {
        return teacherDAO.getPage(index, departmentName, name);
    }

    public TeacherEntity get(Integer id) {
        return teacherDAO.get(id);
    }

    public int create(TeacherEntity entity) {
        return teacherDAO.insert(entity);
    }

    public int update(TeacherEntity entity) {
        return teacherDAO.update(entity);
    }

    public int delete(Integer id) {
        return teacherDAO.delete(id);
    }

    public boolean hasCourse(Integer teacherId) {
        return courseDAO.countByTeacherId(teacherId) > 0;
    }

    public DepartmentEntity getDepartmentById(Integer id) {
        return departmentDAO.get(id);
    }

    public List<IdNameVO> listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<TeacherEntity> entityList = teacherDAO.listName();
        for (TeacherEntity entity : entityList) {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        }
        return voList;
    }
}
