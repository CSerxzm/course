package com.xzm.course.manager.admin;

import com.xzm.course.dao.ClassDAO;
import com.xzm.course.dao.MajorDAO;
import com.xzm.course.dao.StudentDAO;
import com.xzm.course.manager.BaseManager;
import com.xzm.course.model.entity.ClassEntity;
import com.xzm.course.model.entity.MajorEntity;
import com.xzm.course.model.vo.response.IdNameVO;
import com.xzm.course.model.vo.response.table.MajorItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassManager extends BaseManager {

    @Autowired
    private MajorDAO majorDAO;

    @Autowired
    private ClassDAO classDAO;

    @Autowired
    private StudentDAO studentDAO;

    public Integer getPageCount(String departmentName, String majorName, String name) {
        int count = classDAO.count(departmentName, majorName, name);
        return calcPageCount(count, ClassDAO.PAGE_SIZE);
    }

    public List<MajorItemVO> getPage(Integer index, String departmentName, String majorName, String name) {
        return classDAO.getPage(index, departmentName, majorName, name);
    }

    public ClassEntity get(Integer id) {
        return classDAO.get(id);
    }

    public int create(ClassEntity entity) {
        return classDAO.insert(entity);
    }

    public int update(ClassEntity entity) {
        return classDAO.update(entity);
    }

    public int delete(Integer id) {
        return classDAO.delete(id);
    }

    public MajorEntity getMajorById(Integer majorId) {
        return majorDAO.get(majorId);
    }

    public boolean hasStudent(Integer classId) {
        return studentDAO.countByClassId(classId) > 0;
    }

    public List<IdNameVO> listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<ClassEntity> entityList = classDAO.listName();
        for (ClassEntity entity : entityList) {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        }

        return voList;
    }
}
