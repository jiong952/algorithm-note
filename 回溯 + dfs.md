# 回溯 + dfs

lc784. 字母大小写全排列

https://leetcode.cn/problems/letter-case-permutation/description/

tips1：

- 查看ASCII表可以发现ASCII在 65-90 之间是大写字母，97-122 是小写字母，即大小写相差32，可以使用位运算进行快捷转换

- 位运算进行字母大小写转换`arr[index] ^= 32;`

tips2：

- 使用Character.isDigit(arr[index])快捷判断是否是数字

## 代码

```java
class Solution {
    List<String> res;
    int len;
    public List<String> letterCasePermutation(String s) {
        res = new ArrayList<>();
        len = s.length();
        dfs(s.toCharArray(),0);
        return res;
    }
    public void dfs(char[] arr, int index){
        while(index < len && Character.isDigit(arr[index])) index++;
        //最底端
        if(index == len){
            res.add(new String(arr));
            return;
        }
        //先变大小写
        arr[index] ^= 32;
        dfs(arr,index + 1);
        //变回来
        arr[index] ^= 32;
        dfs(arr,index + 1);
    }
}
```

## 遍历过程

[A1B2,A1b2,a1b2,a1B2]