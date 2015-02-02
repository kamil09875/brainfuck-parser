package kamil09875.bfparser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main{
	public static void main(final String[] args) throws IOException{
		System.out.println("BrainfuckParser copyright (c)2015 Kamil Jarosz");
		
		if(args.length == 0 || args[0].equals("-h")){
			printHelp();
			return;
		}
		
		boolean execute = false;
		boolean debug = false;
		boolean java = false, cpp = false, cs = false;
		boolean inline = false;
		String fname;
		
		int i = 0;
		for(; i < args.length; ++i){
			if(!args[i].startsWith("-")){
				break;
			}
			
			switch(args[i]){
				case "-h": {
					printHelp();
					return;
				}
				
				case "-E": {
					execute = true;
					break;
				}
				
				case "-D": {
					debug = true;
					break;
				}
				
				case "-Tcs": {
					cs = true;
					break;
				}
				
				case "-Tcpp": {
					cpp = true;
					break;
				}
				
				case "-Tjava": {
					java = true;
					break;
				}
				
				case "-i": {
					inline = true;
					break;
				}
				
				default: {
					System.err.println("Unknown option: " + args[i]);
					System.err.println("Type -h for help");
					return;
				}
			}
		}
		
		InputStream stream;
		
		if(inline){
			StringBuilder code = new StringBuilder();
			
			for(; i < args.length; ++i){
				code.append(args[i]);
			}
			
			stream = new ByteArrayInputStream(code.toString().getBytes());
			fname = String.valueOf(System.nanoTime());
		}else{
			try{
				stream = new FileInputStream(args[i]);
				fname = new File(args[i]).getName();
			}catch(FileNotFoundException e){
				System.err.println("File '" + args[i] + "' not found");
				return;
			}
		}
		
		try(BFParser parser = new BFParser(stream)){
			parser.parse();
			
			if(execute){
				parser.execute(debug);
			}
			
			if(java){
				saveAs(parser.translate(Translator.JAVA), fname + ".java");
			}
			
			if(cpp){
				saveAs(parser.translate(Translator.CPP), fname + ".cpp");
			}
			
			if(cs){
				saveAs(parser.translate(Translator.CS), fname + ".cs");
			}
		}
	}
	
	private static void saveAs(final String content, final String fname) throws FileNotFoundException, IOException{
		try(FileOutputStream fos = new FileOutputStream(fname)){
			fos.write(content.getBytes());
		}
	}
	
	private static void printHelp(){
		System.out.println("Usage:");
		System.out.println("    bfparser [options] <file or code>");
		System.out.println("Options:");
		System.out.println("    -h      print help");
		System.out.println("    -E      execute code");
		System.out.println("    -D      debug mode");
		System.out.println("    -Tcs    translate to C#");
		System.out.println("    -Tcpp   translate to C++");
		System.out.println("    -Tjava  translate to Java");
		System.out.println("    -i      inline parsing, pass code directly");
		System.out.println("            instead of file names");
	}
}
