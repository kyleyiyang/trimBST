/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        TreeNode temp = new TreeNode();
        if (low==high) {
            return searchTree(root,low);
        } else {
        if (root.val<low) {
            temp = root.right;
        } else if (root.val>high) {
            temp = root.left;
        } else {
            temp = root;
        }
        int count=0;
        return highTree(lowTree(temp,low,0),high,0);
        }
    }
    public TreeNode lowTree(TreeNode r, int l, int n) {
        n++;
        if (r.val<l) {
            r.left=null;
            if (r.right!=null) {
                lowTree(r.right, l,n);
            }
        } 
        if (r.val>l && r.left!=null) {
            r.right=null;
            if (r.left.val<l && r.left.right!=null) {
                r.left=r.left.right;
            }
            lowTree(r.left, l,n);
        }
        if (r.val==l && n>1) {
            r.left=null;
            r.right=null;
        }
        return r;
    }
    public TreeNode highTree(TreeNode r, int h, int n) {
        
        System.out.print(r.val);
        n++;
        if (r.val<h && r.right!=null) {
            r.left=null;
            highTree(r.right, h,n);
        }
        if (r.val>h && r.left!=null) {
            r.right=null;
            if (r.left.val>h && r.left.left!=null) {
                r.left=r.left.left;
            }
            highTree(r.left, h,n);
        }
        if (r.val==h && n>1) {
            r.left=null;
            r.right=null;
        }
        return r;
    }
    public TreeNode searchTree(TreeNode r, int l) {
        if (r.val==l) {
            r.left=null;
            r.right=null;
        }
        if (r.val<l && r.right!=null) {
            return searchTree(r.right, l);
        }
        if (r.val>l && r.left!=null) {
            return searchTree(r.left, l);
        }
        return r;
    }
}
