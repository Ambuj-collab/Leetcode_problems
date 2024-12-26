/*

Intuition:
------------
1) There needs to be 4 parts per IP. There are always three dots/four candidates (2.5.5.2.5.5.1.1.1.3.5 is not valid, neither is 1.1.1)
2) When selecting a candidate, it can't have more than 3 characters (2552.55.111.35 is not valid)
3) When selecting a candidate, its characters cant be above 255 (25.525.511.135 is not valid because of 525 and 511)
4) When selecting a candidate, IF the candidate has 2 or more characters, then the first can't be a zero. (1.0.1.023 is not valid as member 023 is size>1 and leads with 0. But 0.0.0.0 is valid as each candidate is size 1)

*/

class Solution {
    private List<String> ipes;
    private int l;

    public List<String> restoreIpAddresses(String s) {
        ipes = new ArrayList<>();
        l = s.length();
        f(s, 0, "", 0);
        return ipes;
    }

    private boolean isIp(String ip) {
        if (ip.length() > 3 || ip.length() == 0)
            return false;
        if (ip.length() > 1 && ip.charAt(0) == '0')
            return false;
        if (ip.length() > 0 && Integer.parseInt(ip) > 255)
            return false;
        return true;
    }

    private void f(String s, int index, String ip, int dot) {
        // base case
        if (dot == 3) {
            if (isIp(s.substring(index))) {
                ip += s.substring(index);
                ipes.add(ip);
            }
            return;
        }

        // do all the stuff
        for (int i = index; i < l; i++) {
            if (isIp(s.substring(index, i + 1))) {
                int k = s.substring(index, i + 1).length();
                ip += s.substring(index, i + 1) + ".";
                f(s, i + 1, ip, dot + 1);
                ip = ip.substring(0, ip.length() - k - 1);  // backtracking
            }
        }
    }
}

--------------------------------------------------------------------------------------------------
												OR
--------------------------------------------------------------------------------------------------

/*

Approach:
------------
Use an additional input to the function that is index, which says the function where the string starts from, it is as good as forgetting the already dotted part of the string.

Additional Improvement: Check if the given string length is greater than 12, then its completely invalid, Similarly if already 2 dots inserted and the remaining string length is 7 its invalid as well.

*/
class Solution {
    List<String> ans = new ArrayList<>();
    String str;

    public List<String> restoreIpAddresses(String s) {
        str = s;
        magical("", 0, 0);
        return ans;
    }

    void magical(String path, int index, int dots) {
        if (dots > 4)
            return;
        if (dots == 4 && index >= str.length()) {
            ans.add(path.substring(0, path.length() - 1));  // remove the last dot from the 'path' which is a valid IP
            return;
        }
        for (int length = 1; length <= 3 && index + length <= str.length(); length++) {
            String num = str.substring(index, index + length);
            if (num.charAt(0) == '0' && length != 1)
                break;
            else if (Integer.parseInt(num) <= 255) {
                magical(path + str.substring(index, index + length) + ".", index + length, dots + 1);
            }
        }
    }
}

