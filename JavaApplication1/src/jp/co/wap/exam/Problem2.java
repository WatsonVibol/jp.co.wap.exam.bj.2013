package jp.co.wap.exam;

import java.util.List;
import jp.co.wap.exam.lib.Interval;

public class Problem2 {

		final int MINUTE_UNIT_NUM = 24 * 60 + 1;
		final int MAX_INTERVAL_SIZE = 10000 + 1;

		public long time_0, time_1, time_2, time;

		public int getMaxWorkingTime(List<Interval> intervals) {

				time = System.nanoTime();
				
				int[] task_next = new int[MAX_INTERVAL_SIZE];
				int[] task_end = new int[MAX_INTERVAL_SIZE];

				int[] task_stack = new int[MINUTE_UNIT_NUM];
				int[] maxWorkingTime = new int[MINUTE_UNIT_NUM];
				
				time_0 += System.nanoTime() - time;
				time = System.nanoTime();
				
				
				int tasks_num = 1;
				for (Interval element : intervals) {
						int task_id = tasks_num;
						int beginMinuteUnit = element.getBeginMinuteUnit();
						task_end[task_id] = element.getEndMinuteUnit();
						task_next[task_id] = task_stack[beginMinuteUnit];
						task_stack[beginMinuteUnit] = task_id;
						tasks_num++;
				}
				
				time_1 += System.nanoTime()  - time;
				time = System.nanoTime();

				int currentMaxWorkingTime = 0;
				for (int i = 0; i < MINUTE_UNIT_NUM; i++) {
						int task_id = task_stack[i];
						while(task_id != 0){
								int endMinuteUnit = task_end[task_id];
								int endMinuteUnitWorkingTime = currentMaxWorkingTime + (endMinuteUnit - i);
								if (endMinuteUnitWorkingTime > maxWorkingTime[endMinuteUnit]) {
										maxWorkingTime[endMinuteUnit] = endMinuteUnitWorkingTime;
								}
								task_id = task_next[task_id];
						}
						if (currentMaxWorkingTime < maxWorkingTime[i]) {
								currentMaxWorkingTime = maxWorkingTime[i];
						}
				}

				time_2 += System.nanoTime()  - time;


				return currentMaxWorkingTime;
		}
}
