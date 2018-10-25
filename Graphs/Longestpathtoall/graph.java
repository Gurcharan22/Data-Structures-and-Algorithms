import java.util.*;
class graph 
{
  int v;
  class Node
  {
    int v;
    int weight;
    Node(int v,int weight)
    {
      this.v=v;
      this.weight=weight;
    }
  }
  ArrayList<ArrayList<Node>> arr; 
  
  graph(int v)
  {
    this.v=v;
    arr=new ArrayList<ArrayList<Node>>(v);
    int i=0;
    
    while(i<v)
    {
      arr.add(i,new ArrayList<Node>());
      i++;
    }
  }
  
  void addEdge(int u,int v,int weight)
  {
   arr.get(u).add(new Node(v,weight));
  }
  
  void dfs(int u,boolean []visited,Stack<Integer> s)
  {
    visited[u]=true;
    System.out.println("visited"+u);
    ArrayList<Node> adjedges=arr.get(u);
    
    int i=0;
    while(i<adjedges.size())
    {
      int v=adjedges.get(i).v;
      if(!visited[v])
      {
        dfs(v,visited,s);
      }
      i++;
    }
    s.push(u);
  }
 
  void allLongestpathfrom(int start)
  {
    boolean visited[]=new boolean[v];
    int i=0;
    while(i<v)
    {
      visited[i]=false;
      i++;
    }
    
    Stack<Integer> s=new Stack<Integer>();//used to put edges in decreasing order of finishing time in dfs (topologicial sort)
    i=0;
    while(i<v)
    {
      if(!visited[i])
      {
        dfs(i,visited,s);
      }
      i++;
    }
 
    int dist[]=new int[v]; //this will contain max dist of all vertices from start
    i=0;
    while(i<v)
    {
      dist[i]=Integer.MIN_VALUE;
      i++;
    }
    
    dist[start]=0;
    
    while(!s.empty())
    {
      int u=s.pop();
      
      if(dist[u]!=Integer.MIN_VALUE)
      {
        Iterator<Node> itr=arr.get(u).iterator();
        while(itr.hasNext())
        {
          Node temp=itr.next();
          if(dist[temp.v]< dist[u] +temp.weight)
          dist[temp.v]=dist[u]+temp.weight;
        
        }
      }
    }
    i=0;
    System.out.println("maximum distance of all vertices from "+start);
    while(i<v)
    {
      if(dist[i]==Integer.MIN_VALUE)
      {
        System.out.print("NINF ");//negative infinity
        i++;
        continue;
      }
      System.out.print(dist[i]+" ");
      i++;
    }
  }
  
  public static void main(String[] args) 
  {
    
    graph g=new graph(6);
    g.addEdge(0, 1, 5); 
    g.addEdge(0, 2, 3); 
    g.addEdge(1, 3, 6); 
    g.addEdge(1, 2, 2); 
    g.addEdge(2, 4, 4); 
    g.addEdge(2, 5, 2); 
    g.addEdge(2, 3, 7); 
    g.addEdge(3, 5, 1); 
    g.addEdge(3, 4, -1); 
    g.addEdge(4, 5, -2); 
    int start=1;
    
    g.allLongestpathfrom(start);
    
    
  }
}
