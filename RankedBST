import java.util.*;
class RankBst
{

  private class Node 
  {
    private int data;
    private Node left,right;
    public int size; //for number of nodes in subtree
    Node(int v)
    {
      data=v;
      left=null;
      right=null;
      size=1;
    }
   }
  
  public Node root;
  
  private int calnodes(Node temp) //calculate nodes in subtree
  {
    if(temp==null)
      return 0;
    
    int lsize=calnodes(temp.left);
    int rsize=calnodes(temp.right);
    
    
    return 1+lsize+rsize;
  }
  
  private int size(Node root) //return root.size 
  {
    if(root==null)
      return 0;
    else
      return root.size;
  }
  
  private Node insertdata(Node temp,int v)
  {
    if(temp==null)
      return new Node(v);
    if( v < temp.data)
      temp.left=insertdata(temp.left,v);
    
    if( v > temp.data)
      temp.right=insertdata(temp.right,v);
   
    temp.size= 1 + calnodes(temp.left) +calnodes(temp.right);
    
    return temp;
  }
  
  public RankBst() //constructor
  {
    root=null;
  }
   
  
  public void insertdata(int v)
  {
    root=insertdata(root,v);
  }
  
  public int getrankwrapper(Node temp,int key)
  {
   
    if(key==temp.data)
    {
      return size(temp.left);
    }
    else if(key<temp.data)
      return getrankwrapper(temp.left,key);
    else
     return 1+size(temp.left)+getrankwrapper(temp.right,key); 
  }
  
  public void getRank(int key)
  {
     int temp=getrankwrapper(root,key);
    System.out.println("rank of key "+key+" = "+temp);
  }
  
  
  public void inorder(Node temp)
  {
    if(temp==null)
      return;
    
    inorder(temp.left);
    System.out.print(temp.data+" ");
    inorder(temp.right);
  }
 
  
  public static void main(String[] args) 
  {
    RankBst t=new RankBst();
    t.insertdata(5);
    t.insertdata(3);
    t.insertdata(2);
    t.insertdata(4);
    t.insertdata(6);
   
    t.getRank(6);
    t.inorder(t.root); 
  }
}
