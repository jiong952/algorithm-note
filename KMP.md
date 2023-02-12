# KMP板子

注意点：字符串下标从1开始

- 求next数组时 i从2开始
- 求匹配下标：`i-n` 

```java
import java.util.*;
import java.io.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int n = Integer.parseInt(br.readLine());
        String pp = " " + br.readLine();
        int m = Integer.parseInt(br.readLine());
        String ss = " " + br.readLine();
        char[] p = pp.toCharArray();
        char[] s = ss.toCharArray();
        int[] ne = new int[n + 1];
        //求next数组
        for(int i = 2, j = 0; i <= n; i++){
            while(j > 0 && p[i] != p[j+1]) j = ne[j];
            if(p[i] == p[j+1]) j++;
            ne[i] = j;
        }
        //匹配
        for(int i = 1, j = 0; i <= m; i++){
            while(j > 0 && s[i] != p[j+1]) j = ne[j];
            if(s[i] == p[j+1]) j++;
            if(j == n){
                bw.write(i-n+" ");
                j = ne[j];   
            }
        }
        bw.flush();
    }
}
```

相关题目：

- [28.找出字符串中第一个匹配项的下标](https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/)
- [459. 重复的子字符串](https://leetcode.cn/problems/repeated-substring-pattern/)

参考：acwing题解
