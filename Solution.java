package org.example;

public class Solution {
    public boolean isNumber(String s) {
        s = s.trim();

        boolean pointSeen = false; // Kiểm tra trong String có kí tự "." chưa, có rồi thì sẽ declare True
        boolean eSeen = false; // Kiểm tra trong String pass in có kí tự e hay chưa, có thì sẽ declare True
        boolean numberSeen = false; // tương tự như 2 cái trên nhưng là kí tự số
        boolean numberAfterE = true; // tương tự như cái trên kiểm tra số nguyên sau kí tự "e" hoặc "E"
        for(int i=0; i<s.length(); i++) {
            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
                numberAfterE = true; // Cái này mặc định mở đầu loop là True do kí tự "e" là optional
                // và ở đây chưa check về vấn đề số thập phân sau "e"
            }
            else if(s.charAt(i) == '.') {
                if(eSeen || pointSeen) {  // Nếu một trong 2 cái này true thì String này không valid nữa
                    // Do sau e không thể có số thập phân
                    // và không thể có 2 kí tự "." trong 1 decimal number
                    return false;
                }
                pointSeen = true; // Nếu if statement không xảy ra thì ta phát hiện có kị tự "." trong String
                // Set boolean kiểm tra tương ứng sang True
            }
            else if(s.charAt(i) == 'e') {
                if(eSeen || !numberSeen) { // Nếu một trong 2 boolean này True , boolean thứ 2 là boolean phủ định
                    // thì String number này invalid
                    // Do chỉ được có một kí tự "e" hoặc "E" trong String
                    // và "e" hoặc "E" phải đi sau decimal number hoặc integer và được theo sau bởi integer
                    return false;
                }
                numberAfterE = false; // Nếu if statement bên trên không xảy ra thì đây là kí tự "e" đầu tiên
                // thì set 2 boolean liên quan giá trị tương ứng
                eSeen = true;
            }
            else if(s.charAt(i) == '-' || s.charAt(i) == '+') {// 2 kí tự này optional và nó là khởi đầu
                // của 1 decimal number hoặc integer nên nó chỉ có thể là kí tự đầu tiên hoặc là ngay sau
                // kí tự 'e' do là bắt đầu của một integer
                if(i != 0 && s.charAt(i-1) != 'e') {
                    // không cần phải set boolean liên quan đến if statement của case này
                    // do nó không trở thành điều kiện để set các loop phía sau nó mặc dù cùng là optional
                    // giống kí tự 'e'
                    return false;
                }
            }
            else { // Nếu kí tự tại index i không rơi vào bất kì if statement nào bên trên thì
                // String pass in invalid
                return false;
            }
        }
        // Một số thỏa mãn khi và chỉ khi nó là một số độc lập thỏa mãn hoặc 1 số chứa kí tự e và một integer thỏa mãn sau 'e'
        return numberSeen && numberAfterE;
    }
}
