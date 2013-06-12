package jp.co.wap.exam;

import java.util.ArrayList;
import jp.co.wap.exam.lib.Interval;

import java.util.List;
import java.util.Random;

public class TestMe {

		public static void main(String args[]) {

				Random random = new Random(31415926);

				 System.out.println("Test Exam 1");

				 int checksum_p1 = 0;
				 int checksum_p2 = 0;
				 long time_p1 = 0;
				 long time_p2 = 0;
				 Problem1 p1 = new Problem1();
				 Problem2 p2 = new Problem2();
				 List<Interval> intervals = new ArrayList<Interval>();

				 for (int t = 0; t <= 100; t++) {
				 intervals.clear();
				 for (int k = 0; k < 10000; k++) {
				 int i = random.nextInt(24 * 60 + 1);
				 int j = random.nextInt(24 * 60 + 1);
				 int max = i > j ? i : j;
				 int min = i > j ? j : i;
				 intervals.add(new Interval(String.format("%02d:%02d", min / 60, min % 60), String.format("%02d:%02d", max / 60, max % 60)));

				 if (random.nextInt(100) == 1) {
				 long start_time = System.nanoTime();
				 int return_value = p1.getMaxIntervalOverlapCount(intervals);
				 time_p1 += System.nanoTime() - start_time;
				 checksum_p1 ^= return_value;

				 start_time = System.nanoTime();
				 return_value = p2.getMaxWorkingTime(intervals);
				 time_p2 += System.nanoTime() - start_time;
				 checksum_p2 ^= return_value;
				 }
				 }
				 System.out.println(t + "/100 finished");

				 }
				 System.out.println(String.format("t1: %10d(<2600ms?) t2: %10d(<2800ms?) s1: %10d(=3206?) s2: %10d(=261?)", time_p1 / 1000000, time_p2 / 1000000, checksum_p1, checksum_p2));

				System.out.println("Test Exam 2");
				int checksum_pq = 0;
				long time_pq = 0;

				PersistentQueue<Integer> pq = new PersistentQueue<Integer>();
				PersistentQueue<Integer> pq_old = pq;

				random.setSeed(31415926);
				for (int t = 0; t <= 100; t++) {

						for (int tt = 0; tt < 2000000; tt++) {
								int d = random.nextInt();
								int i = random.nextInt(100);
								
								long start_time = System.nanoTime();
								int size = pq_old.size();
								if (size == 0) {
										pq = pq_old.enqueue(d);
								} else {
										if (i <= 15) {
												pq = new PersistentQueue<Integer>();
										} else if (i <= 70) {
												pq = pq_old.enqueue(d);
										} else {
												pq = pq_old.dequeue();
										}
										checksum_pq ^= (int) (Integer) pq_old.peek();
								}
								time_pq += System.nanoTime() - start_time;
								pq_old = pq;
						}


						System.out.println(t + "/100 finished");
				}

				System.out.println(String.format("t: %10d(<8000ms?) s: %10d(=1806364857?)", time_pq / 1000000, checksum_pq));
		}
}
