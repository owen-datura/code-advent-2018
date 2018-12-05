package io.datura.java.quizzes.advent2018.day04;

public class SleepActivityChart {
	private GuardTuple[] chart;

	public SleepActivityChart() {
		chart = new GuardTuple[GuardActivity.INTERVAL_LENGTH];
	}

	public void importHistogram(Integer guardId, Integer[] histogramData) {
		for (int i = 0; i < histogramData.length; i++) {
			// the chart doesn't contain a record for the selected minute, so add whatever's
			// first
			if (chart[i] == null) {
				GuardTuple gt = new GuardTuple(guardId);
				gt.setMinute(i);
				gt.setCount(histogramData[i]);
				chart[i] = gt;
			} else {
				int curMax = chart[i].getCount();
				int newMax = histogramData[i];
				if (newMax > curMax) {
					GuardTuple gt = new GuardTuple(guardId);
					gt.setMinute(i);
					gt.setCount(newMax);
					chart[i] = gt;
				}
			}
		}
	}
	
	public void printConsolidatedRecord() {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < chart.length; i++) {
			output.append(String.format("%02d", i));
			output.append("\t");
			GuardTuple gt = chart[i];
			output.append(gt.getGuardId());
			output.append("\t");
			output.append(gt.getCount());
			output.append("\n");
		}
		System.out.println(output.toString());
	}
}

class GuardTuple {
	private final int guardId;
	private int minute;
	private int count;

	public GuardTuple(int guardId) {
		this.guardId = guardId;
	}

	public int getGuardId() {
		return guardId;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}