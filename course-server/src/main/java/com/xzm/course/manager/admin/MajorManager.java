package com.xzm.course.manager.admin;

import com.xzm.course.dao.ClassDAO;
import com.xzm.course.dao.DepartmentDAO;
import com.xzm.course.dao.MajorDAO;
import com.xzm.course.manager.BaseManager;
import com.xzm.course.model.entity.DepartmentEntity;
import com.xzm.course.model.entity.MajorEntity;
import com.xzm.course.model.vo.response.IdNameVO;
import com.xzm.course.model.vo.response.table.MajorItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MajorManager extends BaseManager {

    @Autowired
    private MajorDAO majorDAO;

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private ClassDAO classDAO;

    public Integer getPageCount(String departmentName, String name) {
        int count = majorDAO.count(departmentName, name);
        return calcPageCount(count, MajorDAO.PAGE_SIZE);
    }

    public List<MajorItemVO> getPage(Integer index, String departmentName, String name) {
        return majorDAO.getPage(index, departmentName, name);
    }

    public MajorEntity get(Integer id) {
        return majorDAO.get(id);
    }

    public int create(MajorEntity entity) {
        return majorDAO.insert(entity);
    }

    public int update(MajorEntity entity) {
        return majorDAO.update(entity);
    }

    public int delete(Integer id) {
        return majorDAO.delete(id);
    }

    public boolean hasClass(Integer majorId) {
        return classDAO.countByMajorId(majorId) > 0;
    }

    public DepartmentEntity getDepartmentById(Integer id) {
        return departmentDAO.get(id);
    }

    public List<IdNameVO> listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<MajorEntity> entityList = majorDAO.listName();
        for (MajorEntity entity : entityList) {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        }

        return voList;
    }
}
