package com.preety.rest.webservices.fileapis.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UtilCSVFiles {

	public static List<String[]> readCSVFile(String csvFilePath) {
		String line = null;
		List<String[]> listLatLongs= new ArrayList<String[]>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
			

			while ((line = br.readLine()) != null) {
				if(line.split(",").length ==0) break;
				String[] latlongPair= line.split(",");
				listLatLongs.add(latlongPair);
				
			}
			
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listLatLongs;
	}
	
	public static double getDecimal(String coordinate) {
		int degree= Integer.parseInt(coordinate.substring(0, coordinate.indexOf("°")));
		double min= Double.parseDouble(coordinate.substring( coordinate.indexOf("°") +1, coordinate.indexOf("′")));
		double sec= Double.parseDouble(coordinate.substring( coordinate.indexOf("′")+1, coordinate.indexOf("″")));
		char direction= coordinate.charAt(coordinate.indexOf("″")+1);
		
		 double dec= degree + min/60 + sec/(60*60);
		 return dec;
	}
	public static char getDirection(String coordinate) {
		return coordinate.charAt(coordinate.indexOf("″")+1);
		
	}
	public static void main(String[] args) {
		List<String[]> list= readCSVFile("./uploads/lat-long.csv");
		int count=0;
		for(String[] ll: list) {
			String latitude= "";
			
		//	System.out.println(++count);
			++count;
			if(count ==1) continue;
			System.out.print(getDecimal(ll[0])+ " "+ getDirection(ll[0]));
			System.out.print(" : ");
			System.out.print(getDecimal(ll[1])+ " "+ getDirection(ll[1]));
			System.out.println();
		}
	}

}
