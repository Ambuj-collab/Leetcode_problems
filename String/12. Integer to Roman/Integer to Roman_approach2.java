class Solution {
    public String intToRoman(int num) {
        String res = "";

        while(num > 0){
            if(num >= 1000){
                res = res + 'M';
                num -= 1000;
            } else if(num >= 500){
                if(num >= 900){
                    res = res + "CM";
                    num -= 900;
                    continue; 
                }
                res = res + 'D';
                num -= 500;
            } else if(num >= 100){
                if(num >= 400){
                    res = res + "CD";
                    num -= 400;
                    continue; 
                }
                res = res + 'C';
                num -= 100;
            } else if(num >= 50){
                if(num >= 90){
                    res = res + "XC";
                    num -= 90;
                    continue; 
                }
                res = res + 'L';
                num -= 50;
            } else if(num >= 10){
                if(num >= 40){
                    res = res + "XL";
                    num -= 40;
                    continue; 
                }
                res = res + 'X';
                num -= 10;
            } else if(num >= 5){
                if(num >= 9){
                    res = res + "IX";
                    num -= 9;
                    continue; 
                }
                res = res + 'V';
                num -= 5;
            } else {
                if(num >= 4){
                    res = res + "IV";
                    num -= 4;
                    continue; 
                }
                res = res + 'I';
                num -= 1;
            }
        }

        return res;
    }
}
