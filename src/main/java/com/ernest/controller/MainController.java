package com.ernest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Ernest
 * @Description TODO
 * @date 2022/7/17-10:11
 */
@Controller
public class MainController {

    @GetMapping("/index")
    public String toIndex(){
        return "index";
    }

    @GetMapping("/backindex")
    public String toBackIndex(){
        return "redirect:allUser";
    }

    @GetMapping("/allUser")
    public String allUser(){
        return "back_userTable";
    }

    @GetMapping("/addUser")
    public String addUser(){
        return "back_addUser";
    }

    @GetMapping("/allOrder")
    public String allOrder(){
        return "back_orderTable";
    }

    @GetMapping("/addOrder")
    public String addOrder(){
        return "back_addOrder";
    }
}
