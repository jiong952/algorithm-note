package ac1296;

import java.util.*;
import java.io.*;
public class Main{
    static int N = 50000; //sqrt(S)
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 线性筛
    static int[] primes = new int[N];
    static int cnt = 0;
    static boolean[] st = new boolean[N];
    // 结果 S:输入 len：答案个数
    static int S,len;
    static int[] res = new int[N];
    public static boolean is_primes(int n){
        if(n < N) return !st[n]; //如果被筛了 就不是质数
        for(int i = 0; primes[i] <= n / primes[i]; i++){
            if(n % primes[i] == 0) return false;
        }
        return true;
    }
    public static void get_primes(int n){
        for(int i = 2; i <= n; i++){
            if(!st[i]) primes[cnt++] = i;
            for(int j = 0; primes[j] * i <= n; j++){
                st[primes[j] * i] = true;
                if(i % primes[j] == 0) break;
            }
        }
    }
    //last表示上一个用到的质数 prod：当前的数（由每一个括号项的最高次幂乘积组成）
    //S:表示剩余的值
    public static void dfs(int last, int prod, int S){
        if(S == 1){
            res[len++] = prod;
            return;
        }
        // 剪枝 S-1要大于上一个遍历的质数
        if(S - 1 > ((last < 0) ? 1 : primes[last]) && is_primes(S-1)){
            res[len++] = prod * (S-1);
        }
        for(int i = last + 1; primes[i] <= S / primes[i]; i++){
            int p = primes[i];
            for(int j = 1 + p, t = p; j <= S; t*=p, j += t){
                if(S % j == 0){
                    dfs(i,prod * t,S/j);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException{
        String cur = null;
        get_primes(N-1);
        while((cur = br.readLine()) != null){
            S = Integer.parseInt(cur);
            len = 0;
            dfs(-1,1,S);
            bw.write(len+"\n");
            if(len > 0){
                Arrays.sort(res,0,len);
                for(int i = 0; i < len; i++){
                    bw.write(res[i] + " ");
                }
                bw.write("\n");
            }
        }
        bw.flush();
    }
}