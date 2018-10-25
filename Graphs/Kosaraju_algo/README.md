kosaraju algorithm

it is used to find strongly connected components in a graph

stronly connected components in a graph are the components in which all vertex can be reached from any vertex in that component

idea of algo-

use dfs and store vertex in stack in dec. order of finishing reverse graph while stack not empty pop from stack and do dfs (each dfs will give a Strongly connected component)
