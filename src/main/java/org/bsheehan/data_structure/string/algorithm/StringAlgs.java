package org.bsheehan.data_structure.string.algorithm;

public class StringAlgs {
    public static java.lang.String int2str(int val){

        StringBuilder sb = new StringBuilder();

        // negative case
        if (val < 0) {
            sb.insert(0, '-');
            val *= -1;
        }

        int div = 1;
        while(val/div != 0) {
            int digit = (val/div)%10;
            sb.insert(0,digit);
            div *= 10;
        }
        return sb.toString();
    }

    public static int str2int(java.lang.String str){

        // negative case
        int len = str.length();
        boolean negative = false;
        if (str.charAt(0) == '-') {
            negative = true;
            len -= 1;
        }

        int val = 0;
        int mult = 1;
        for (int i = 0; i < len; i++) {
            char ch =  str.charAt(str.length()-i-1);
            val += Character.getNumericValue(ch)* mult;
            mult *= 10;
        }

        if (negative)
            val *= -1;

        return val;
    }
}
