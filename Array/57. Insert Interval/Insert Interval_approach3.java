/*

Intuition:
------------
My initial thought to solve this problem is to iterate through the list of intervals and compare each interval with the new interval to determine its position for insertion.

Approach:
-----------
I will traverse through the list of intervals and compare each interval's start and end points with the start and end points of the new interval. Based on the comparison, I will insert the new interval appropriately, ensuring that the resulting list remains sorted and non-overlapping.

*/

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        int start = newInterval[0], end = newInterval[1];
        boolean inserted = false;
        
        for (int[] inv : intervals) {
            int cstart = inv[0], cend = inv[1];
            
            if (cend < start || inserted) {
                ans.add(new int[]{cstart, cend});
                continue;
            }
            
            start = Math.min(start, cstart);
            if (end < cstart) {
                ans.add(new int[]{start, end});
                ans.add(new int[]{cstart, cend});
                inserted = true;
                continue;
            }
            
            if (end <= cend) {
                ans.add(new int[]{start, cend});
                inserted = true;
            }
        }
        
        if (!inserted) {
            ans.add(new int[]{start, end});
        }
        
        return ans.toArray(new int[ans.size()][]);
    }
}

/*

Time and Space Complexity:
-----------------------------
Time complexity: O(n), where n is the number of intervals in the input list. This is because we iterate through the list of intervals only once.

Space complexity: O(n) as well. We are using extra space to store the resultant list of intervals, which could potentially contain all intervals from the input list plus the new interval.

*/