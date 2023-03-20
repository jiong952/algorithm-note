# 树状数组与线段树

## 树状数组

- 单点修改
- 区间查询

![image-20230213173606498](img/image-20230213173606498.png)

三个重要的函数

- `int lowBit(int x)` 返回当前下标在第几层
- `void add(int x, int v) `将树状数组与下标x有关联的部分都加上修改的差值v
- `int query(int x)` 查询[1,x]区间前缀和

```java
public static int lowBit(int x){
        return x & -x;
    }
    public static void add(int x, int v){
        for(int i = x; i <= n; i += lowBit(i)) tr[i] += v;
    }
    public static int query(int x){
        int res = 0;
        for(int i = x; i > 0; i -= lowBit(i)) res += tr[i];
        return res;
    }
```

### 树状数组板子

```java
import java.util.*;
import java.io.*;
public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N = 100010;
    static int n;
    static int m;
    static int[] arr = new int[N];
    static int[] tr = new int[N];
    //返回层数
    public static int lowBit(int x){
        return x & -x;
    }
    //修改树状数组
    public static void add(int x, int v){
        for(int i = x; i <= n; i += lowBit(i)) tr[i] += v;
    }
    //返回前缀和
    public static int query(int x){
        int res = 0;
        for(int i = x; i > 0; i -= lowBit(i)) res += tr[i];
        return res;
    }
    public static void main(String[] args) throws IOException{
        String[] init = br.readLine().split(" ");
        n = Integer.parseInt(init[0]);
        m = Integer.parseInt(init[1]);
        String[] data = br.readLine().split(" ");
        //初始化
        for(int i = 1; i <= n; i++){
            arr[i] = Integer.parseInt(data[i-1]);
            add(i,arr[i]);
        } 
        while(m-- > 0){
            data = br.readLine().split(" ");
            int k = Integer.parseInt(data[0]);
            int a = Integer.parseInt(data[1]);
            int b = Integer.parseInt(data[2]);
            if(k == 0){
                //返回区间和
                System.out.println(query(b) - query(a-1));
            }else{
                add(a,b);
            }
        }
    }
}
```

## 线段树

数据结构来看就是二叉树

比树状数组万能、但更慢且代码量更大	

线段树分为四个操作

- 初始化数：`void buildTree(int u, int l, int r)`
  - 当前结点u则左儿子`2u`右儿子`2u+1`
  - 第一个参数，当前节点编号，第二个参数，左边界，第三个参数，右边界
- 计算当前结点的值：`void pushUp(int u)`
- 修改操作：`void modify(int u, int a, int b)`
- 查询操作：`int query(int u, int l, int r)`

### 板子[求区间和]

```java
import java.util.*;
import java.io.*;
public class Main{
    static class Node{
        int l,r,sum;
        Node(int l, int r, int sum){
            this.l = l;
            this.r = r;
            this.sum = sum;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N = 100010;
    static int[] arr = new int[N]; //原数组
    static Node[] tree = new Node[N*4]; //线段树数组
    public static void build(int u, int l, int r){
        if(l == r){
            tree[u] = new Node(l,r,arr[l]);
        }else{
            tree[u] = new Node(l,r,0);
            int mid = (l+r)/2;
            build(u*2,l,mid);
            build(u*2+1,mid+1,r);
            pushUp(u);
        }
    }
    public static void pushUp(int u){
        tree[u].sum = tree[u*2].sum + tree[u*2+1].sum;
    }
    public static void modify(int u, int a, int b){
        if(tree[u].l == tree[u].r){
            tree[u].sum += b;
        }else{
            int mid = (tree[u].l + tree[u].r) / 2;
            if(a <= mid) modify(u*2,a,b);
            else modify(u*2+1,a,b);
            pushUp(u);
        }
    }
    public static int query(int u, int l, int r){
        if(l <= tree[u].l && r >= tree[u].r) return tree[u].sum;
        int mid = (tree[u].l + tree[u].r) / 2;
        int sum = 0;
        if(l <= mid) sum += query(u*2,l,r);
        if(r >= mid+1) sum += query(u*2+1,l,r);
        return sum;
    }
    public static void main(String[] args) throws IOException{
        String[] init = br.readLine().split(" ");
        int n = Integer.parseInt(init[0]);
        int m = Integer.parseInt(init[1]);
        String[] data = br.readLine().split(" ");
        for(int i = 1; i <= n; i++) arr[i] = Integer.parseInt(data[i-1]);
        build(1,1,n);
        while(m-- > 0){
            data = br.readLine().split(" ");
            int k = Integer.parseInt(data[0]);
            int a = Integer.parseInt(data[1]);
            int b = Integer.parseInt(data[2]);
            if(k == 0){
                bw.write(query(1,a,b)+"\n");
            }else{
                modify(1,a,b);
            }
        }
        bw.flush();
    }
}
```

### 求区间最大值

```java
import java.util.*;
import java.io.*;
public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static class Node{
        int l,r,max;
        Node(int l, int r, int max){
            this.l = l;
            this.r = r;
            this.max = max;
        }
    }
    static int N = 100010;
    static int[] arr = new int[N];
    static Node[] tree = new Node[N*4];
    public static void build(int u, int l, int r){
        if(l == r) tree[u] = new Node(l,r,arr[l]);
        else{
            tree[u] = new Node(l,r,Integer.MIN_VALUE);
            int mid = (l + r) / 2;
            build(u*2,l,mid);
            build(u*2+1,mid+1,r);
            pushUp(u);
        }
    }
    public static void pushUp(int u){
        tree[u].max = Math.max(tree[u*2].max,tree[u*2+1].max);
    }
    public static int query(int u, int l, int r){
        if(l <= tree[u].l && r >= tree[u].r) return tree[u].max;
        int mid = (tree[u].l + tree[u].r) / 2;
        int maxv = Integer.MIN_VALUE;
        if(l <= mid) maxv = Math.max(maxv,query(u*2,l,r));
        if(r >= mid + 1) maxv = Math.max(maxv,query(u*2+1,l,r));
        return maxv;
    }
    public static void main(String[] args) throws IOException{
        String[] init = br.readLine().split(" ");
        int n = Integer.parseInt(init[0]);
        int m = Integer.parseInt(init[1]);
        String[] data = br.readLine().split(" ");
        //下标从1开始
        for(int i = 1; i <= n; i++) arr[i] = Integer.parseInt(data[i-1]);
        build(1,1,n);
        while(m-- > 0){
            data = br.readLine().split(" ");
            int l = Integer.parseInt(data[0]);
            int r = Integer.parseInt(data[1]);
            bw.write(query(1,l,r) + " \n");
        }
        bw.flush();
    }
}
```

