/**
 * Name         : Yap Kai Herng
 * Matric. No   : A0199729A
*/

import java.util.*;

public class Palindrome {
    private void run() {
        Scanner sc = new Scanner(System.in);
        int numWords = sc.nextInt();
        for(int n = 0; n < numWords; n++) {
            String toCheck = sc.next();
            int k = sc.nextInt();
            if (this.isPalindrome(toCheck, k)) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    public boolean isPalindrome(String str, int k) {
        if (str.length() <= 1) return true;
        if (str.charAt(0) == str.charAt(str.length()-1)) {
            return isPalindrome(str.substring(1,str.length()-1), k);
        } else {
            if (k == 0) return false;
            else {
                return isPalindrome(str.substring(0,str.length()-1),
                k-1) || isPalindrome(str.substring(1, str.length()),
                k-1);
            }
        }
    }

    public static void main(String args[]) {
        Palindrome runner = new Palindrome();
        runner.run();
    }
}
