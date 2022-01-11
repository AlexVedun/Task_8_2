package jobs;

import com.alexeyefr.main.Main;

import java.util.concurrent.RecursiveTask;

public class CalcSumOfArrayJob extends RecursiveTask<Long> {
    private final int a;
    private final int b;
    int[] arr;

    public CalcSumOfArrayJob(int[] arr, int a, int b) {
        this.a = a;
        this.b = b;
        this.arr = arr;
    }

    @Override
    protected Long compute() {
        if ((b - a) <= Main.MIN_COUNT) {
            long sum = 0;
            for (int i = a; i < b; i++) {
                sum += arr[i];
            }

            return sum;
        } else {
            int mid = (a + b) / 2;
            CalcSumOfArrayJob leftPart = new CalcSumOfArrayJob(arr, a, mid);
            leftPart.fork();
            CalcSumOfArrayJob rightPart = new CalcSumOfArrayJob(arr, mid, b);

            return (leftPart.join() + rightPart.compute());
        }
    }
}
