package io.datura.java.quizzes.advent2018.day04;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
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
			// determine which guard sleeps the most
			Map<Integer, GuardActivity> parsedSleepSchedule = evalGuardSleepTimes(activities);
			GuardActivity record = GuardActivity.getSleepyGuard(parsedSleepSchedule.values());
			String output = String.format("Determined that the guard with ID %d slept the most.", record.getGuardId());
			System.out.println(output);
			// determine the interval where the guard's most likely to be asleep
			record.printSleepTimes();
		} catch (IOException ioe) {
			System.err.println("Encountered an error when handling input file, aborting.");
			System.exit(1);
		}
	}

	public static Map<Integer, GuardActivity> evalGuardSleepTimes(List<String> activities) {
		Integer guard = null;
		GuardState guardState = GuardState.UNKNOWN;
		LocalDateTime lastEvent = null;

		Map<Integer, GuardActivity> sleepTimesByGuard = new HashMap<>();

		for (String activity : activities) {
			LocalDateTime tmpTime = SortIntelFile.parseInputDate(activity);

			guardState = getStateFromActivity(activity);
			switch (guardState) {
			case ARRIVED:
				guard = getCurrentGuardFromActivity(activity);
				if (sleepTimesByGuard.containsKey(guard)) {
					GuardActivity record = sleepTimesByGuard.get(guard);
					record.incrementTimesOnDuty();
				} else {
					GuardActivity record = new GuardActivity(guard);
					record.incrementTimesOnDuty();
					sleepTimesByGuard.put(guard, record);
				}

				lastEvent = tmpTime;
				break;
			case FELL_ASLEEP:
				lastEvent = tmpTime;
				break;
			case AWOKE:
				// make a record of the time that was spent asleep
				GuardActivity record = sleepTimesByGuard.get(guard);
				record.addSleepTime(lastEvent, tmpTime);
				lastEvent = tmpTime;
				break;
			case UNKNOWN:
			default:
				System.err.println("Unknown state change for line " + activity);
				break;
			}
		}

		return Collections.unmodifiableMap(sleepTimesByGuard);
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
