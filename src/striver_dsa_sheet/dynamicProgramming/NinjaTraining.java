package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * ninja is planning this 'N' days training. each day he can perform
 * any one of 3 activities. each activity has some merit pionts, on each day.
 * As ninja has to improve all his skills, he cannot do the same activity in two consecuive days.
 * 
 * How can he get the max points.
 * A1 = activity1
 * A2 = activity 2
 * A3 = activity 3
 *        A1 A2 A2
 * day 1 {1, 2, 5}
 * day 2 {3, 1, 1}
 * day 3 {3, 3, 3}
 * 
 * Maximize the merit points.
 * 
 * ans {5+3+3} = 11
 * 
 * </pre>
 */
public class NinjaTraining {
	public static void main(String[] args) {
		int days[][] = { { 1, 2, 5 }, { 3, 1, 1 }, { 3, 3, 3 } };
		int days2[][] = { { 1, 50, 2 }, { 1, 100, 5 } };
		System.out.println(recursion(days, -1, days.length - 1));
		// dp
		int dp[][] = new int[days.length + 1][days[0].length + 1];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				dp[i][j] = -1;
			}
		}
//		System.out.println(recursionDP(days, 2, days.length - 1, dp));
		System.out.println(recursionDPTabulation(days, dp));
		System.out.println(recursionNoDPTabulation(days));
	}

	private static int recursion(int days[][], int lastTask, int dayIndex) {
		int max = Integer.MIN_VALUE;
		// assume base case as last day = 0th day
		if (dayIndex == 0) {
			for (int i = 0; i < days[dayIndex].length; i++) {
				if (i != lastTask) {
					max = Math.max(max, days[dayIndex][i]);
				}
			}
			return max;
		}
		max = Integer.MIN_VALUE;
		int maxMerit = Integer.MIN_VALUE;
		int[] day = days[dayIndex];
		for (int i = 0; i < day.length; i++) {
			int curMerit = 0;
			if (i != lastTask) {
				curMerit = recursion(days, i, dayIndex - 1) + day[i];
				maxMerit = Math.max(maxMerit, curMerit);
			}
		}

		return maxMerit;
	}

	private static int recursionDP(int days[][], int lastTask, int dayIndex, int dp[][]) {
		int max = Integer.MIN_VALUE;
		// assume base case as last day = 0th day
		if (dayIndex == 0) {
			for (int i = 0; i < days[dayIndex].length; i++) {
				if (i != lastTask) {
					max = Math.max(max, days[dayIndex][i]);
				}
			}
			return max;
		}
		max = 0;
		int maxMerit = Integer.MIN_VALUE;
		int[] day = days[dayIndex];
		for (int i = 0; i < day.length; i++) {
			int curMerit = 0;
			if (i != lastTask) {
				curMerit = recursion(days, i, dayIndex - 1) + day[i];
				maxMerit = Math.max(maxMerit, curMerit);
				if (lastTask != -1) {
					dp[dayIndex][lastTask] = maxMerit;
				}
			}
		}
		return maxMerit;
	}

	/**
	 * <pre>
	 * 
	 * 
	 *        A1 A2 A2
	 * day 1 {1, 2, 5}
	 * day 2 {3, 1, 1}
	 * day 3 {3, 3, 3}
	 * 
	 * 
	 * TC : O(N*4*3)
	 * SC : O(N*N)
	 * </pre>
	 */
	private static int recursionDPTabulation(int days[][], int dp[][]) {
		// since its 2d array, the entire 0th array should be pre filled with base
		// conditions.
		/**
		 * <pre>
		 * our base conditions are, 
		 * if we get last task as 1, then we need to send max of (0,2)
		 * last task as 2, then max(0,1)
		 * last task as 0 then max(1,2)
		 * 
		 * maintain an extra index at the end to store the max(0,1,2) if there is only one day.
		 * 
		 * 
		 * </pre>
		 * 
		 */

		dp[0][0] = Math.max(days[0][1], days[0][2]);
		dp[0][1] = Math.max(days[0][0], days[0][2]);
		dp[0][2] = Math.max(days[0][0], days[0][1]);
		dp[0][3] = Math.max(days[0][0], Math.max(days[0][1], days[0][2]));
//for day 0, we have prepared the max values for each lasttask.
		for (int day = 1; day < days.length; day++) {
			// we have 4 tasks need to be found. the 4th one will be the max of 1st, 2nd and
			// 3rd task points
			for (int last = 0; last < 4; last++) {
				int maxMerit = 0;
				dp[day][last] = 0;
				for (int task = 0; task < 3; task++) {
					if (last != task) {
						int curMerit = days[day][task] + dp[day - 1][task];
						maxMerit = Math.max(maxMerit, curMerit);
					}
				}

				dp[day][last] = maxMerit;
			}

		}
		return dp[days.length - 1][3];
	}

	/**
	 * TC : O(N*4*3) SC : O(4)
	 */
	private static int recursionNoDPTabulation(int days[][]) {
		// since its 2d array, the entire 0th array should be pre filled with base
		// conditions.
		/**
		 * <pre>
		 * our base conditions are, 
		 * if we get last task as 1, then we need to send max of (0,2)
		 * last task as 2, then max(0,1)
		 * last task as 0 then max(1,2)
		 * 
		 * maintain an extra index at the end to store the max(0,1,2) if there is only one day.
		 * 
		 * 
		 * </pre>
		 * 
		 */
		int[] prev = new int[4];
		prev[0] = Math.max(days[0][1], days[0][2]);
		prev[1] = Math.max(days[0][0], days[0][2]);
		prev[2] = Math.max(days[0][0], days[0][1]);
		prev[3] = Math.max(days[0][0], Math.max(days[0][1], days[0][2]));

//for day 0, we have prepared the max values for each lasttask.
		for (int day = 1; day < days.length; day++) {
			// we have 4 tasks need to be found. the 4th one will be the max of 1st, 2nd and
			// 3rd task points
			int temp[] = new int[4];
			for (int last = 0; last < 4; last++) {
				int maxMerit = 0;
				for (int task = 0; task < 3; task++) {
					if (last != task) {
						int curMerit = days[day][task] + prev[task];
						maxMerit = Math.max(maxMerit, curMerit);
					}
				}

				temp[last] = maxMerit;
			}
			prev = temp;

		}
		return prev[3];
	}
}
