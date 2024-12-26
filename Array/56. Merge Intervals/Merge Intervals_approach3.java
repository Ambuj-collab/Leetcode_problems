import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class Solution {
  
    // Method to merge overlapping intervals.
    public int[][] merge(int[][] intervals) {
        // Sort the intervals by their starting times.
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // List that holds the merged intervals.
        List<int[]> mergedIntervals = new ArrayList<>();
        // Add the first interval to the list as starting interval for merging.
        mergedIntervals.add(intervals[0]);

        // Loop through all the intervals starting from the second one.
        for (int i = 1; i < intervals.length; ++i) {
            // Get the start and end times of the current interval.
            int start = intervals[i][0];
            int end = intervals[i][1];

            // Get the last interval in the merged list.
            int[] lastMergedInterval = mergedIntervals.get(mergedIntervals.size() - 1);

            // Check if there is an overlap with the last interval in the merged list.
            if (lastMergedInterval[1] < start) {
                // No overlap, so we can add the current interval as it is.
                mergedIntervals.add(intervals[i]);
            } else {
                // Overlap exists, so we extend the last interval's end time.
                lastMergedInterval[1] = Math.max(lastMergedInterval[1], end);
            }
        }

        // Convert the merged intervals list to a 2D array and return it.
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }
}
