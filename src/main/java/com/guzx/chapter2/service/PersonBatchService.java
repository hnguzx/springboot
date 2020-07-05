package com.guzx.chapter2.service;

import com.guzx.chapter2.pojo.Person;

import java.util.List;

public interface PersonBatchService {
    public int insertPersons(List<Person> personList);
}
