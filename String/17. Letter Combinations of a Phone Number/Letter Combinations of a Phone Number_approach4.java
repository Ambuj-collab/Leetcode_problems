class Solution {
	
	/*
		Now , we start with the first step of creating Hashmap. The numbers act as keys and the alphabets act as values
	*/
    private static final Map<Character, String> phoneMap = new HashMap<>();
	
	/* 
		I have made this block as static , because this is the part of code that does not need any modifications. Also , I want this map to be a part of the class itself rather than the instances so that this phoneMap is shared among all the instances .
	*/
    static {
        phoneMap.put('2', "abc");
        phoneMap.put('3', "def");
        phoneMap.put('4', "ghi");
        phoneMap.put('5', "jkl");
        phoneMap.put('6', "mno");
        phoneMap.put('7', "pqrs");
        phoneMap.put('8', "tuv");
        phoneMap.put('9', "wxyz");
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        backtrack(result, new StringBuilder(), digits, 0);
        return result;

    }

    private void backtrack(List<String> result, StringBuilder combination, String digits, int index) {
        if (index == digits.length()) {
            result.add(combination.toString());
            return;
        }

        char d = digits.charAt(index);
        String letters = phoneMap.get(d);

        for (char l : letters.toCharArray()) {
            combination.append(l);
            backtrack(result, combination, digits, index + 1);
            combination.deleteCharAt(combination.length() - 1);
        }
    }
}
