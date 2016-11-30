package function;


public class FunctionFactory {
//	private FunctionFactory(){}
//	private static FunctionFactory instance = new FunctionFactory();
//	public static FunctionFactory getFunctionFactory(){
//		return instance;
//	}
//	
	
	//,String path,String fileName 
	public static Operation CreateOperation(String operation){
		Operation oper = null;
		switch(operation){
		case "GC123":
			oper = new GC123();
			break;
			
		}
		return oper;
	}
	
	
	
}
