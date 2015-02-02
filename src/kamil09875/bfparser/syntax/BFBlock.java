package kamil09875.bfparser.syntax;

import java.util.List;

import kamil09875.bfparser.BFMemory;
import kamil09875.bfparser.Translator;

public class BFBlock implements BFInstruction{
	private final List<BFInstruction> instructions;
	
	public BFBlock(final List<BFInstruction> instructions){
		this.instructions = instructions;
	}
	
	@Override
	public void execute(final BFMemory memory, final boolean debug){
		execute(memory, debug, false);
	}
	
	public void execute(final BFMemory memory, final boolean debug, final boolean main){
		if(main){
			for(BFInstruction instr : instructions){
				instr.execute(memory, debug);
			}
		}else{
			while(memory.get() != 0){
				for(BFInstruction instr : instructions){
					instr.execute(memory, debug);
				}
			}
		}
	}
	
	@Override
	public String translate(final Translator translator){
		StringBuilder ret = new StringBuilder();
		for(BFInstruction instr : instructions){
			ret.append(instr.translate(translator));
		}
		return ret.toString();
	}
}
