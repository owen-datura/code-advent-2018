package io.datura.java.quizzes.advent2018.day04;

public class GuardActivity {
	private final int guardId;
	private int timesOnDuty = 1;
	private long timeAsleep = 0l;

	public GuardActivity(int guardId) {
		this.guardId = guardId;
	}

	public GuardActivity(int guardId, long timeAsleep) {
		this.guardId = guardId;
		this.timeAsleep = timeAsleep;
	}

	public int getGuardId() {
		return guardId;
	}

	public void incrementTimesOnDuty() {
		timesOnDuty++;
	}

	public int getTimesOnDuty() {
		return timesOnDuty;
	}

	public long getTimeAsleep() {
		return timeAsleep;
	}

	public void addToTimeAsleep(long len) {
		timeAsleep += len;
	}
}
