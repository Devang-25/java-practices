package test;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Island {
    
   
    static int buildRegion(int count, int[][] a,int n, int m, int i, int j, boolean[][]isUsed){

        if(!isUsed[i][j] && a[i][j] == 1) {
             count++;
            isUsed[i][j]=true;

            count= j+1 <m ? buildRegion(count,a,n,m, i,j+1, isUsed) : count;
            count= (i+1 <n && j-1 >=0) ? buildRegion(count,a,n,m, i+1,j-1, isUsed): count;
            count= i+1 <n ? buildRegion(count,a,n,m, i+1,j, isUsed) : count;
            count= i+1 <n && j+1 <m ? buildRegion(count,a,n,m, i+1,j+1, isUsed) : count;
        }
        return count;
    }
    
    static boolean isIsland(int[][] grid) {
        int rows= grid.length;
        int cols= grid[0].length;
        
    	boolean [][] isUsed= new boolean[rows][cols];
    	for(int i=0; i<rows; i++) {
    		for(int j=0; j<cols; j++) {
        		int count= buildRegion(0, grid, rows, cols, i,j, isUsed);
        		if(count>1) {
        			return true;
        		}
        	}
    	}
    	return false;
    }
    

    // Complete the maxRegion function below.
    static int maxRegion(int[][] grid) {
        int rows= grid.length;
        int cols= grid[0].length;
       
        boolean [][] isUsed= new boolean[rows][cols];
        int max=0;
        
        for(int i=0; i<rows ; i++){
            for( int j=0; j<cols ; j++){
                int regionCount= buildRegion(0, grid, rows, cols, i,j, isUsed);
                if(regionCount> max) max=regionCount;
            }
        }

        return max;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
       int[][] grid= new int[][] {
    	   { 0, 0, 1, 1},
    	   { 0, 0, 1, 0},
    	   { 0, 0, 1, 0},
    	   { 0, 1, 0, 1},
    	   { 1, 1, 0, 0 }
       };
     //  grid= new int[][] {{0,0,1}};

        int res = maxRegion(grid);
        System.out.println("length of max size island region");
        System.out.println(res);
        
        boolean isIsland= isIsland(grid);
        System.out.println("Does it form island");
        System.out.println(isIsland);
    }
}

/*
 Inputs:
 
 5
4
0 0 1 1
0 0 1 0
0 1 1 0
0 1 0 0
1 1 0 0
 
 
7
5
1 1 1 0 1
0 0 1 0 0
1 1 0 1 0
0 1 1 0 0
0 0 0 0 0
0 1 0 0 0
0 0 1 1 0


8
9
0 1 0 0 0 0 1 1 0
1 1 0 0 1 0 0 0 1
0 0 0 0 1 0 1 0 0
0 1 1 1 0 1 0 1 1
0 1 1 1 0 0 1 1 0

*/
