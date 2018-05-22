package com.school.driving.controller;

import com.school.driving.model.msg.ErrorCode;
import com.school.driving.model.msg.Message;
import com.school.driving.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Api(value="用户 controller",description="用户操作",tags={"用户操作接口"})
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;


    @ApiOperation("接口描述")
    @ApiImplicitParam(name = "id",value = "用户ID",required = false,dataType = "Long",paramType = "path")
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public Message test(@PathVariable Long id){
        System.out.println(id);
        return new Message(ErrorCode.SUCCESS,testService.queryUserService());
    }
}
