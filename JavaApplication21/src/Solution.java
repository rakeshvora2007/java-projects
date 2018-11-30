// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public String solution(String S, int K) {
       
        char a[] = new char[S.length()];
        int i ;
        int flag = 0;
        int flag2 = 0;
        int j=S.length()-1;        
        for(i=S.length()-1; i>=0;i--){
            
            if(i !=S.length()-1 && flag%K==0 && j>0){
               System.out.println(S.charAt(i)+" a "+i + " " + j + " "+flag2);
            i++;
            a[j]='-';
            flag=1;
            j--;
            if(S.charAt(i)!='-'&& j>=0)
            {
                a[j] = S.charAt(i);
                j--;
            }
              System.out.println(S.charAt(i)+" a "+i + " " + j + " "+flag2);
            }else {
                if(S.charAt(i)!='-'){
                System.out.println(S.charAt(i)+" b "+i + " " + j + " "+flag2);
            a[j+flag2]=S.charAt(i);
            j--;
                    
            flag++;
            }
                else {flag2++;
                j--;}
            
            }
        }
        return String.valueOf(a);
            
        }
    
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println("Answer : "+s.solution("2-4A0r7-4k", 4));
        
        
    }
    
    }
