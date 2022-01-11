package com.alexeyefr.main;

import jobs.CalcSumOfArrayJob;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static final int COUNT = 500000000;
    public static final int MIN_COUNT = 20;

    public static void main(String[] args) {
	    int[] ar = new int[COUNT];
//---------------------------------------------------------
        System.out.println("Fill array by random ints...");
        for (int i = 0; i < COUNT; i++) {
            ar[i] = getRandomInt(0, 100);
        }
        System.out.println("Array is filled");
        System.out.println();
//---------------------------------------------------------
        System.out.println("Calculate sum of array elements in for loop.");
        long sum = 0;
        for (int elem: ar) {
            sum += elem;
        }
        System.out.println("Sum: " + sum);
        System.out.println();
//----------------------------------------------------------
        System.out.println("Calculate sum of array elements by ForkJoin.");
        ForkJoinPool pool = new ForkJoinPool(8);
        long parallelSum = pool.invoke(new CalcSumOfArrayJob(ar, 0, COUNT));
        System.out.println("Parallel Sum: " + parallelSum);
        System.out.println();
    }

    private static int getRandomInt(int a, int b) {
        return (a + (int) (Math.random() * b));
    }
}
