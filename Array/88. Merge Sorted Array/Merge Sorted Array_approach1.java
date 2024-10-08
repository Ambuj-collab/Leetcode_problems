class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int nums1Trav = 0, nums2Trav = 0, numsTrav = m;

        while (nums1Trav < m && nums2Trav < n) {

            if(nums1[nums1Trav] > nums2[nums2Trav]){
                // Shift the elements from index nums1Trav to index m-1
                for(int i=m-1; i >= nums1Trav; i--){
                    nums1[i+1] = nums1[i]; 
                }

                // Copy the smaller element from nums2 array to nums1 array
                nums1[nums1Trav++] = nums2[nums2Trav++];

                // Increment the number of elements in nums1 array 
                m++;
            } else {
                nums1Trav++;
            }
        }

        // Add the remaining greater elements of nums2 array to nums1 array
        while(nums2Trav < n){
            nums1[nums1Trav++] = nums2[nums2Trav++];
        }
    
    }
}
