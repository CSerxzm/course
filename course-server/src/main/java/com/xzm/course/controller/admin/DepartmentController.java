package com.xzm.course.controller.admin;

import com.xzm.course.config.themis.annotation.Admin;
import com.xzm.course.controller.BaseController;
import com.xzm.course.model.entity.DepartmentEntity;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.admin.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Admin(Admin.DEPARTMENT_MANAGE)
@RequestMapping("/admin/department")
@RestController
public class DepartmentController extends BaseController {

    @Autowired
    private DepartmentService departmentService;
    
    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return departmentService.get(id);
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated DepartmentEntity entity) {
        return departmentService.create(entity);
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return departmentService.delete(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated DepartmentEntity entity) {
        return departmentService.update(entity);
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String name) {
        return departmentService.getPageCount(name);
    }

    @RequestMapping("/page")
    public ResultVO getPage(String name) {
        return departmentService.getPage(1, name);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String name) {
        return departmentService.getPage(index, name);
    }

    @Admin
    @RequestMapping("/names")
    public ResultVO listName() {
        return departmentService.listName();
    }
}
