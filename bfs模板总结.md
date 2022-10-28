# bfs模板总结

bfs以及dfs都是蓝桥杯常考题

以下以LeetCode111.二叉树的最小深度https://leetcode.cn/problems/minimum-depth-of-binary-tree/description/为例引出bfs的经典模板

最小深度就是当存在结点没有孩子结点就返回

```java
 //最小深度bfs经典模板
class Solution {
    public int minDepth(TreeNode root) {
        Queue<TreeNode> que = new LinkedList<>();
        if(root == null) return 0;
        que.offer(root);
        int depth = 0;
        while(!que.isEmpty()){
            depth++;
            int size = que.size();
            while(size > 0){
                size--;
                TreeNode cur = que.poll();
                if(cur.left != null) que.offer(cur.left);
                if(cur.right != null) que.offer(cur.right);
                if(cur.left == null && cur.right == null) return depth;
            }
        }
        return depth;
    }
}
```

对比：LeetCode 104. 二叉树的最大深度

https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/

与最小深度唯一的不同就是去掉了

`if(cur.left == null && cur.right == null) return depth;`这一行，因为最大深度就是要遍历每一层 直接套用层次遍历模板遍历完即可

```java
//使用bfs模板解
    public int maxDepth(TreeNode root) {
        Queue<TreeNode> que = new LinkedList<>();
        if(root == null) return 0;
        que.offer(root);
        int depth = 0;
        while(!que.isEmpty()){
            depth++;
            int size = que.size();
            while(size > 0){
                size--;
                TreeNode cur = que.poll();
                if(cur.left != null) que.offer(cur.left);
                if(cur.right != null) que.offer(cur.right);
                // if(cur.left == null && cur.right == null) return depth;
            }
        }
        return depth;
    }
```

