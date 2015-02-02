package kamil09875.bfparser.syntax;

import kamil09875.bfparser.BFMemory;
import kamil09875.bfparser.Translator;

public interface BFInstruction{
	public default void execute(final BFMemory memory){
		execute(memory, false);
	}
	
	public void execute(BFMemory memory, boolean debug);
	public String translate(final Translator translator);
}
