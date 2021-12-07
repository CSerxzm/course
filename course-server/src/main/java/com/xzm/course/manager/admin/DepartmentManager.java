package com.xzm.course.manager.admin;

import com.xzm.course.dao.DepartmentDAO;
import com.xzm.course.dao.MajorDAO;
import com.xzm.course.dao.TeacherDAO;
import com.xzm.course.manager.BaseManager;
import com.xzm.course.model.entity.DepartmentEntity;
import com.xzm.course.model.vo.response.IdNameVO;
import com.xzm.course.model.vo.response.table.DepartmentItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentManager extends BaseManager {

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private TeacherDAO teacherDAO;

    @Autowired
    private MajorDAO majorDAO;

    public Integer getPageCount(String name) {
        int count = departmentDAO.count(name);
        return calcPageCount(count, DepartmentDAO.PAGE_SIZE);
    }

    @Transactional
    public List<DepartmentItemVO> getPage(Integer index, String namePart) {
        List<DepartmentItemVO> departmentItemList = new ArrayList<>();
        List<DepartmentEntity> departmentList = departmentDAO.getPage(index, namePart);

        for (DepartmentEntity department : departmentList) {
            int id = department.getId();
            String name = department.getName();
            int majorCount = majorDAO.countByDepartmentId(id);
            int teacherCount = teacherDAO.countByDepartmentId(id);

            departmentItemList.add(new DepartmentItemVO(id, name, majorCount, teacherCount));
        }

        return departmentItemList;
    }

    public DepartmentEntity get(Integer id) {
        return departmentDAO.get(id);
    }

    public int create(DepartmentEntity entity) {
        return departmentDAO.insert(entity);
    }

    public int update(DepartmentEntity entity) {
        return departmentDAO.update(entity);
    }

    public int delete(Integer id) {
        return departmentDAO.delete(id);
    }

    public boolean hasMajor(Integer id) {
        return majorDAO.countByDepartmentId(id) > 0;
    }

    public boolean hasTeacher(Integer id) {
        return teacherDAO.countByDepartmentId(id) > 0;
    }

    public List<IdNameVO> listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<DepartmentEntity> entityList = departmentDAO.listName();
        for (DepartmentEntity entity : entityList) {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        }

        return voList;
    }
}
