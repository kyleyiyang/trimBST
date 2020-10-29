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
           //System.out.println("temp="+temp.val+"temp.right="+temp.right.val);
            return cleanTree(temp,low,high);
        }
    }
    public static TreeNode newTree(TreeNode r,int l, int h) {
        if (r!=null) {
            if (r.val<l) {
                TreeNode sm = r.right;
                r.left = null;
                r=r.right;
                newTree(sm,l,h);
            } else if (r.val>h) {
                TreeNode lg = r.left;
                r.right = null;
                r=r.left;
                newTree(lg,l,h);
            } else {
                //if (r.val==l || (r.val==h && r.left.val<l)) {
                    //System.out.println(r.val);
                  //  r.left=null;
                  //  r.right=null;
                //} else 
                if (r.val==h) {
                    r.right = null;
                }
                if (r.val == l) {
                    r.left = null;
                }
                if (r.left!=null) {
                    
                    if (r.left.val<l && r.left.right!=null) {
                        r.left=r.left.right;
                    }
                    //newTree(r.left,l,h);
                } //else 
                if (r.right!=null) {
                    if (r.right.val>h && r.right.left!=null) {
                        r.right = r.right.left;
                    }
                    //newTree(r.right,l,h);
                }
                //System.out.println(r.val);
                newTree(r.left,l,h);
                newTree(r.right,l,h);
            }
        }
        return r;
    }
    
    public TreeNode cleanTree(TreeNode r, int l, int h) {
        if (r!=null) {
            TreeNode left = r.left;
            TreeNode right = r.right;
            //System.out.println(r.val);
            if (r.val<l) {
                r=right;
                //System.out.println(r.val);
            }
            if (r.val>h) {
                r=left;
            }
            cleanTree(left,l,h);
            cleanTree(right,l,h);
            
        }
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
