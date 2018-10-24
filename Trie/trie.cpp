#include<iostream>
#include<map>
using namespace std;

struct trienode
{
    map<char,struct trienode*> m; // contain map(char->trienode) 
    bool endofword;  //indicator for end of word in trie
};
class trietree
{
  public:
    trienode* root;
    trietree()
    {
        root=createnode();
    }

    trienode* createnode()
    {
        trienode* r=new trienode();
        r->endofword=false;
        return r;
    }

    void insertdata(string s)
    {
        trienode *t = root;
        int i=0;
        while(i<s.size()) 
        {
            if( t->m.find(s[i]) == t->m.end())//s[i] not find in map
            {

                t->m.insert(pair <char,trienode*> (s[i],createnode()) );//here we are mapping a new trienode to s[i] (key)
                t=t->m[s[i]]; //moving to next trienode
            }
            else//s[i] is present in map 
            {
                t=t->m[s[i]]; move to next trienode
            }
            i++;

        }
        t->endofword=true;//indicating end of word 
    }

    void searchdata(string s)
    {
        trienode* t=root;
        int i=0;
        while(i<s.size())
        {
            if(t->m.find(s[i]) == t->m.end()) //char not found
            {
                cout<<"not found"<<endl;
                break;
            }
            else
            {
                t=t->m[s[i]];
            }
            i++;

        }
        if(t->endofword == true  && i==s.size())
        {
            cout<<"found"<<endl;
            return ;
        }
        cout<<"not found"<<endl;
        return ;

    }

    int deletehelper(trienode* tnode,string s,int level)//level marks the ith char in string 
    {
       if(tnode)
       {
           if(level==s.size())
           {cout<<"equal to size"<<endl;
               if(tnode->endofword==true)
               {
                   tnode->endofword=false; //remove word from trie
                   if(tnode->m.empty()) //if map of tnode is empty return 1 so that previous tnode can delete next reference
                   {
                       cout<<"empty"<<endl;
                       return 1;
                   }
                   cout<<"not empty"<<endl;
                   return 0;
               }

           }
           else
           {
               char index=s[level];
               if(deletehelper(tnode->m[index],s,level+1))
               {
                   cout<<"deleted"<<s[level]<<" ";
                   deletenode(tnode->m[index]);//delete value of map key=index
                   return (!leafnode(tnode) && tnode->m.empty());
                          //leafnode marks the end of someother word
                          //if map of tnode is not empty then also return 0 so that previous trienode don't delete next reference 
               }
           }

       }
       return 0;
    }
    void deletenode(trienode* tnode)
    {
        delete(tnode);
    }

    void deletedata(string s)
    {
        deletehelper(this->root,s,0);
        cout<<"deleted"<<endl;
    }

    bool leafnode(trienode* tnode)
    {
        return tnode->endofword==true;
    }

};
int main()
{
    trietree t;
    t.insertdata("hellon");
    t.insertdata("hey");
    t.insertdata("good");
    t.insertdata("hello");
    t.insertdata("hellobye");

    t.searchdata("hello");
    t.deletedata("hello");
    t.deletedata("he");

    t.searchdata("hellon");

    return 0;
}
