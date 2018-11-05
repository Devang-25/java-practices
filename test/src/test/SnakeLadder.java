package test;

//Java program to find minimum number of dice 
//throws required to reach last cell from first 
//cell of a given snake and ladder board

import java.util.LinkedList;
import java.util.Queue;

public class SnakeLadder 
{
 // An entry in queue used in BFS
 static class qentry 
 {
     int v;// Vertex number
     int dist;// Distance of this vertex from source
 }

 // This function returns minimum number of dice 
 // throws required to Reach last cell from 0'th cell 
 // in a snake and ladder game. move[] is an array of 
 // size N where N is no. of cells on board If there 
 // is no snake or ladder from cell i, then move[i] 
 // is -1 Otherwise move[i] contains cell to which 
 // snake or ladder at i takes to.
 static int getMinDiceThrows(int move[], int n) 
 {
     boolean visited[] = new boolean[n];
     Queue<qentry> q = new LinkedList<qentry>();
     qentry qe = new qentry();
     qe.v = 1;
     qe.dist = 0;

     // Mark the node 0 as visited and enqueue it.
     visited[0] = true;
     q.add(qe);

     // Do a BFS starting from vertex at index 0
     while (!q.isEmpty()) 
     {
         qe = q.remove();
         int v = qe.v;

         // If front vertex is the destination 
         // vertex, we are done
         if (v == n - 1)
             break;

         // Otherwise dequeue the front vertex and 
         // enqueue its adjacent vertices (or cell 
         // numbers reachable through a dice throw)
         for (int j = v + 1; j <= (v + 6) && j < n; ++j) 
         {
        	 
        	 
             // If this cell is already visited, then ignore
             if (!visited[j])
             {
            	 System.out.println("qe v, dist, visited[j], j: " + qe.v +", "
            			 + qe.dist +", " + visited[j] +", " + j +", ");
                 // Otherwise calculate its distance and 
                 // mark it as visited
                 qentry a = new qentry();
                 a.dist = (qe.dist + 1);
                 visited[j] = true;

                 // Check if there a snake or ladder at 'j'
                 // then tail of snake or top of ladder
                 // become the adjacent of 'i'
                 if (move[j] != -1)
                     a.v = move[j];
                 else
                     a.v = j;
                 q.add(a);
             }
         }
     }

     // We reach here when 'qe' has last vertex
     // return the distance of vertex in 'qe'
     if(qe.v !=100) {
    	 return -1;
     }
     return qe.dist;
 }

 public static void main(String[] args) 
 {
     // Let us construct the board given in above diagram
     int N = 101;
     int moves[] = new int[N];
     for (int i = 1; i < N; i++)
         moves[i] = -1;

//     // Ladders
//     moves[2] = 21;
//     moves[4] = 7;
//     moves[10] = 25;
//     moves[19] = 28;
//
//     // Snakes
//     moves[26] = 0;
//     moves[20] = 8;
//     moves[16] = 3;
//     moves[18] = 6;
     
//  // Ladders    3
//     moves[32] = 62;
//     moves[42] = 68;
//     moves[12] = 98;
//
//  // Snakes 7
//     moves[95] = 13;
//     moves[97] = 25;
//     moves[93] = 37;
//     moves[79] = 27;
//     moves[75] = 19;
//     moves[49] = 47;
//     moves[67] = 17;

//  Ladders   4
//     moves[8] = 52;
//     moves[6] = 80;
//     moves[26] = 42;
//     moves[2] = 72;
//
////   Snakes  9
//     moves[51] = 19;
//     moves[39] = 11;
//     moves[37] = 29;
//     moves[81] = 3;
//     moves[59] = 5;
//     moves[79] = 23;
//     moves[53] = 7;
//     moves[43] = 33;
//     moves[77] = 21;
     
     // ladders
     moves[3]= 90;
     //snakes 7
     moves[99] =10;
     moves[97]= 20;
     moves[98] =30;
     moves[96] =40;
     moves[95] =50;
     moves[94] =60;
     moves[93] =70;
     
    // 9
    // 10 ladders
//     moves[3]= 5;
//     moves[7]= 8;
//     moves[44]= 56;
//     moves[36]= 54;
//     moves[88]= 91;
//     moves[77] =83;
//     moves[2] =4;
//     moves[9]= 99;
//     moves[45]= 78;
//     moves[31]= 75;
//     //5 snakes
//     moves[10] =6;
//     moves[95] =90;
//     moves[96] =30;
//     moves[97] =52;
//     moves[98] =86;



     System.out.println("Min Dice throws required is " + 
                       getMinDiceThrows(moves, N));
 }
}