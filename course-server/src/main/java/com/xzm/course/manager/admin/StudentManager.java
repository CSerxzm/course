package com.xzm.course.manager.admin;

import com.xzm.course.dao.ClassDAO;
import com.xzm.course.dao.StudentCourseDAO;
import com.xzm.course.dao.StudentDAO;
import com.xzm.course.manager.BaseManager;
import com.xzm.course.model.entity.ClassEntity;
import com.xzm.course.model.entity.StudentEntity;
import com.xzm.course.model.vo.response.IdNameVO;
import com.xzm.course.model.vo.response.table.StudentItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentManager extends BaseManager {

    @Autowired
    private ClassDAO classDAO;

    @Autowired
    private StudentCourseDAO studentCourseDAO;

    @Autowired
    private StudentDAO studentDAO;

    public Integer getPageCount(String majorName, String className, String name) {
        int count = studentDAO.count(majorName, className, name);
        return calcPageCount(count, StudentDAO.PAGE_SIZE);
    }

    public List<StudentItemVO> getPage(Integer index, String majorName, String className, String name) {
        return studentDAO.getPage(index, majorName, className, name);
    }

    public StudentEntity get(Integer id) {
        return studentDAO.get(id);
    }

    public int create(StudentEntity entity) {
        return studentDAO.insert(entity);
    }

    public int update(StudentEntity entity) {
        return studentDAO.update(entity);
    }

    public int delete(Integer id) {
        return studentDAO.delete(id);
    }

    public ClassEntity getClassById(Integer classId) {
        return classDAO.get(classId);
    }

    public boolean hasStudentCourse(Integer studentId) {
        return studentCourseDAO.countByStudentId(studentId) > 0;
    }

    public List<IdNameVO> listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<StudentEntity> entityList = studentDAO.listName();
        for (StudentEntity entity : entityList) {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        }

        return voList;
    }
}
