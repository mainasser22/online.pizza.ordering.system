/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package porders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.*;
/**
 *
 * @author Nasser
 */
class details{
String phoneno;
String name;
String address;
int loc;
public details(String phoneno,String name,String address,int  loc)
{
    this.phoneno=phoneno;
    this.name=name;
    this.address=address;
    this.loc=loc;
     
}

public String getname(){
    return name;
}
public int getloc(){
    return loc;
}
public String getadd(){
    return address;
}
public String getphone(){
    return phoneno;
}
}

class ListNode {
        int vertex, weight;
        ListNode(int v, int w) {
            vertex = v;
            weight = w;
        }
        int getVertex() {
            return vertex;
        }
        int getWeight() {
            return weight;
        }
    }




public class Porders {
    
    static Scanner input=new Scanner(System.in);
    static double tot=0;
    static double estimated_time=0;
    static double delivery_charge=0;
    static int quan=0;
    static ArrayList<details> d=new ArrayList<details>();
    static HashMap<String, details> c_data = new HashMap<>();
   
    public static void new_cust( String phoneno,String name, String add, int loc)
{
    details new_c = new details(phoneno,name,add,loc);
    c_data.put(phoneno,new_c); 
}
    
    
    public static int[] dijkstra(int V, ArrayList<ArrayList<ListNode>> graph,int src) {
        int[] distance = new int[V];
        for (int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[src] = 0;
 
        PriorityQueue<ListNode> pq = new PriorityQueue<>((v1, v2) -> v1.getWeight() - v2.getWeight());
        pq.add(new ListNode(src, 0));

        while (pq.size() > 0) {
            ListNode current = pq.poll();
            for (ListNode n : graph.get(current.getVertex())) {
                if (distance[current.getVertex()] + n.getWeight() < distance[n.getVertex()]) {
                    distance[n.getVertex()] = n.getWeight() + distance[current.getVertex()];
                    pq.add(new ListNode(n.getVertex(),distance[n.getVertex()]));
                }
            }
        }
        return distance;
    }
    
    static double size(double price){
        System .out.println("#Enter Size you want[ (1)S\t (2)M\t (3)L\t ]");
        int c=input.nextInt();
        if (c==1)
        {  
            return price;
        }
        else if(c==2)
        {  price+=15;
        }else if(c==3)
        {  price+=30;
        }
        return price;
    }
    static void cost(int quan,double p){
         System.out.print("#Enter Quantity : ");
	      quan=input.nextInt();
             tot+=quan*p;
           System.out.print("#Subtotal="+tot+"\n");
    }
    
   static void Displaymenu()
   {
     
      System.out.println(" \t \t ****=Menu=**** \t \t  \n1. (1)Mercury.............(S:$25.00\t M:$40.00\t L:$55.00) \n2. (2)Uranus..............(S:$20.00\t M:$35.00\t L:$50.00) \n3. (3)Mars................(S:$27.00\t M:$42.00\t L:$57.00) \n4. (4)Pluto...............(S:$35.00\t M:$50.00\t L:$65.00) \n");
      System.out.println("#Enter your choice :\n");
      int p=input.nextInt();
      switch(p){
          case 1:  
         cost(quan,size(25.00));
              break;
          case 2:
             cost(quan,size(20.00));
     
              break;
          case 3:
              cost(quan,size(27.00));
              
              break;
          case 4:
              cost(quan,size(35.00));
              break;
          default:
                System.out.println("#Invalid option.");   
          }
              System.out.println("#Would you like to order again? Y/N / y/n ");
              char s=input.next().charAt(0);
		 while (s!= 'n' && s!= 'N')
               {
                   Displaymenu();
               }
                           
                 System.out.println("#Your order has been placed for home delivery");
                 System.out.println("#Please fill in the following information to complete your order..");
                 
      }
             
    static boolean c_valid(String mob){
           if(mob.length()!=11)
        return false;
    else
    {
        
        for(int i=0; i<mob.length();i++)
        {
            if (mob.charAt(i)<48 || mob.charAt(i)>57)
                return false;
        }
    }
    return true;    
      }
     static int c_info(){
                
         String name, add,phoneno;
         int loc;
          do{
          System.out.println("#Enter your mobile number\n");
          phoneno=input.next();
          if(!c_valid(phoneno))
              System.out.println("#Please enter a valid number! ");
          }while(c_valid(phoneno)==false); 
            if(c_data.containsKey(phoneno))                     
          {       
              details de = c_data.get(phoneno);
              name=de.name;
              add=de.address;
              loc=de.loc;
              System.out.println("**Hello "+name+" nice to see you again there is your data");
              System.out.println("*Mobile no. "+phoneno+"\n*Name: "+name+"\nAddress: "+add+"\n*Location:"+loc);
              System.out.print("#Deliver to the registered address?=>(press1) OR Enter a new address?=>(press 2)");
              int c=input.nextInt();
              if(c==1)
              {add=de.address;  
              System.out.print("#Previous address is still valid"); 
             
              }
              else if(c==2)
                  System.out.print("#Enter your new address: ");  
               input.nextLine(); 
               add= input.nextLine();
               System.out.print("#Added successfuly ");  
          }
            else{
   
                System.out.println("#Enter your name");
                name=input.next();
                System.out.println("#Enter your address");
                 add=input.next();
                 System.out.println("#Delivery is available to the following locations: \n1. (1)Maadi  \n2. (2)Mokatam  \n3. (3)Nasr City  \n4. (4)Fifth Settlement  \n5. (5)Rehab  \n6. (6)First settlement  \n7. (7)Third settlement  \n8. (8)katamia");
                 System.out.println("#Enter your location"); 
                 loc=input.nextInt();
                   new_cust(phoneno,name,add,loc);
                }
            return loc;
          }
     
        public static void calc_min(int loc){
         int V = 9;
        ArrayList<ArrayList<ListNode>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        int source = 0;
        graph.get(0).add(new ListNode(1, 4));
        graph.get(0).add(new ListNode(7, 8));
        graph.get(1).add(new ListNode(2, 8));
        graph.get(1).add(new ListNode(7, 11));
        graph.get(1).add(new ListNode(0, 7));
        graph.get(2).add(new ListNode(1, 8));
        graph.get(2).add(new ListNode(3, 7));
        graph.get(2).add(new ListNode(8, 2));
        graph.get(2).add(new ListNode(5, 4));
        graph.get(3).add(new ListNode(2, 7));
        graph.get(3).add(new ListNode(4, 9));
        graph.get(3).add(new ListNode(5, 14));
        graph.get(4).add(new ListNode(3, 9));
        graph.get(4).add(new ListNode(5, 10));
        graph.get(5).add(new ListNode(4, 10));
        graph.get(5).add(new ListNode(6, 2));
        graph.get(6).add(new ListNode(5, 2));
        graph.get(6).add(new ListNode(7, 1));
        graph.get(6).add(new ListNode(8, 6));
        graph.get(7).add(new ListNode(0, 8));
        graph.get(7).add(new ListNode(1, 11));
        graph.get(7).add(new ListNode(6, 1));
        graph.get(7).add(new ListNode(8, 7));
        graph.get(8).add(new ListNode(2, 2));
        graph.get(8).add(new ListNode(6, 6));
        graph.get(8).add(new ListNode(7, 1));

        int[] distance = dijkstra(V, graph, source);
        
      
        for (int i = 1; i < V; i++) 
        {
             
            if(distance[i]==distance[loc])
            {  
             estimated_time=10*distance[i];
             delivery_charge=5*distance[i];
             tot+=estimated_time+delivery_charge;
             System.out.println("-----------------Cart-------------------");
             System.out.print(" #Distance calculated= " + distance[i]+ " km "+" \n #The estimated delivery time = "+estimated_time+"\n #Delivery Charge= "+delivery_charge+" LE "+" \n #Total bill amount= " +tot+" LE \n" );
             System.out.println("----------------------------------------");
                break;
                
            }
          
        }
         
         }  
   
   
   
    public static void main(String[] args) {
        
        System.out.println("\t \t *******-Welcome to our Pizza invision-*******\t \t");
        System.out.println("#To view the menu card,press 1");
        System.out.println("#To Exit,press 0");
        int m=input.nextInt();
        if(m==1)
            Displaymenu();
        else
        { System.out.println(" #Thank you visit again...");
            System.exit(0);
        }
        new_cust("01258649832","ahmed","23st",1);
        new_cust("01258655832","ehab","25st",2);
        calc_min(c_info());
         System.out.println("#Do you want to confirm your order and pay the bill amount or Cancel it\n"+" =>(To confirm press1 / To cancel press0)");
            int x=input.nextInt();
          if(x==1)
          { 
              System.out.println("**Enjoy your meal**");
              System.out.println("**Hope you visit us again soon**...");
          }
        else
        { System.out.println(" #Thank you visit again...");
            System.exit(0);
        }
        
    }
    
}
