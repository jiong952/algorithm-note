### 前言

记录个人刷题模板总结 持续更新...

刷题永无止境~

目前已更新

1. 二分模板
2. 单调栈+贡献值
3. 背包问题【0-1背包、完全背包】
4. bfs模板

---

有机会整理成gitbook



## 常用API整理

输入 加快速度

```java
//先引入io和util的包
import java.util.Scanner;
import java.io.*;

StreamTokenizer sc = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
//在此输入您的代码...
sc.nextToken();
int N = (int) sc.nval;
sc.nextToken();
int M = (int) sc.nval;
```

拷贝数组

```java
Arrays.copyOf(dataType[] srcArray,int length);
其中，srcArray 表示要进行复制的数组，length 表示复制后的新数组的长度。
int scores[] = new int[]{57,81,68,75,91};
int[] newScores = (int[])Arrays.copyOf(scores,8);
```

