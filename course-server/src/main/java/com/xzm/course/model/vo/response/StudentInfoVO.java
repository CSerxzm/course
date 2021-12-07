package com.xzm.course.model.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class StudentInfoVO {
    private String number;
    private String name;
    private String departmentName;
    private String majorName;
    private String className;
    private String phone;
    private String email;
    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Integer sex;
}
