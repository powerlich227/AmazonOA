import java.util.*;

public class MaximumAverageSubtree {
	double max; 
	public double maximumAverageSubtree(TreeNode root) {
		max = 0.0;
		helper(root);
		return max;
	}
	public double[] helper(TreeNode root) {
		double[] res = new double[2];
		if (root == null)
			return res;
		double[] l = helper(root.left);
		double[] r = helper(root.right);
		res[0] = l[0] + r[0] + root.val;
		res[1] = l[1] + r[1] + 1.0;
		max = Math.max(max, res[0] / res[1]);
		return res;
	}
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) {
			this.val = val;
		}
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}
