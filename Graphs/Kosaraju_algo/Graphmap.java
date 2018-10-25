
import java.util.*;
class Graphmap
{
  int v; //number of vertices
  HashMap<Integer,LinkedList<Integer>> map;//(adjacency list)hashmap of key mapped to linkedlist of adjacent vertices 
  
  Graphmap()
  {
    v=0;
    map=new HashMap<Integer,LinkedList<Integer>>();
  }


  void addEdge(int x,int y)
  {
   
    if(!map.containsKey(x)) //check map contain key
    {
      map.put(x,new LinkedList<Integer>());//adding new key to map
      if(y==-1) //if key doesnot connected to any vertex
      {return;}
    }
    
   /* if(!map.containsKey(y)) //optional for undirected graph
    {
      map.put(y,new LinkedList<Integer>());
    }
    */
     map.get(x).add(y);
     //map.get(y).add(x);
    
  }
  
  
  void dfs(int key,HashSet<Integer> visited)
  {
    visited.add(key);
    System.out.print(key);
    if(this.map.get(key)!=null)
    {
      Iterator<Integer> itr= this.map.get(key).iterator();
    
      while(itr.hasNext())
      {
        int adjedge=itr.next();
      
        if(!visited.contains(adjedge))
        {
          dfs(adjedge,visited);
        }
       }
     }
   }
  
  
  void dfs(int key,HashSet<Integer> visited,Stack<Integer> s)
  {
    visited.add(key);
  
    Iterator<Integer> itr= map.get(key).iterator();
    while(itr.hasNext())
    {
      int adjedge=itr.next();
      
      if(!visited.contains(adjedge))
      {
        dfs(adjedge,visited,s);
      }
    }
    s.push(key);
    
    
  }
  
  void scc()
  {
    HashSet<Integer> visited=new HashSet<Integer>();
 
    Stack<Integer> s=new Stack<Integer>(); //stack for storing vertex according to their decreasing time of completion in dfs (at top would be vertex with highest time to finish)
    
    System.out.println("map");
    
    for(Map.Entry<Integer,LinkedList<Integer>> m:map.entrySet())//using a set to traverse map
    {
      int key=m.getKey(); 
      if(!visited.contains(key))
      {
        dfs(key,visited,s);
      }
    }

    Graphmap rgraph=reversegraph(); //get reverse graph
  
    visited.clear();
    System.out.println("strongly connected components");
    
    while(!s.empty())
    {
      int parent=s.pop();
      if(!visited.contains(parent))
      {
        rgraph.dfs(parent,visited); //get the scc components
        System.out.println();
      }
    }
       
  }
  
  Graphmap reversegraph()
  {
     Graphmap rgraph=new Graphmap();
     for(Map.Entry<Integer,LinkedList<Integer>> m:map.entrySet())
     {
       int parent=m.getKey();
       Iterator<Integer> itr=m.getValue().iterator();
       while(itr.hasNext())
       {
         int child=itr.next();
         rgraph.addEdge(child,parent); //make child as parent and  parent as child in new graph
       }
       
     }
    return rgraph;
  }

  
  void print()
  {System.out.println("printing");
     for(Map.Entry<Integer,LinkedList<Integer>> m:map.entrySet())
    {
      System.out.print("key"+m.getKey()+" ");
      LinkedList<Integer> mylist=m.getValue();
      Iterator<Integer> itr=mylist.iterator();
      while(itr.hasNext())
      {
        System.out.print(itr.next());
      }
    }
    System.out.println();
  }


  public static void main(String[] args) 
  {
    Graphmap g=new Graphmap();
    g.addEdge(0,1);
    g.addEdge(1,-1);
    g.addEdge(2,3);
    g.addEdge(3,-1);
    g.addEdge(2,0);
     
    g.print();
    g.scc();//strongly connected components
   
 
    
  }
}
