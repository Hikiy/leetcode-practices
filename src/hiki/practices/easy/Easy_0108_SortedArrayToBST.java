package hiki.practices.easy;
/**
* 108. 将有序数组转换为二叉搜索树
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
*/
public class Easy_0108_SortedArrayToBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        return initTree(nums,0,nums.length-1);
    }

    public TreeNode initTree(int[] nums, int l, int r){
        if( l == r ) return new TreeNode(nums[l]);
        if( l > r ) return null;

        int mid = (l + r + 1) >> 1;

        TreeNode t = new TreeNode(nums[mid]);
        t.left = initTree(nums, l, mid - 1);
        t.right = initTree(nums, mid + 1, r);

        return t;
    }
}