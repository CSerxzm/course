package com.xzm.course.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xzm.course.model.bo.CourseItemBO;
import com.xzm.course.model.bo.StudentCourseSelectItemBO;
import com.xzm.course.model.entity.CourseEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMapper extends BaseMapper<CourseEntity> {
    Integer count(@Param("departmentName")String departmentName, @Param("teacherName")String teacherName, @Param("name")String name);

    IPage<CourseItemBO> getPage(IPage<CourseItemBO> page, @Param("departmentName")String departmentName, @Param("teacherName")String teacherName, @Param("name")String name);

    Integer countStudentCanSelect(@Param("studentId")Integer studentId, @Param("departmentId")Integer departmentId, @Param("grade")Integer grade, @Param("courseName")String courseName, @Param("teacherName")String teacherName);

    IPage<StudentCourseSelectItemBO> getStudentCanSelectPage(IPage<StudentCourseSelectItemBO> page,@Param("studentId") Integer studentId,@Param("departmentId")  Integer departmentId,@Param("grade") Integer grade,@Param("courseName") String courseName,@Param("teacherName") String teacherName);

    Integer getDepartmentIdById(Integer courseId);
}
