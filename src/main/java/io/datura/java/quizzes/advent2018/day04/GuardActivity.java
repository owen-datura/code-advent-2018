package io.datura.java.quizzes.advent2018.day04;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class GuardActivity {
	private final int guardId;
	private int timesOnDuty = 1;
	private Map<LocalDate, BitSet> sleepTimes = new LinkedHashMap<>();

	public static int INTERVAL_LENGTH = 60;

	public GuardActivity(int guardId) {
		this.guardId = guardId;
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

	public int addSleepTime(LocalDateTime wentToSleep, LocalDateTime awoke) {
		// we'll store the times based on when they awoke, not when they fell asleep
		LocalDate wakeTime = LocalDate.from(awoke);
		BitSet sleepBits = createSleepTimeBitSet(wentToSleep, awoke);
		if (sleepTimes.containsKey(wakeTime))
			sleepTimes.get(wakeTime).or(sleepBits);
		else
			sleepTimes.put(wakeTime, sleepBits);

		return sleepTimes.get(wakeTime).cardinality();
	}

	public int getSumTimeAsleep() {
		int sum = 0;
		for (BitSet t : sleepTimes.values()) {
			sum += t.cardinality();
		}
		return sum;
	}

	public void printSleepTimes() {
		StringBuilder dutyChart = new StringBuilder();
		dutyChart.append(String.format("Duty Chart for Guard #%d\n", getGuardId()));
		dutyChart.append("===\n");

		for (Entry<LocalDate, BitSet> dutyDates : sleepTimes.entrySet()) {
			// print the date
			dutyChart.append(dutyDates.getKey());
			dutyChart.append(":\t");

			BitSet timeAsleep = dutyDates.getValue();

			for (int i = 0; i < INTERVAL_LENGTH; i++) {
				dutyChart.append(timeAsleep.get(i) ? '#' : '.');
			}

			dutyChart.append(" [");
			dutyChart.append(timeAsleep.cardinality());
			dutyChart.append("]\n");
		}

		
		System.out.println(dutyChart.toString());
	}

	public static BitSet createSleepTimeBitSet(LocalDateTime wentToSleep, LocalDateTime awoke) {
		LocalDateTime start = getAdjustedStartTime(wentToSleep, awoke);
		int startMinute = start.getMinute();
		int endMinute = awoke.getMinute();

		if (startMinute > endMinute)
			throw new RuntimeException("Parsing error when handling time differential, can't continue.");

		BitSet minutes = new BitSet(INTERVAL_LENGTH);
		for (; startMinute < endMinute; startMinute++) {
			minutes.set(startMinute);
		}

		return minutes;
	}

	public static LocalDateTime getAdjustedStartTime(LocalDateTime start, LocalDateTime end) {
		// the input data requires us to consider the midnight hours
		// but can overlap into the previous day. to simplify the
		// calculation, we'll need to round off the time to start at 12:00
		return start.getDayOfMonth() < end.getDayOfMonth() ? end.with(LocalTime.MIN) : start;
	}

	public static GuardActivity getSleepyGuard(Collection<GuardActivity> guardRecords) {
		GuardActivity max = null;
		for (GuardActivity a : guardRecords) {
			if (max == null) {
				max = a;
				continue;
			}

			if (a.getSumTimeAsleep() > max.getSumTimeAsleep())
				max = a;
		}

		return max;
	}

	public static void createHistogram(Integer[] times) {
		StringBuilder output = new StringBuilder();

		for (int i = 0, sz = times.length; i < sz; i++) {
			// print the index with leading zeros as appropriate
			output.append(String.format("%02d", i));
			output.append(" [");
			output.append(String.format("%03d", times[i]));
			output.append("]:\t");

			for (int j = 0; j < times[i]; j++) {
				output.append("#");
			}
			output.append("\n");
		}

		System.out.println(output);
	}

	public Integer[] createHistogramValues() {
		int[] timeAsleep = new int[INTERVAL_LENGTH];
		for (BitSet b : sleepTimes.values()) {
			for (int i = 0; i < INTERVAL_LENGTH; i++) {
				if (b.get(i))
					timeAsleep[i]++;
			}
		}

		return Arrays.stream(timeAsleep).boxed().toArray(Integer[]::new);
	}
}
