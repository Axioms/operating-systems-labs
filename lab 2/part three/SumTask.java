/**
 * Fork/join parallelism in Java
 *
 * Figure 4.18
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts  - Tenth Edition
 * Copyright John Wiley & Sons - 2018
 */

import java.util.concurrent.*;

public class SumTask extends RecursiveTask<Integer>
{
    static final int SIZE = 10000;
    static final int THRESHOLD = 1000;

    private int begin;
    private int end;
    private int[] array;

    public SumTask(int begin, int end, int[] array) {
        this.begin = begin;
        this.end = end;
        this.array = array;
    }

    protected Integer compute() {
        if (end - begin < THRESHOLD) {
            // conquer stage 
            int sum = 0;
            for (int i = begin; i <= end; i++)
                sum += array[i];

            return sum;
        }
        else {
            // divide stage 
            int mid = begin + (end - begin) / 2;
            
            SumTask leftTask = new SumTask(begin, mid, array);
            SumTask rightTask = new SumTask(mid + 1, end, array);

            leftTask.fork();
            rightTask.fork();

            return rightTask.join() + leftTask.join();
        }
    }

	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		int[] array = new int[SIZE];
        int[] arrayOne = new int[SIZE];
        int[] arrayTwo = new int[SIZE];

		// create SIZE random integers between 0 and 9
		java.util.Random rand = new java.util.Random();

		for (int i = 0; i < SIZE; i++) {
			array[i] = rand.nextInt(10);
            arrayOne[i] = (int)Math.pow(rand.nextInt(10), 2);
            arrayTwo[i] = (int)Math.pow(rand.nextInt(10), 3);
		}		
		
		// use fork-join parallelism to sum the array
		SumTask task = new SumTask(0, SIZE-1, array);
        SumTask taskOne = new SumTask(0, SIZE-1, arrayOne);
        SumTask taskTwo = new SumTask(0, SIZE-1, arrayTwo);

		int sum = pool.invoke(task);
        int sumOne = pool.invoke(taskOne);
        int sumTwo = pool.invoke(taskTwo);

		System.out.println("The sum(i) is " + sum);
        System.out.println("The sum(i^3) is " + sumOne);
        System.out.println("The sum(i^3) is " + sumTwo);
	}
}

