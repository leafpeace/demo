package com.example.demo.controller;

import com.example.demo.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class IndexController {

    public static List<Person> list = new ArrayList<>();
    public static Integer currentId = 1;

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model) {
        return "index";
    }

    @GetMapping("list")
    @ResponseBody
    public List<Person> index() {
        return list;
    }

    @PostMapping("add")
    @ResponseBody
    public Person add(String name, String email) {
        Person p = new Person();
        p.setId(getNextId());
        p.setEmail(email);
        p.setName(name);
        list.add(p);
        return p;
    }

    @GetMapping("get")
    @ResponseBody
    public Person get(int id) {
        Optional<Person> optional = list.stream().filter((e) -> e.getId() == id).findFirst();
        return optional != null && optional.isPresent() ? optional.get() : null;
    }

    @DeleteMapping("remove")
    @ResponseBody
    public Person remove(int id) {
        Optional<Person> optional = list.stream().filter((e) -> e.getId() == id).findFirst();
        return optional != null && optional.isPresent() ? optional.get() : null;
    }

    public synchronized int getNextId() {
        int y = currentId;
        currentId ++;
        return y;
    }

}
