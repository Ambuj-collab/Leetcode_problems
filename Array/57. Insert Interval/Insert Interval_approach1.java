class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new LinkedList<>();
        int i = 0;
        // add all the intervals ending before newInterval starts
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // merge all overlapping intervals to one considering newInterval
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            // we could mutate newInterval here also
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        // add the union of intervals we got
        result.add(newInterval);

        // add all the rest
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }
}

------------------------------------------------------------------------------
										OR
------------------------------------------------------------------------------

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        int n = intervals.length;
        int i = 0;

        while (i < n && intervals[i][1] < newInterval[0]) {
            ans.add(new int[] { intervals[i][0], intervals[i][1] });
            i++;
        }

        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }

        ans.add(new int[] { newInterval[0], newInterval[1] });
        
        while (i < n) {
            ans.add(new int[] { intervals[i][0], intervals[i][1] });
            i++;
        }
        
        int res[][] = new int[ans.size()][2];
        for (int j = 0; j < ans.size(); j++) {
            res[j][0] = ans.get(j)[0];
            res[j][1] = ans.get(j)[1];
        }
        return res;
    }
}
