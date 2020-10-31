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
            temp = newTree(root,low,high);
           //System.out.println("temp="+temp.val+";temp.right="+temp.right.val);
            //return cleanTree(temp,low,high);
            return temp;
        }
    }
    public static TreeNode newTree(TreeNode r,int l, int h) {
        if (r!=null) {
            while (r.val<l) {
                //System.out.println("1st r="+r.val);
                r=r.right;
                //System.out.println("2nd r="+r.val);
            }
            while (r.val>h) {
                r=r.left;
            }
            if (r.val<l) {
                TreeNode sm = r.right;
                r.left = null;
                r=r.right;
                newTree(sm,l,h);
            } else if (r.val>h) {
                TreeNode lg = r.left;
                r.right = null;
                r=lg;
                newTree(lg,l,h);
            } else {
                if (r.val==h) {
                    r.right = null;
                    left_connect(r,l);
                }
                if (r.val == l) {
                    r.left = null;
                    right_connect(r,h);
                }
                while (r.left!=null && r.left.val<l) {
                    left_connect(r,l);
                }
                while (r.right!=null && r.right.val>h) {
                    right_connect(r,h);
                }
                newTree(r.left,l,h);
                newTree(r.right,l,h);
            }
        }
        return (r==null) ? null : (r.val<l ? r=r.right : r);
    }
    public static void left_connect(TreeNode r, int l) {
        if (r.left!=null && r.left.val<l && r.left.right!=null) {
            r.left=r.left.right;
        }
    }
    public static void right_connect(TreeNode r, int h) {
        if (r.right!=null && r.right.val>h && r.right.left!=null) {
            r.right = r.right.left;
        }
    }
    public TreeNode cleanTree(TreeNode r, int l, int h) {
        if (r!=null) {
            TreeNode left = r.left;
            TreeNode right = r.right;
            while (r.val<l) {
                //System.out.println("1st r="+r.val);
                r=r.right;
                //System.out.println("2nd r="+r.val);
            }
            while (r.val>h) {
                r=r.left;
            }
            /*while (r.left!=null && r.left.val<l) {
                left_connect(r,l);
            }
            while (r.right!=null && r.right.val>h) {
                right_connect(r,h);
            }*/
            cleanTree(left,l,h);
            cleanTree(right,l,h);
        }
        /*if (r!=null) {
            System.out.println(r.val);
        }*/
        return r;
    }
    
    public TreeNode searchTree(TreeNode r, int l) {
        if(r!=null) {
            if (r.val==l) {
                r.left=null;
                r.right=null;
            }
            if (r.val<l) {
                return searchTree(r.right, l);
            }
            if (r.val>l) {
                return searchTree(r.left, l);
            }
        }
        return r;
    }
}
