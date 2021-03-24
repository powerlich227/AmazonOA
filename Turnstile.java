/*
A warehouse has one loading dock that workers use to load and unload goods.

Warehouse workers carrying the goods arrive at the loading dock at different times. They form two queues, a "loading" queue and an "unloading" queue. Within each queue, the workers are ordered by the time they arrive at the dock.

The arrival time (in minutes) array stores the minute the worker arrives at the loading dock. The direction array stores whether the worker is "loading" or "unloading", a value of 0 means loading and 1 means unloading. Loading/unloading takes 1 minute.

When a worker arrives at the loading dock, if no other worker is at the dock at the same time, then the worker can use the dock.

If a "loading" worker and an "unloading" worker arrive at the dock at the same time, then we decide who can use the dock with these rules:

if the loading dock was not in use in the previous minute, then the unloading worker can use the dock.
if the loading dock was just used by another unloading worker, then the unloading worker can use the dock.
if the loading dock was just used by another loading worker, then the loading worker can use the dock.
Return an array of the time (in minute) each worker uses the dock.

Examples
Example 1:
Input:
time = [0, 0, 1, 6] direction = [0, 1, 1, 0]

Output:
[2, 0, 1, 6]

Explanation:
At time 0, worker 0 and 1 want to use the dock. Worker 0 wants to load and worker 1 wants to unload. The dock was not used in the previous minute, so worker 1 unload first.
At time 1, workers 0 and 2 want to use the rock. Worker 2 wants to unload, and at the previous minute the dock was used to unload, so worker 2 uses the dock.
At time 2, worker 0 is the only worker at the dock, so he uses the dock.
At time 6, worker 3 arrives at the empty dock and uses the dock.
We return [2, 0, 1, 6].

 */
import java.util.*;
public class Turnstile {
	public int[] getTimes(int[] times, int[] directions) {
		int[] res = new int[times.length];
		
		List<Integer> entry = new ArrayList<>();
		List<Integer> exit = new LinkedList<>();
		for (int i = 0; i < times.length; i++) {
			if (directions[i] == 0)
				entry.add(i);
			else
				exit.add(i);
		}
		
		int curTime = -1;
		int preDir = 1;
		while (!entry.isEmpty() && !exit.isEmpty()) {
			int curEntry = entry.get(0);
			int curExit = exit.get(0);
			int curEnterTime = Math.max(times[curEntry], curTime);
			int curExitTime = Math.max(times[curExit], curTime);
			if (curEnterTime < curExitTime) {
				res[curEntry] = curEnterTime;
				preDir = 0;
				entry.remove(0);
				curTime = curEnterTime + 1;
			}
			else if (curEnterTime > curExitTime) {
				res[curExit] = curExitTime;
				preDir = 1;
				entry.remove(0);
				curTime = curExitTime + 1;
			}
			else {
				if (preDir == 0) {
					res[curEntry] = curEnterTime;
					preDir = 0;
					entry.remove(0);
					curTime = curExitTime + 1;
				}
				else {
					res[curExit] = curExitTime;
					preDir = 1;
					entry.remove(0);
					curTime = curExitTime + 1;
				}
			}
		}
		while (!entry.isEmpty()) {
			int curEntry = entry.remove(0);
			int curEnterTime = Math.max(times[curEntry], curTime);
			res[curEnterTime] = curEnterTime;
			curTime = curEnterTime + 1;
		}
		while (!exit.isEmpty()) {
			int curExit = entry.remove(0);
			int curExitTime = Math.max(times[curExit], curTime);
			res[curExit] = curExitTime;
			curTime = curExitTime + 1;
		}
		return res;
	}
}
