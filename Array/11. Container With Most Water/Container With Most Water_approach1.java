/*

Intuition:
------------
Using two pointer to get width easily and improve time complexity from O(n^2) to O(n).

*/

class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}

--------------------------------------------------------------------------------------------------
											OR
--------------------------------------------------------------------------------------------------

class Solution {
    public int maxArea(int[] height) {
        int left = 0;                   // Left pointer starting from the leftmost edge
        int right = height.length - 1;  // Right pointer starting from the rightmost edge
        int maxWater = 0;               // Initialize the maximum water capacity
        
        while (left < right) {
            // Calculate the width of the container
            int width = right - left;
            
            // Calculate the height of the container (the minimum height between the two lines)
            int h = Math.min(height[left], height[right]);
            
            // Calculate the water capacity of the current container
            int water = width * h;
            
            // Update the maximum water capacity if the current container holds more water
            maxWater = Math.max(maxWater, water);
            
            // Move the pointers towards each other
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxWater;
    }
}

--------------------------------------------------------------------------------------------------
											OR
--------------------------------------------------------------------------------------------------

class Solution {
  
    // Method to find the maximum area formed between the vertical lines
    public int maxArea(int[] height) {
        // Initialize two pointers at the beginning and end of the array
        int left = 0; 
        int right = height.length - 1;
        // Variable to keep track of the maximum area
        int maxArea = 0;
      
        // Iterate until the two pointers meet
        while (left < right) {
            // Calculate the area with the shorter line as the height and the distance between the lines as the width
            int currentArea = Math.min(height[left], height[right]) * (right - left);
            // Update the maximum area if the current area is larger
            maxArea = Math.max(maxArea, currentArea);
          
            // Move the pointer that points to the shorter line towards the center
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
      
        // Return the maximum area found
        return maxArea;
    }
}

/*

Time and Space Complexity:
------------------------------
Time complexity: O(n)
Space complexity: O(1)

*/