package kamil09875.bfparser.syntax;

import java.io.IOException;

import kamil09875.bfparser.BFMemory;
import kamil09875.bfparser.Translator;

public enum BFISet implements BFInstruction{
	LEFT{
		@Override
		public void execute(final BFMemory memory, final boolean debug){
			if(debug){
				System.out.println("Moving left");
				memory.dump();
			}
			
			memory.left();
			
			if(debug){
				System.out.println(" vvv");
				memory.dump();
				System.out.println();
				BFMemory.waitForUser();
			}
		}
		
		@Override
		public String translate(final Translator translator){
			return translator.left();
		}
	},
	
	RIGHT{
		@Override
		public void execute(final BFMemory memory, final boolean debug){
			if(debug){
				System.out.println("Moving right");
				memory.dump();
			}
			
			memory.right();
			
			if(debug){
				System.out.println(" vvv");
				memory.dump();
				System.out.println();
				BFMemory.waitForUser();
			}
		}
		
		@Override
		public String translate(final Translator translator){
			return translator.right();
		}
	},
	
	ADD{
		@Override
		public void execute(final BFMemory memory, final boolean debug){
			if(debug){
				System.out.println("Increasing current value");
				memory.dump();
			}
			
			memory.add();
			
			if(debug){
				System.out.println(" vvv");
				memory.dump();
				System.out.println();
				BFMemory.waitForUser();
			}
		}
		
		@Override
		public String translate(final Translator translator){
			return translator.add();
		}
	},
	
	SUB{
		@Override
		public void execute(final BFMemory memory, final boolean debug){
			if(debug){
				System.out.println("Decreasing current value");
				memory.dump();
			}
			
			memory.sub();
			
			if(debug){
				System.out.println(" vvv");
				memory.dump();
				System.out.println();
				BFMemory.waitForUser();
			}
		}
		
		@Override
		public String translate(final Translator translator){
			return translator.sub();
		}
	},
	
	OUTPUT{
		@Override
		public void execute(final BFMemory memory, final boolean debug){
			if(debug){
				System.out.println("Outputting current value");
				memory.dump();
				System.out.print("Output: " + memory.get() + " = ");
			}
			
			System.out.print((char)memory.get());
			
			if(debug){
				System.out.println(" vvv");
				memory.dump();
				System.out.println();
				BFMemory.waitForUser();
			}
		}
		
		@Override
		public String translate(final Translator translator){
			return translator.output();
		}
	},
	
	INPUT{
		@Override
		public void execute(final BFMemory memory, final boolean debug){
			if(debug){
				System.out.println("Setting current value to user input");
				memory.dump();
			}
			
			try{
				memory.set(System.in.read());
			}catch(IOException e){
				memory.set(-1);
			}
			
			if(debug){
				System.out.println(" vvv");
				memory.dump();
				System.out.println();
				BFMemory.waitForUser();
			}
		}
		
		@Override
		public String translate(final Translator translator){
			return translator.input();
		}
	};
}
