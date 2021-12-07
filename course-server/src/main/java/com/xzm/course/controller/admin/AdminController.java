package com.xzm.course.controller.admin;

import com.xzm.course.config.themis.annotation.Admin;
import com.xzm.course.controller.BaseController;
import com.xzm.course.model.entity.AdminEntity;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Admin(Admin.ADMIN_MANAGE)
@RequestMapping("/admin/admin")
@RestController
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return adminService.get(id);
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated AdminEntity entity) {
        return adminService.create(entity);
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return adminService.delete(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated AdminEntity entity) {
        return adminService.update(entity);
    }

    @GetMapping()
    public ResultVO list() {
        return adminService.list();
    }
}
