/*

Notes:
Instead of trying all characters of hand in all the possible positions in board. Instead we can put it in only for 2 conditions:

1) if the hand character is same as board character - Got this idea from example test case3
2) If the board has 2 consecutive similar characters. - to make sure we generate 3 length of consecutive characters.

*/

class Solution {
    public int findMinStep(String board, String hand) {
        int m = hand.length();
        Map<Character, Integer> map = new HashMap();
        Map<String, Integer> dp = new HashMap();
        for (int index = 0; index < m; index += 1) {
            map.put(hand.charAt(index), map.getOrDefault(hand.charAt(index), 0) + 1);
        }
        int ans = findAns(board, map, dp);
        if (ans >= 100000)
            return -1;
        return ans;
    }

    private int findAns(String board, Map<Character, Integer> hand, Map<String, Integer> dp) {
        String dpAns = board + "-" + getMapString(hand);
        if (dp.containsKey(dpAns))
            return dp.get(dpAns);
        int ans = 100000; // We can also take Integer.MAX_VALUE
        board = correctString(board);
        int n = board.length();
        if (n == 0)
            return 0;
        for (int index = 0; index < n; index += 1) {
            for (Map.Entry<Character, Integer> entry : hand.entrySet()) {
                char ch = entry.getKey();
                char ch2 = board.charAt(index);
                if (hand.get(ch) > 0
                        && (ch == ch2 || (index < (n - 1) && board.charAt(index) == board.charAt(index + 1)))) {
                    String newString = board.substring(0, index + 1) + ch + board.substring(index + 1);
                    hand.put(ch, hand.get(ch) - 1);
                    ans = Math.min(ans, 1 + findAns(newString, hand, dp));
                    hand.put(ch, hand.get(ch) + 1);
                }
            }
        }

        dp.put(dpAns, ans);
        return ans;
    }

    private String correctString(String str) {
        int n = str.length();
        int i = 0;
        while (i < n) {
            char curChar = str.charAt(i);
            int j = i + 1;
            while (j < n) {
                if (str.charAt(j) != curChar) {
                    break;
                }
                j += 1;
            }
            if ((j - i) >= 3) {
                return correctString(str.substring(0, i) + str.substring(j));
            }
            i = j;
        }

        return str;

    }

    char[] colors = new char[] { 'R', 'Y', 'B', 'G', 'W' };

    private String getMapString(Map<Character, Integer> hand) {
        StringBuilder ans = new StringBuilder();
        for (int index = 0; index < colors.length; index += 1) {
            ans.append(hand.getOrDefault(colors[index], 0) + "-");
        }
        return ans.toString();
    }

}