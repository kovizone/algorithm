package com.kovizone.algorithm;

import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:kovichen@163.com">KoviChen</a>
 * @version 1.0
 */
public class Algorithm0024_LeetCode0222_CountCompleteTreeNodes {

    public int countNodes(TreeNode root) {
        int height = treeHeight(root);
        if (height <= 1) {
            return height;
        }
        if (treeHeight(root.right) == height - 1) {
            return countNodes(root.right) + (1 << (height - 1));
        }
        return countNodes(root.left) + (1 << (height - 2));
    }

    private int treeHeight(TreeNode root) {
        return root == null ? 0 : 1 + treeHeight(root.left);
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1)
                .setChildren(2, 3);

        System.out.println(root.val);
        System.out.println(root.left.val + " " + root.right.val);

        System.out.println();
        System.out.println("countNodes: " + countNodes(root));
    }

}

class TreeNode {
    int val;
    TreeNode parent;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    TreeNode(TreeNode parent, int x) {
        this.parent = parent;
        val = x;
    }

    TreeNode setChildren(int leftVal) {
        this.left = new TreeNode(this, leftVal);
        this.right = null;
        return this;
    }

    TreeNode setChildren(int leftVal, int rightVal) {
        this.left = new TreeNode(this, leftVal);
        this.right = new TreeNode(this, rightVal);
        return this;
    }

    public String str() {
        return val + "";
    }

    public String toChildrenString() {
        return left == null ? "" : (left.val + " " + (right == null ? "" : right.val));
    }

}