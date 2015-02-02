package kamil09875.bfparser;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import kamil09875.bfparser.syntax.BFBlock;
import kamil09875.bfparser.syntax.BFISet;
import kamil09875.bfparser.syntax.BFInstruction;

public class BFParser implements Closeable{
	private final InputStream stream;
	private BFBlock root;
	
	public BFParser(final InputStream stream){
		this.stream = stream;
	}
	
	@Override
	public void close() throws IOException{
		stream.close();
	}
	
	public void parse() throws IOException{
		root = parseBlock();
	}
	
	private BFBlock parseBlock() throws IOException{
		List<BFInstruction> instructions = new ArrayList<>();
		
		while(true){
			int ch = stream.read();
			if(ch < 0 || ch == ']'){
				return new BFBlock(instructions);
			}
			
			switch(ch){
				case '<': {
					instructions.add(BFISet.LEFT);
					break;
				}
				
				case '>': {
					instructions.add(BFISet.RIGHT);
					break;
				}
				
				case '+': {
					instructions.add(BFISet.ADD);
					break;
				}
				
				case '-': {
					instructions.add(BFISet.SUB);
					break;
				}
				
				case '.': {
					instructions.add(BFISet.OUTPUT);
					break;
				}
				
				case ',': {
					instructions.add(BFISet.INPUT);
					break;
				}
				
				case '[': {
					instructions.add(parseBlock());
					break;
				}
				
				// comment
				default: break;
			}
		}
	}
	
	public void execute(final boolean debug){
		root.execute(new BFMemory(), debug, true);
	}
	
	public String translate(final Translator translator){
		return root.translate(translator);
	}
}
