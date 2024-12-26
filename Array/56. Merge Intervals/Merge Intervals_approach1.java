class Solution {
    public int[][] merge(int[][] intervals) {
		// sorting all intervals with respect to starting time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        int[] prev = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] <= prev[1]) {
                prev[1] = Math.max(prev[1], interval[1]);
            } else {
                merged.add(prev);
                prev = interval;
            }
        }

        merged.add(prev);

        return merged.toArray(new int[merged.size()][]);
    }
}

-------------------------------------------------------------------------------
									OR
-------------------------------------------------------------------------------
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0)
            return new int[0][0];

        // Sort the intervals by their start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        LinkedList<int[]> result = new LinkedList<>();

        // Add the first interval to the result list
        result.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            // Get the last interval added to the result list
            int[] lastAddedInterval = result.getLast();

            // If the current interval overlaps with the last added interval, merge them
            if (lastAddedInterval[1] >= intervals[i][0]) {
                lastAddedInterval[1] = Math.max(lastAddedInterval[1], intervals[i][1]);
            } else {
                // Otherwise, add the current interval to the result list
                result.add(intervals[i]);
            }
        }

        // Convert the LinkedList to a 2D array and return it
        return result.toArray(new int[result.size()][]);
    }
}

-------------------------------------------------------------------------------
										OR
-------------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if (n == 0)
            return new int[0][0];

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        ArrayList<int[]> res = new ArrayList<>();

        int[] currentInterval = intervals[0];
        res.add(currentInterval);

        for (int i = 1; i < n; i++) {
            int[] interval = intervals[i];

            if (currentInterval[1] >= interval[0]) { // Overlapping intervals, merge them
                currentInterval[1] = Math.max(currentInterval[1], interval[1]);
            } else { // Disjoint intervals, add the new interval
                currentInterval = interval;
                res.add(currentInterval);
            }
        }

        return res.toArray(new int[res.size()][]);
    }
}


/*

Time Complexity:
-------------------
Sorting the Intervals: The algorithm starts by sorting the intervals based on their start times. The time complexity for sorting is O(nlogn), where n is the number of intervals.
Merging the Intervals: After sorting, the algorithm iterates through the list of intervals once to merge them. This step takes O(n).

Thus, the overall time complexity is:
O(nlogn) + O(n) = O(nlogn)

Space Complexity:
--------------------
Space for Merged Intervals: The merged intervals are stored in a list. In the worst case, all intervals are non-overlapping, and the space required will be O(n).
Auxiliary Space for Sorting: The sorting operation may require additional space, depending on the sorting algorithm used. In the case of Timsort (used by Java), the space complexity is O(n) in the worst case.

Therefore, the overall space complexity is:
O(n)

Summary:
----------
Time Complexity: O(nlogn)
Space Complexity: O(n)

*/