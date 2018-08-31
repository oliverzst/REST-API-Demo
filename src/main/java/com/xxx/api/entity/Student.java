package com.xxx.api.entity;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

/**
 * 学生实体类
 *
 * @author zhangst
 * @version 2018-5-5
 */
@Data
public class Student{

    public Student(){
    }

    @NotNull
    private Integer id;

    @NotNull
    @Size(min=2, max=10)
    private String name;

    @Min(1)
    private Integer age;

    @NotNull
    @Max(30)
    private Integer classId;

}
