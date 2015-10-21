/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.domain;

import java.util.Random;

/**
 *
 * @author Serguei
 */
public class Question {

    private int x;
    private int y;
    private int z;
    private int answer;
    private static Question instance;

    private Question() {
    }

    public void genQuestion() {
        Random rand = new Random();
        x = rand.nextInt(9) + 1;
        y = rand.nextInt(9) + 1;
        z = rand.nextInt(2) + 1;
        answer = (x + y) * z;
    }

    @Override
    public String toString() {
        return "(" + x + "+" + y + ")*" + z + "=";
    }

    public int getAnswer() {
        return answer;
    }

    public static Question getInstance() {
        if (instance == null) {
            instance = new Question();
        }
        return instance;
    }

}
