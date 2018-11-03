import com.tree.RedBlackTree;

class Test {
  public static void main(String[] args) {
    
   RedBlackTree<Integer,String> rbtree= new RedBlackTree<Integer,String>();
   
    rbtree.add(0,"first");
    rbtree.add(1,"second");
    rbtree.add(2,"third");
    rbtree.add(3,"fourth");
    rbtree.add(4,"fifth");
    
    System.out.println(rbtree.height());
    
    
  }
}
