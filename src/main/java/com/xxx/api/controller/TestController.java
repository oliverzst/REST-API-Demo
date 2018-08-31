package com.xxx.api.controller;

import com.xxx.api.entity.Student;
import com.xxx.api.exception.ApiStatus;
import com.xxx.api.exception.UserException;
import com.xxx.api.response.ApiResponse;
import com.xxx.api.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * 控制器demo
 *
 * @author zhangst
 * @version 2018-5-3
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/test")
public class TestController {

    /**
     * URI路径方式传递参数
     */
    @RequestMapping(path = "/students/{classId}/{limit}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse getStudentsByClassId(@Max(value = 30, message = "classId should be integer less than 30") @NotNull @PathVariable(value = "classId") Integer classId,
                                            @Min(value = 1, message = "limit should be integer greater than 1") @PathVariable(value = "limit") Integer limit) {
        //业务代码 略
        List<String> students = new ArrayList();
        return new ApiResponse(students);
    }

    /**
     * QueryParam方式传递参数
     */
    @RequestMapping(path = "/students", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse getStudentsByClassIdAndName(@Max(value = 30, message = "classId should be integer less than 30") @NotNull @RequestParam(value = "classId") Integer classId,
                                                   @Size(max = 10, min = 2, message = "name should have between 2 and 10 characters") @NotNull(message = "name should not be null") @RequestParam(value = "name") String name,
                                                   @RequestParam(value = "cursor") String cursor,
                                                   @Min(value = 1, message = "limit should be integer greater than 1") @RequestParam(value = "limit") Integer limit) {
        //业务代码 略
        List<String> students = new ArrayList();
        //带游标的返回值
        return new ApiResponse(students, cursor);
    }

    /**
     * 请求参数带时间
     * @param datetime datetime format 2018-05-04T00:06:49Z
     */
    @RequestMapping(path = "/students/datetime", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse getStudentsByTime(@Max(value = 30, message = "classId should be integer less than 30") @RequestParam(value = "classId") Integer classId,
                                         @NotNull(message = "datetime should not be null") @RequestParam(value = "datetime") String datetime,
                                         @RequestParam(value = "cursor") String cursor,
                                         @Min(value = 1, message = "limit should be integer greater than 1") @RequestParam(value = "limit") Integer limit) {
        //时间格式化
        Instant instant = DateUtils.String2Instant(datetime);
        String strDate = DateUtils.Instant2String(instant);
        return new ApiResponse(strDate, cursor);
    }

    /**
     * 请求参数带数组类型
     * @param courses array type request param
     */
    @RequestMapping(path = "/students/courses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse getStudentsByCourse(@RequestParam(value = "courses") List<String> courses) {
        //业务代码 略
        List<String> students = new ArrayList();
        return new ApiResponse(students);
    }

    /**
     * 添加学生信息
     * @param student request body use JSON format
     * @exception IOException throw io exception
     */
    @RequestMapping(path = "/student", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse addStudent(@Valid @RequestBody Student student) throws IOException{
        //业务代码 略
        return new ApiResponse(ApiStatus.SUCCESS);
    }

    /**
     * 批量添加学生信息
     * @param studentList request body use JSON format
     */
    @RequestMapping(path = "/students", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse addStudents(@Valid @RequestBody List<Student> studentList){
        //业务代码 略
        return new ApiResponse(ApiStatus.SUCCESS);
    }

    /**
     * 全量修改学生信息
     * @param id request param
     * @param student request body
     */
    @RequestMapping(path = "/students/id", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse updateStudentById(@NotNull @RequestParam(value = "id") Integer id,
                                         @Valid @RequestBody Student student) {
        //业务代码 略
        return new ApiResponse(ApiStatus.SUCCESS);
    }

    /**
     * 增量修改学生信息
     * @exception UserException throw user exception
     */
    @RequestMapping(path = "/students/id/name", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse updateStudentNameById(@NotNull @RequestParam(value = "id") Integer id,
                                             @Size(max = 10, min = 2, message = "name should have between 2 and 10 characters") @NotNull @RequestParam(value = "name") String name) {
        //判断是否name已存在，若存在，抛出UserException异常
        Boolean isNameExist = true;
        if (isNameExist) {
            throw new UserException(ApiStatus.NAME_EXIST);
        }
        return new ApiResponse(ApiStatus.SUCCESS);
    }

    /**
     * 删除学生信息
     * @exception UserException throw user exception
     */
    @RequestMapping(path = "/students/name", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse deleteStudentByName(@Size(max = 10, min = 2, message = "name should have between 2 and 10 characters") @NotNull @RequestParam(value = "name") String name) {
        //判断是否name不存在,若不存在，抛出UserException异常
        Boolean isNameNotExist = true;
        if (isNameNotExist) {
            throw new UserException(ApiStatus.NAME_NOT_EXIST);
        }
        return new ApiResponse(ApiStatus.SUCCESS);
    }

    /**
     * 用户登录
     * @exception UserException throw user exception
     */
    @RequestMapping(path = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse login(@NotNull @RequestParam(value = "username") String username,
                             @NotNull @RequestParam(value = "password") String password) {
        //判断用户名密码是否正确
        Boolean isUserNameOrPasswordRight = false;
        if (isUserNameOrPasswordRight) {
            throw new UserException(ApiStatus.USERNAME_OR_PASSWORD_ERROR);
        }
        return new ApiResponse(ApiStatus.SUCCESS);
    }

}
