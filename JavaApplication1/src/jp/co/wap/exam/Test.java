package jp.co.wap.exam;

import java.util.ArrayList;
import jp.co.wap.exam.lib.Interval;

import java.util.List;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Test {

		public static void main(String args[]) {
				testPersistentQueue();
		}

		public static void testPersistentQueue() {
				PersistentQueue<Integer> q0 = new PersistentQueue<Integer>();

				assertThat(q0.size(), 0);
				int i = 0;
				try {
						q0.peek();
						i = 1;
				} catch (NoSuchElementException e) {
						i = 2;
				}
				assertThat(i, 2);

				PersistentQueue<Integer> q1 = q0.enqueue(2);
				assertThat(q0.size(), 0);
				assertThat(q1.size(), 1);
				assertThat(q1.peek(), 2);

				PersistentQueue<Integer> q2 = q1.enqueue(1);
				assertThat(q2.size(), 2);
				assertThat(q2.peek(), 2);

				PersistentQueue<Integer> q3 = q2.dequeue();
				assertThat(q3.size(), 1);
				assertThat(q3.peek(), 1);
				assertThat(q2.size(), 2);
				assertThat(q2.peek(), 2);
				assertThat(q1.size(), 1);
				assertThat(q1.peek(), 2);

				try {
						PersistentQueue<Integer> q5 = q3.enqueue(null);
						i = 3;
				} catch (IllegalArgumentException e) {
						i = 4;
				}
				assertThat(i, 4);

		}

		public static void testProblem2Usage() {
				Problem2 p = new Problem2();
				List<Interval> tests = new ArrayList<Interval>();
				tests.add(new Interval("06:00", "08:30"));
				tests.add(new Interval("09:00", "11:30"));
				tests.add(new Interval("12:30", "14:00"));
				int t = p.getMaxWorkingTime(tests);
				assertThat(t, is(390));
		}

		public static void testProblem1Usage() {
				Problem1 p = new Problem1();

				Interval interval1 = new Interval("08:00", "12:00");
				Interval interval2 = new Interval("06:00", "09:00");
				Interval interval3 = new Interval("11:00", "13:30");

				List<Interval> figure1 = Arrays.asList(interval1, interval2, interval3);
				assertThat(p.getMaxIntervalOverlapCount(figure1), is(2));

				List<Interval> figure2 = Arrays.asList(
						new Interval("09:00", "12:30"),
						new Interval("06:00", "09:30"),
						new Interval("12:00", "14:30"),
						new Interval("10:00", "10:30"),
						new Interval("11:00", "13:30"));
				assertThat(p.getMaxIntervalOverlapCount(figure2), is(3));


				List<Interval> figure3 = Arrays.asList(
						new Interval("00:00", "12:30"),
						new Interval("21:00", "24:00"),
						new Interval("24:00", "24:00"));
				assertThat(p.getMaxIntervalOverlapCount(figure3), is(3));
		}

		public static void assertThat(int value1, int value2) {
				if (value1 != value2) {
						System.out.println("Error value1 is " + value1 + " value2 is " + value2);
				}
		}

		public static int is(int value) {
				return value;
		}
}
