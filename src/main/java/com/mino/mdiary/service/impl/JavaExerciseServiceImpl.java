package com.mino.mdiary.service.impl;

import com.mino.mdiary.exercise.java.dsaa.chapter1.Test1_14;
import com.mino.mdiary.service.JavaExerciseService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class JavaExerciseServiceImpl implements JavaExerciseService {

    @Autowired
    private Test1_14 test1_14;

    @Override
    public String bookDataStructureAndAlgorithmInJava() {
        test1_14.test_1_14();
        return "";
    }
}