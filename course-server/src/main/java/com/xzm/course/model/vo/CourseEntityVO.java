package com.xzm.course.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class CourseEntityVO {
    public static final String ID = "course_id";
    public static final String TEACHER_ID = "course_teacher_id";
    public static final String NAME = "course_name";
    public static final String GRADE = "course_grade";
    public static final String TIME = "course_time";
    public static final String LOCATION = "course_location";
    public static final String CREDIT = "course_credit";
    public static final String TYPE = "course_type";
    public static final String SELECTED_COUNT = "course_selected_count";
    public static final String MAX_SIZE = "course_max_size";
    public static final String EXAM_DATE = "course_exam_date";
    public static final String EXAM_LOCATION = "course_exam_location";

    @NotNull
    private Integer id;

    private Integer teacherId;

    @NotBlank(message = "课程名不能为空")
    private String name;

    @NotNull
    @Range(min = 1980, max = 2999, message = "年级范围必须在1980-2999之间")
    private Integer grade;

    @Pattern(regexp = "[1-7]-[1-9]-[1-4]", message = "课程最长时间为4节")
    private String time;

    @NotBlank(message = "上课地点不能为空")
    private String location;

    @NotNull
    @Range(min = 1, max = 20, message = "学分必须在1-20之间")
    private Integer credit;

    private Integer selectedCount;

    @NotNull
    @Range(min = 0, message = "容量不能为负数")
    //@TableField(MAX_SIZE)
    private Integer maxSize;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date examDate;

    private String examLocation;
}
