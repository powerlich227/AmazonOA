/*
 * LeetCode 1041
 * 
On an infinite plane, a robot initially stands at (0, 0) and faces north. The robot can receive one of three instructions:

"G": go straight 1 unit;
"L": turn 90 degrees to the left;
"R": turn 90 degrees to the right.
The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.

Example 1:

Input: instructions = "GGLLGG"
Output: true
Explanation: The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
Example 2:

Input: instructions = "GG"
Output: false
Explanation: The robot moves north indefinitely.
Example 3:

Input: instructions = "GL"
Output: true
Explanation: The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
 
*/

public class RobotBoundedInCircle {
	public static boolean isRobotBounded(String instruction) {
		int dir = 0; // north = 0, east = 1, south = 2, west = 3
		int[] cur = {0, 0};
		int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		for (int i = 0; i < instruction.length(); i++) {
			char c = instruction.charAt(i);
			if (c == 'G') {
				cur[0] += move[dir][0];
				cur[1] += move[dir][1];
			}
			else if (c == 'L')
				dir = (dir - 1 + 4) % 4;
			else 
				dir = (dir + 1) % 4; 
		}
		return dir != 0 || (cur[0] == 0 && cur[1] == 0);
	}
	public static void main(String[] args) {
		String instruction1 = "GGLLGG";
		String instruction2 = "GG";
		String instruction3 = "GL";
		
		System.out.println(isRobotBounded(instruction1));
		System.out.println(isRobotBounded(instruction2));
		System.out.println(isRobotBounded(instruction3));
	}
}
