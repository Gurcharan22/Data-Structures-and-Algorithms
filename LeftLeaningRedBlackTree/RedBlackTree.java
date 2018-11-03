package com.tree;

import java.util.*;

public class RedBlackTree<K extends Comparable<K>,V>
{
  private Node root=null;
  final boolean RED=false;
  final boolean BLACK=true;
  private class Node
  {
    private K key;
    private V val;
    private Node left ,right;
    private boolean color;
     
    public Node(K key ,V value)
    {
      this.key=key;
      this.val=value;
      this.color=RED;
    }
    public void setColor(boolean newcolor)
    {
      this.color=newcolor;
    }
  }

  private Node leftRotate(Node root)
  {
   
    Node root_rchild=root.right;
    Node root_rlchild=root_rchild.left;
    
    root.right=root_rlchild;
    root_rchild.left=root;
    
    boolean tcolor=getColor(root_rchild);
    
    root_rchild.setColor(getColor(root));
    root.setColor(tcolor);
    root=root_rchild;
    return root;
  }
  
  private Node rightRotate(Node root)
  {
   
    Node root_lchild=root.left;
    Node root_lrchild=root_lchild.right;
    
    root.left=root_lrchild;
    root_lchild.right=root;
    
    boolean tcolor=getColor(root_lchild);
    root_lchild.setColor(getColor(root));
    root.setColor(tcolor);
    
    root=root_lchild;
    return root;
  }
  
  private void swapColor(Node n)
  {
    n.left.color=n.color;
    n.right.color=n.color;
    n.color=RED;
   
  }
  
  private Node add(Node root,K key, V val)
  {
    if(root==null)
    {return new Node(key,val);}
     int cmp=key.compareTo(root.key);
     if(cmp==0)
     {
       root.val=val;
     }
     else if(cmp>0)
     {
       root.right= add(root.right,key,val);
     }
     else
     {
        root.left= add(root.left,key,val);
     }
     
     if(getColor(root.left)==BLACK && getColor(root.right)==RED)
     {
       System.out.println("left rotating"+root.key);
       return leftRotate(root);
     }
     System.out.println("root"+root.key);
     if(getColor(root.left)==RED && getColor(root.left.left)==RED)
     {
       System.out.println("right rotating"+root.key);
       return rightRotate(root);
     }
     
     if(getColor(root.left)==RED && getColor(root.right)==RED)
     {
       System.out.println("swapping colors"+root.key);
       swapColor(root);
     }
     
     return root;
  }
   
  
  private int height(Node root)
  {
    if(root==null)
    {
     return 0;
    }
    
    int lheight=height(root.left);
    int rheight=height(root.right);
    if(lheight>=rheight)
    {
       return 1+lheight;
    }
    else
    {
      return 1+rheight;
    }
 
  }
  
  
  private V get(Node root,K key)
  {
    int cmp=key.compareTo(root.key);
    if(cmp==0)
    {
     return root.val;
    }
    else if(cmp>0)
    {
      return get(root.right,key);
    }

    
      return get(root.left,key);    
  }
  
  private boolean getColor(Node n)
  {
    if(n==null) return BLACK;
    return n.color;
  }
  
  
  public V get(K key)
  {
    return get(root,key);
  }
  
  public int height()
  {
    return height(root);
  }
  
  public void add(K key,V val)
  {
    root=add(root,key,val);
    root.color=BLACK;
  }
  

}
