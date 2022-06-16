package com.lig.convert.controller;

import com.lig.convert.vo.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: com.lig.convert.controller-> TestController
 * @description:
 * @author: lig
 * @createDate: 2022-06-16 20:26
 * @version: 1.0
 * @todo:
 */
@RestController
public class TestController {

    @RequestMapping("/test1")
    private ResponseEntity test1(@RequestBody List<Order> orderList) {
        return new ResponseEntity(orderList, HttpStatus.OK);
    }

}
