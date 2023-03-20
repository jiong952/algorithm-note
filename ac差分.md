## 一维差分

差分可以看做是前缀和的逆运算

用来重复对[l,r]进行加减操作很高效，从O(n)->O(1)

> 首先给定一个原数组a：a[1], a[2], a[3],,,,,, a[n];
>
> 然后我们构造一个数组b ： b[1] ,b[2] , b[3],,,,,, b[i];
>
> 使得 a[i] = b[1] + b[2 ]+ b[3] +,,,,,, + b[i]
>
> 也就是说，a数组是b数组的前缀和数组，反过来我们把b数组叫做a数组的差分数组。换句话说，每一个a[i]都是b数组中从头开始的一段区间和。

1. **差分构造：**

![<img src="https://img-blog.csdnimg.cn/20201215214337143.png"   width="80%">](img/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTYyOTI4NQ==,size_16,color_FFFFFF,t_70.png)

`b[i] = a[i] - a[i-1];`

2. **差分修改时的操作**

给定区间[l ,r ]，让我们把a数组中的[ l, r]区间中的每一个数都加上c,即 a[l] + c , a[l+1] + c , a[l+2] + c ,,,,,, a[r] + c;

`b[l] += c; b[r + 1] -= c;`

![在这里插入图片描述](img/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTYyOTI4NQ==,size_16,color_FFFFFF,t_70-16728896203873.png)

3. **利用差分数组求原数组（前缀和）**

`a[i] = a[i-1] + b[i]`

```java
import java.util.*;
import java.io.*;
public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N = 100010;
    static int[] a = new int[N];
    static int[] b = new int[N];
    public static void main(String[] args) throws IOException{
        String[] init = br.readLine().split(" ");
        int n = Integer.parseInt(init[0]), m = Integer.parseInt(init[1]);
        String[] data = br.readLine().split(" ");
        for(int i = 1; i <= n; i++){
            a[i] = Integer.parseInt(data[i-1]);
            b[i] = a[i] - a[i-1];
        }
        while(m-- > 0){
            data = br.readLine().split(" ");
            int l = Integer.parseInt(data[0]), r = Integer.parseInt(data[1]), c = Integer.parseInt(data[2]);
            b[l] += c;
            b[r + 1] -= c;
        }
        for(int i = 1; i <= n; i++) a[i] = a[i-1] + b[i];
        for(int i = 1; i <= n; i++) System.out.print(a[i]+" ");
    }
}
```

## 二维差分

1. 构造差分数组
2. 修改
3. 还原

**差分是前缀和操作的逆运算**

构造前缀和

`s[i][j] = s[i-1][j] + s[i][j-1] - s[i-1][j-1] + b[i][j]`

这里的b矩阵就是差分矩阵

因此`b[i][j] = s[i][j] - s[i-1][j] - s[i][j-1] + s[i-1][j-1]`

修改操作(x1,y1)(x2,y2)

```java
b[x1][y1]+=c;
b[x1][y1+1]-=c;
b[x2+1][y2]-=c;
b[x2+1][y2+1]+=c;
```

还原，其实就是求前缀和

`s[i][j] = s[i-1][j] + s[i][j-1] - s[i-1][j-1] + b[i][j]`

板子

```java
import java.util.*;
import java.io.*;
public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N = 1010;
    static int[][] s = new int[N][N];
    static int[][] b = new int[N][N];
    public static void main(String[] args) throws IOException{
        String[] init = br.readLine().split(" ");
        int n = Integer.parseInt(init[0]), m = Integer.parseInt(init[1]), q = Integer.parseInt(init[2]);
        for(int i = 1; i <= n; i++){
            String[] data = br.readLine().split(" ");
            for(int j = 1; j <= m; j++){
                s[i][j] = Integer.parseInt(data[j - 1]);
                b[i][j] = s[i][j] - s[i-1][j] - s[i][j-1] + s[i-1][j-1];
            }
        }
        while(q-- > 0){
            String[] data = br.readLine().split(" ");
            int x1 = Integer.parseInt(data[0]);
            int y1 = Integer.parseInt(data[1]);
            int x2 = Integer.parseInt(data[2]);
            int y2 = Integer.parseInt(data[3]);
            int c = Integer.parseInt(data[4]);
            b[x1][y1]+=c;
            b[x1][y2+1]-=c;
            b[x2+1][y1]-=c;
            b[x2+1][y2+1]+=c;
        }
        //还原
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                s[i][j] = s[i-1][j] + s[i][j-1] - s[i-1][j-1] + b[i][j];
                bw.write(s[i][j] +" ");
            }
            bw.write("\n");
        }
        bw.flush();
    }
}
```

## 三维差分

```java
s[i][j][k] = b[i][j][k] + s[i-1][j][k] + s[i][j-1][k] - s[i-1][j-1][k]
            + s[i][j][k-1] - s[i-1][j][k-1] - s[i][j-1][k-1] + s[i-1][j-1][k-1];
因此可以根据原数组s推出b：
b[i][j][k] = s[i][j][k] - s[i-1][j][k] - s[i][j-1][k] + s[i-1][j-1][k]
            - s[i][j][k-1] + s[i-1][j][k-1] + s[i][j-1][k-1] - s[i-1][j-1][k-1];
```

- 可以发现，在求b[i][j][k]的等式右侧，有偶数个坐标减一前面的系数为+1，奇数个坐标减一系数为-1。

- 现在考虑将位于(x1, y1, z1)和(x2, y2, z2)之间的原序列都加上c，对数组b的影响：

```
b[x1    ][y1    ][z1    ]   += c;  // 000
b[x1    ][y1    ][z2 + 1]   -= c;  // 001
b[x1    ][y2 + 1][z1    ]   -= c;  // 010
b[x1    ][y2 + 1][z2 + 1]   += c;  // 011
b[x2 + 1][y1    ][z1    ]   -= c;  // 100
b[x2 + 1][y1    ][z2 + 1]   += c;  // 101
b[x2 + 1][y2 + 1][z1    ]   += c;  // 110
b[x2 + 1][y2 + 1][z2 + 1]   -= c;  // 111
```

- 可以发现有偶数个加一的就是+=c，有奇数个加一的就是-=c。

板子

ac1232

#### 坐标映射

(i,j) -> i*B+j

(i,j,k) -> (i*B+j) *C + k 
