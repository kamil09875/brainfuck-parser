package kamil09875.bfparser;

interface ITranslator{
	public String add();
	public String sub();
	public String input();
	public String output();
	public String right();
	public String left();
}

public enum Translator implements ITranslator{
	JAVA{
		@Override
		public String add(){
			return "";
		}
		
		@Override
		public String sub(){
			return "";
		}
		
		@Override
		public String left(){
			return "";
		}
		
		@Override
		public String right(){
			return "";
		}
		
		@Override
		public String input(){
			return "";
		}
		
		@Override
		public String output(){
			return "";
		}
	},
	
	CPP{
		@Override
		public String add(){
			return "++(*p)";
		}
		
		@Override
		public String sub(){
			return "";
		}
		
		@Override
		public String left(){
			return "";
		}
		
		@Override
		public String right(){
			return "";
		}
		
		@Override
		public String input(){
			return "";
		}
		
		@Override
		public String output(){
			return "";
		}
	},
	
	CS{
		@Override
		public String add(){
			return "";
		}
		
		@Override
		public String sub(){
			return "";
		}
		
		@Override
		public String left(){
			return "";
		}
		
		@Override
		public String right(){
			return "";
		}
		
		@Override
		public String input(){
			return "";
		}
		
		@Override
		public String output(){
			return "";
		}
	};
}
