package com.guzx.chapter2.controller;

import com.guzx.chapter2.pojo.Person;
import com.guzx.chapter2.service.PersonBatchService;
import com.guzx.chapter2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonBatchService personBatchService;

    @RequestMapping("/getPerson")
    @ResponseBody
    public Person getPerson(Long id){
        return personService.getPerson(id);
    }

    @RequestMapping("/insertPerson")
    @ResponseBody
    public Map<String, Object> insertPerson(String userName, String note){
        Person person = new Person();
        person.setUserName(userName);
        person.setNote(note);
        // 返回结果会回填主键，返回插入的条数
        int update = personService.insertPerson(person);
        Map<String,Object> result = new HashMap<>();
        result.put("success",update==1);
        result.put("person",person);
        return result;
    }

    @RequestMapping("/insertPersons")
    @ResponseBody
    public Map<String, Object> insertPersons(String userName1, String note1,String userName2, String note2){
        Person person1 = new Person();
        person1.setUserName(userName1);
        person1.setNote(note1);

        Person person2 = new Person();
        person2.setUserName(userName2);
        person2.setNote(note2);
        // 返回结果会回填主键，返回插入的条数
        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);

        int inserts =personBatchService.insertPersons(personList);
        Map<String,Object> result = new HashMap<>();
        result.put("success",inserts>1);
        result.put("person",personList);
        return result;
    }
}
