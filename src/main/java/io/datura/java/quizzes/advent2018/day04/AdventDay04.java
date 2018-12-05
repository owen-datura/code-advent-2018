package io.datura.java.quizzes.advent2018.day04;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdventDay04 {
	private enum GuardState {
		ARRIVED, FELL_ASLEEP, AWOKE, UNKNOWN
	};

	public static void main(String[] args) {
		try {
			List<String> activities = loadIntelFile();
			evalGuardSleepTimes(activities);
		} catch (IOException ioe) {
			System.err.println("Encountered an error when handling input file, aborting.");
			System.exit(1);
		}
	}

	public static Map<Integer, Long> evalGuardSleepTimes(List<String> activities) {
		Integer guard = null;
		GuardState guardState = GuardState.UNKNOWN;
		LocalDateTime lastEvent = null;

		Map<Integer, Long> sleepTimesByGuard = new HashMap<>();

		for (String activity : activities) {
			LocalDateTime tmpTime = SortIntelFile.parseInputDate(activity);

			guardState = getStateFromActivity(activity);
			switch (guardState) {
			case ARRIVED:
				// a new guard's on duty, so reset the clock and the id
				guard = getCurrentGuardFromActivity(activity);
				lastEvent = tmpTime;
				break;
			case FELL_ASLEEP:
				lastEvent = tmpTime;
				break;
			case AWOKE:
				// the guard had fallen asleep, so determine how long they'd been sleeping
				Long slept = getTimeAsleep(lastEvent, tmpTime);

				// make a record of time spent asleep
				if (sleepTimesByGuard.containsKey(guard)) {
					Long timeSlept = sleepTimesByGuard.get(guard);
					timeSlept += slept;
					sleepTimesByGuard.put(guard, timeSlept);
				} else {
					sleepTimesByGuard.put(guard, slept);
				}

				// reset the clock
				lastEvent = tmpTime;
				break;
			case UNKNOWN:
			default:
				System.err.println("Unknown state change for line: " + activity);
				break;
			}
		}
		
		return Collections.unmodifiableMap(sleepTimesByGuard);
	}

	public static Long getTimeAsleep(LocalDateTime ts, LocalDateTime ta) {
		return ChronoUnit.MINUTES.between(ts, ta);
	}

	public static Integer getCurrentGuardFromActivity(String activity) {
		int guardIdStart = activity.indexOf('#') + 1;
		String guardId = activity.substring(guardIdStart, activity.indexOf(' ', guardIdStart));
		try {
			return Integer.parseInt(guardId);
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			return 0;
		}
	}

	public static GuardState getStateFromActivity(String activity) {
		if (activity.endsWith("begins shift"))
			return GuardState.ARRIVED;
		else if (activity.endsWith("falls asleep"))
			return GuardState.FELL_ASLEEP;
		else if (activity.endsWith("wakes up"))
			return GuardState.AWOKE;
		else
			return GuardState.UNKNOWN;
	}

	private static List<String> loadIntelFile() throws IOException {
		try {
			ClassLoader cl = SortIntelFile.class.getClassLoader();
			Path input = Paths.get(cl.getResource("intel-sorted.txt").toURI());
			return Files.readAllLines(input);
		} catch (URISyntaxException urie) {
			throw new IOException("Encountered an error when handling intel file.");
		}
	}
}
