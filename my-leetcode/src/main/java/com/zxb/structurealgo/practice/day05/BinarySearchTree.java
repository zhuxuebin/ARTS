package com.zxb.structurealgo.practice.day05;

import java.util.*;

/**
 * @ClassName BinarySearchTree
 * @Description 二叉搜索树相关操作
 *
 * 1、 增删查
 * 2、 前继、后继
 * 3.  中后续遍历（递归+非递归）及按层遍历
 * @Author xuery
 * @Date 2019/5/8 9:30
 * @Version 1.0
 */
public class BinarySearchTree {


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        public TreeNode(int val){
            this.val = val;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }

    public static void main(String[] args) {
        //1.增删查
        System.out.println("#################1.增删查################");

        BinarySearchTree bst01 = new BinarySearchTree();
        bst01.add(4);
        bst01.add(3);
        bst01.add(1);
        bst01.add(5);
        bst01.add(7);
        bst01.add(6);

        TreeNode findNode = bst01.find(5);
        System.out.println(findNode.toString());

        bst01.delete(3);
        bst01.delete(5);

        //2.前继、后继
        System.out.println("###############2.前继、后继##################");
        BinarySearchTree bst02 = new BinarySearchTree();
        bst02.add(4);
        bst02.add(3);
        bst02.add(1);
        bst02.add(5);
        bst02.add(7);
        bst02.add(6);

        System.out.println(bst02.successorNode(4));
        System.out.println(bst02.successorNode(3));
        System.out.println(bst02.successorNode(6));

        System.out.println(bst02.postNode(4));
        System.out.println(bst02.postNode(3));
        System.out.println(bst02.postNode(6));

        //3.前中后序及按层遍历
        System.out.println("#################3.前中后序及按层遍历 #####################");
        BinarySearchTree bst03 = new BinarySearchTree();
        bst03.add(4);
        bst03.add(3);
        bst03.add(1);
        bst03.add(5);
        bst03.add(7);
        bst03.add(6);

        System.out.println("######3.1前序######");
        bst03.preOrderRecursion(bst03.root);
        System.out.println();
        bst03.preOrderLoop(bst03.root);
        System.out.println();

        System.out.println("######3.2中序######");
        bst03.inOrderRecursion(bst03.root);
        System.out.println();
        bst03.inOrderLoop(bst03.root);
        System.out.println();

        System.out.println("######3.3后序######");
        bst03.postOrderRecurison(bst03.root);
        System.out.println();
        bst03.postOrderLoop(bst03.root);
        System.out.println();

        System.out.println("######3.4按层遍历######");
        bst03.printByLevel(bst03.root);
    }

    private TreeNode root = null;

    //1.1 增
    public void add(int  val){

        TreeNode newNode = new TreeNode(val);

        if(root == null){
            root = newNode;
            return;
        }

        TreeNode p = root;
        while(p != null){
            if(newNode.val < p.val){
                if(p.left == null){
                    break;
                }
                p = p.left;
            } else {
                if(p.right == null){
                    break;
                }
                p = p.right;
            }
        }

        if(newNode.val < p.val){
            p.left = newNode;
        } else {
            p.right = newNode;
        }
        newNode.parent = p;
    }

    //1.2 删,这里假设二叉搜索树中没有重复值；如果有则需要多次遍历
    public void delete(int val){
        //先查找
        TreeNode targetNode = find(val);
        if(targetNode == null){
            return;
        }

        if(targetNode.left != null){
            //用左边的最大值替换当前节点，并将之前左边的最大值所在节点删除
            TreeNode p = targetNode.left;
            while(p.right != null){
                p = p.right;
            }
            targetNode.val = p.val;
            if(p == p.parent.left){
                p.parent.left = null;
            } else{
                p.parent.right = null;
            }
        } else if(targetNode.right != null){
            //用右边的最小值替换当前节点，并将之前右边的最小值所在节点删除
            TreeNode p = targetNode.right;
            while(p.left != null){
                p = p.left;
            }
            targetNode.val = p.val;
            if(p == p.parent.left){
                p.parent.left = null;
            } else{
                p.parent.right = null;
            }
        } else{
            //删除targetNode本身即可,有可能就是root
            if(targetNode == root){
                root = null;
            } else if(targetNode == targetNode.parent.left){
                targetNode.parent.left = null;
            } else if(targetNode == targetNode.parent.right){
                targetNode.parent.right = null;
            }
        }
    }

    //1.3 查
    public TreeNode find(int val){

        TreeNode p = root;
        while(p != null){
            if(p.val == val){
                return p;
            } else if(p.val < val){
                p = p.right;
            } else{
                p = p.left;
            }
        }
        return null;
    }

    //2.前继、后继
    public TreeNode successorNode(int val){
        TreeNode findNode =  find(val);
        if(findNode == null){
            return null;
        }

        if(findNode.left != null){
            //找左边的最大值
            TreeNode p = findNode.left;
            while(p.right != null){
                p = p.right;
            }
            return p;
        } else {
            //该节点为父节点的左子树，则继续遍历父节点的父节点，直至某个节点是父节点的右子树，则取父节点；没有返回null
            //该节点为父节点的右子树，则直接取父节点
            TreeNode p = findNode;
            while(p!=null){
                if(p.parent.left == p){
                    p = p.parent;
                } else {
                    return p.parent;
                }
            }
            return null;
        }
    }

    public TreeNode postNode(int val){
        TreeNode findNode = find(val);
        if(findNode == null){
            return null;
        }

        if(findNode.right != null){
            //找右边的最小值
            TreeNode p = findNode.right;
            while(p.left != null){
                p = p.left;
            }
            return p;
        } else {
            //类似找前继节点
            TreeNode p = findNode;
            while(p != null){
                if(p.parent.right == p){
                    p = p.parent;
                } else {
                    return p.parent;
                }
            }
            return null;
        }
    }

    //前中后序、分层遍历
    public void preOrderRecursion(TreeNode root){
        //前序：根左右
        if(root == null){
            return;
        }

        System.out.print(root.val+" ");
        if(root.left != null){
            preOrderRecursion(root.left);
        }
        if(root.right != null){
            preOrderRecursion(root.right);
        }
    }

    public void preOrderLoop(TreeNode root){

        if(root == null){
            return;
        }

        //栈，父节点先进，然后出栈，分别右子树先入栈、左子树再进栈；然后重复该过程直至栈为空
        List<Integer> orderList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode pop = stack.pop();
            orderList.add(pop.val);
            if(pop.right != null){
                stack.push(pop.right);
            }
            if(pop.left != null){
                stack.push(pop.left);
            }
        }

        for(Integer val : orderList){
            System.out.print(val+" ");
        }
    }


    public void inOrderRecursion(TreeNode root){
        if(root == null){
            return;
        }

        if(root.left != null){
           inOrderRecursion(root.left);
        }
        System.out.print(root.val + " ");
        if(root.right != null){
            inOrderRecursion(root.right);
        }
    }

    public void inOrderLoop(TreeNode root){
        if(root == null){
            return;
        }

        //还是栈，左根右：先将根节点入栈，之后递归看是否有左子树，有则将左子树入栈；
        // 然后开始出栈，看当前元素是否有右子树，有则将右子树入栈，并递归看右子树是否有左子树，有则依次入栈
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while(p!= null){
            stack.add(p);
            p = p.left;
        }

        List<Integer> orderList = new ArrayList<>();
        while(!stack.isEmpty()){
            TreeNode pop = stack.pop();
            orderList.add(pop.val);
            if(pop.right != null){
                stack.add(pop.right);
                pop = pop.right;
                while(pop.left != null){
                    stack.add(pop.left);
                    pop = pop.left;
                }
            }
        }

        for(Integer val: orderList){
            System.out.print(val+" ");
        }
    }

    public void postOrderRecurison(TreeNode root){
        if(root == null){
            return;
        }

        if(root.left != null){
            postOrderRecurison(root.left);
        }
        if(root.right != null){
            postOrderRecurison(root.right);
        }
        System.out.print(root.val + " ");
    }

    public void postOrderLoop(TreeNode root){
        //左右根-->转换为根右左，类似于前序了，最后将顺序倒转即可
        if(root == null){
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        List<Integer> orderList = new ArrayList<>();
        while(!stack.isEmpty()){
            TreeNode pop = stack.pop();
            orderList.add(pop.val);
            if(pop.left != null){
                stack.push(pop.left);
            }
            if(pop.right != null){
                stack.push(pop.right);
            }
        }

        //根右左-->左右根
        for(int i=orderList.size()-1;i>=0;i--){
            System.out.print(orderList.get(i)+" ");
        }
    }

    public void printByLevel(TreeNode root){
        if(root == null){
            return;
        }

        //借助队列，每层要记录下个数，然后依次出栈相同的个数，并将对应的左右子树存入队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int count = 1; //初始化每层个数计数器
        while(!queue.isEmpty()){

            int tempCnt = 0; //记录下一层的个数
            while(count-- > 0){
                TreeNode poll = queue.poll();
                System.out.print(poll.val+" ");
                if(poll.left != null){
                    queue.add(poll.left);
                    tempCnt++;
                }
                if(poll.right != null){
                    queue.add(poll.right);
                    tempCnt++;
                }
            }
            count = tempCnt;
        }
    }

}
