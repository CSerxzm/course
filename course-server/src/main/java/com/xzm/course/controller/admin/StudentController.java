package com.xzm.course.controller.admin;

import com.xzm.course.config.themis.annotation.Admin;
import com.xzm.course.controller.BaseController;
import com.xzm.course.model.entity.StudentEntity;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.admin.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Admin(Admin.STUDENT_MANAGE)
@RequestMapping("/admin/student")
@RestController
public class StudentController extends BaseController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return studentService.get(id);
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated StudentEntity entity) {
        return studentService.create(entity);
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return studentService.delete(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated StudentEntity entity) {
        return studentService.update(entity);
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String majorName, String className, String name) {
        return studentService.getPageCount(majorName, className, name);
    }

    @RequestMapping("/page")
    public ResultVO getPage(String majorName, String className, String name) {
        return studentService.getPage(1, majorName, className, name);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String majorName, String className, String name) {
        return studentService.getPage(index, majorName, className, name);
    }

    @Admin
    @RequestMapping("/names")
    public ResultVO listName() {
        return studentService.listName();
    }
}
