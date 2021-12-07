package com.xzm.course.model.vo.request;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class StudentInfoFormVO {
    private String password;

    @Pattern(regexp = "^1[0-9]{10}$", message = "手机号格式不正确")
    private String phone;

    private String address;

    @Email(message = "邮箱格式不正确")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @Range(min = 0, max = 1)
    private Integer sex;
}
