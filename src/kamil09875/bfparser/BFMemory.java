package kamil09875.bfparser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class BFMemory{
	private final Map<Integer, Integer> memory = new HashMap<>();{
		memory.put(0, 0);
	}
	
	private int p = 0;
	
	public void left(){
		--p;
	}
	
	public int get(){
		if(memory.containsKey(p)){
			return memory.get(p);
		}else{
			return 0;
		}
	}
	
	public void set(final int num){
		memory.put(p, num);
	}

	public void right(){
		++p;
	}

	public void add(){
		memory.put(p, get() + 1);
	}

	public void sub(){
		memory.put(p, get() - 1);
	}

	public void dump(){
		int xmax = 0, ymax = 0, tmp;
		int size = memory.size();
		
		Set<Entry<Integer, Integer>> entrySet = memory.entrySet();
		for(Entry<Integer, Integer> e : entrySet){
			if((tmp = digits(e.getKey())) > xmax){
				xmax = tmp;
			}
			
			if((tmp = digits(e.getValue())) > ymax){
				ymax = tmp;
			}
		}
		
		bar(xmax, ymax);
		
		Iterator<Entry<Integer, Integer>> iterator = entrySet.iterator();
		
		print(iterator.next(), xmax, ymax);
		for(int i = 1; i < size; ++i){
			bar(xmax, ymax);
			print(iterator.next(), xmax, ymax);
		}
		
		bar(xmax, ymax);
	}
	
	private static void print(final Entry<Integer, Integer> e, final int xmax, final int ymax){
		System.out.println(
			"| " + e.getKey() + repeat(xmax - digits(e.getKey()), " ") +
			" | " + e.getValue() + repeat(ymax - digits(e.getValue()), " ") +
			" |"
		);
	}
	
	private static void bar(final int xmax, final int ymax){
		System.out.println(repeat(ymax + xmax + 7, "-"));
	}
	
	private static String repeat(final int num, final String string){
		StringBuilder ret = new StringBuilder();
		for(int i = 0; i < num; ++i){
			ret.append(string);
		}
		return ret.toString();
	}
	
	private static int digits(final int value){
		return (int)(value == 0 ? 1 : Math.floor(Math.log10(value)) + 1);
	}
	
	public static void waitForUser(){
		try{
			System.in.read();
		}catch(IOException e){}
	}
}
