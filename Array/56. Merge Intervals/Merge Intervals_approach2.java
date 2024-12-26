class Solution {

// Most optimize, TC: O(NlogN + N + N) ~= O(NlogN)

public int[][] merge(int[][] arr) {

    int n = arr.length; // size of the array
        //sort the given intervals:
        Arrays.sort(arr, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            // if the current interval does not lie in the last interval:
            if (ans.isEmpty() || arr[i][0] > ans.get(ans.size() - 1).get(1)) {
                ans.add(Arrays.asList(arr[i][0], arr[i][1]));
            }
            // if the current interval lies in the last interval:
            else {
                ans.get(ans.size() - 1).set(1, Math.max(ans.get(ans.size() - 1).get(1), arr[i][1]));
            //  we have to take the end value which is max from both the arrays-comparision
            }
        }
        // Convert List<List<Integer>> to int[][]
        int[][] result = new int[ans.size()][2];
        for (int i = 0; i < ans.size(); i++) {
            result[i][0] = ans.get(i).get(0);
            result[i][1] = ans.get(i).get(1);
        }

        return result; 

	}
}

----------------------------------------------------------------------------------------
											OR
----------------------------------------------------------------------------------------

// This is also most optimized code.
class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int start = intervals[0][0];
        int end = intervals[0][1];
        int n = intervals.length;
        for (int i = 0; i < n; i++) {
            if (intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
            } else {
                list.add(new int[] { start, end });
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        list.add(new int[] { start, end });
        return list.toArray(new int[0][]);
    }
}

----------------------------------------------------------------------------------------
											OR
----------------------------------------------------------------------------------------

class Solution {

    // Brute Force
    public int[][] merge(int[][] arr) {

        Arrays.sort(arr, new Comparator<int[]>() { // comparing arrys based on 0th index
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) { // select an interval:
            int start = arr[i][0];
            int end = arr[i][1];

            // Skip all the merged intervals:
            if (!ans.isEmpty() && end <= ans.get(ans.size() - 1).get(1)) {
                continue;
            } // like [8,10] 10(end) is smaller than (9,11) in our book eg. so we skipped it,
              // as it will come in the merging intervals

            for (int j = i + 1; j < arr.length; j++) {

                if (arr[j][0] <= end) {
                    end = Math.max(end, arr[j][1]); // we have to take the end value which is max from both the arrays-comparision
                } else {
                    break;
                }
            }
            ans.add(Arrays.asList(start, end));
        }

        // Convert List<List<Integer>> to int[][]
        int[][] result = new int[ans.size()][2];
        for (int i = 0; i < ans.size(); i++) {
            result[i][0] = ans.get(i).get(0);
            result[i][1] = ans.get(i).get(1);
        }

        return result;
    }
}
