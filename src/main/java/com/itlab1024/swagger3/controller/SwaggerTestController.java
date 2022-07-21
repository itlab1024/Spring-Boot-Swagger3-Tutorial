package com.itlab1024.swagger3.controller;

import com.itlab1024.swagger3.vo.TestVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/api")
public class SwaggerTestController {

    @ApiOperation(value = "通过ID获取")
    @GetMapping("/{id}")
    public TestVo get(@PathVariable String id){
        return new TestVo("name1");
    }
}
