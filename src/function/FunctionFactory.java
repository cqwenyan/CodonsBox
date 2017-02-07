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
		case "Filter":
			oper = new Filter();
			break;
		case "300":
			oper = new Longer300();
			break;
		case "CircosGC":
			oper = new CircosGC();
			break;
		}
		return oper;
	}
	
	
	
}
