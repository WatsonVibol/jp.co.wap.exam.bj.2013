package jp.co.wap.exam;

import java.util.List;
import jp.co.wap.exam.lib.Interval;

public class Problem1 {

		public int getMaxIntervalOverlapCount(List<Interval> intervals) {
				if (intervals == null || intervals.isEmpty()) {
						return 0;
				}

				final int MINUTE_UNIT_NUM = (24 << 8) + 1;

				int[] tasksBegin = new int[MINUTE_UNIT_NUM];
				int[] tasksEnd = new int[MINUTE_UNIT_NUM];

				for (Interval element : intervals) {
						int beginMinuteUnit = (element.getBeginHour() << 8) + element.getBeginMinute();
						int endMinuteUnit = (element.getEndHour() << 8) + element.getEndMinute();
						tasksBegin[beginMinuteUnit]++;
						tasksEnd[endMinuteUnit]++;
				}
				tasksBegin[MINUTE_UNIT_NUM - 1] += tasksBegin[0];

				int maxIntervalOverlapCount = 0;
				int curIntervalOverlapCount = 0;
				for (int i = 0; i < MINUTE_UNIT_NUM;) {
						curIntervalOverlapCount += tasksBegin[i];
						if (curIntervalOverlapCount > maxIntervalOverlapCount) {
								maxIntervalOverlapCount = curIntervalOverlapCount;
						}
						curIntervalOverlapCount -= tasksEnd[i];
						i++;
				}
				return maxIntervalOverlapCount;
		}
}