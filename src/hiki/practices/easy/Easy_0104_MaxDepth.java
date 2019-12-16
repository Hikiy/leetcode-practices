package hiki.practices.easy;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
* 104. 二叉树的最大深度
*/
public class Easy_0104_MaxDepth {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //递归法
    //Time  complexity  O(n)
    //Space complexity  最坏O(n) 最好O(log(n))
    public int maxDepth(TreeNode root) {
        if( root == null ) return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1 ;
    }

    //迭代法
    //Time  complexity  O(n)
    //Space complexity  O(n)
    public int maxDepth2(TreeNode root) {
        Queue<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root != null) {
            stack.add(new Pair(root, 1));
        }

        int depth = 0;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.poll();
            root = current.getKey();
            int current_depth = current.getValue();
            if (root != null) {
                depth = Math.max(depth, current_depth);
                stack.add(new Pair(root.left, current_depth + 1));
                stack.add(new Pair(root.right, current_depth + 1));
            }
        }
        return depth;
    }
}
