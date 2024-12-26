class Solution {

    // Function to insert a new interval into an existing list of intervals
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // Initialize an expanded array to hold the existing intervals and the new interval
        int[][] expandedIntervals = new int[intervals.length + 1][2];
      
        // Copy existing intervals into the expanded array
        for (int i = 0; i < intervals.length; ++i) {
            expandedIntervals[i] = intervals[i];
        }
      
        // Add the new interval to the end of the expanded intervals array
        expandedIntervals[intervals.length] = newInterval;
      
        // Merge overlapping intervals and return the result
        return merge(expandedIntervals);
    }

    // Helper function to merge overlapping intervals
    private int[][] merge(int[][] intervals) {
        // Sort the intervals based on the starting times
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
      
        // List to hold the merged intervals
        List<int[]> mergedIntervals = new ArrayList<>();
      
        // Add the first interval to the list as initialization
        mergedIntervals.add(intervals[0]);
      
        // Iterate through each interval and merge if necessary
        for (int i = 1; i < intervals.length; ++i) {
            // Get the start and end times of the current interval
            int start = intervals[i][0];
            int end = intervals[i][1];
          
            // Get end time of the last interval in the list.
            int lastEnd = mergedIntervals.get(mergedIntervals.size() - 1)[1];
          
            // If the current interval does not overlap with the previous, simply add it
            if (lastEnd < start) {
                mergedIntervals.add(intervals[i]);
            } else {
                // Otherwise, merge the current interval with the previous one by updating the end time
                mergedIntervals.get(mergedIntervals.size() - 1)[1] = Math.max(lastEnd, end);
            }
        }
      
        // Convert the list back into an array and return
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }
}

/*

Time and Space Complexity:
----------------------------
Time Complexity:
The given code consists of two main operations: sorting the list of intervals, and then merging these intervals. Here's how each operation contributes to the total time complexity:

Sorting: The sort() method in Java uses the Timsort algorithm, which has a time complexity of O(nlogn) where n is the number of intervals. Since we are appending a new interval before sorting, the sorting step will operate on n + 1 intervals, but this does not change the overall complexity, so it remains O(nlogn).

Merging: The merge() method iterates through the sorted list of intervals once to combine overlapping intervals. This is a linear pass, which means it runs in O(n) time, considering n as the number of intervals including the new one we added.

Combining both steps, the time complexity of the algorithm is dominated by the sorting step, so the overall time complexity is O(nlogn).

Space Complexity:
The space complexity is determined by the extra space used by the algorithm. In this case, we have:

The additional list 'mergedIntervals' that is initially a copy of the first interval, and worst-case, could be extended to include all intervals if none overlap. This results in a worst-case space complexity of O(n).

The in-place sort() generally has a space complexity of O(1) for the actual sorting since Timsort is a hybrid stable sorting algorithm that takes advantage of the existing order in the list. Yet, it might require a temporary space of up to O(n) in the worst case when merging runs. But since we are considering the space for the output as separate, we do not count this towards additional space.

Thus, the overall space complexity of the algorithm is O(n).

*/