package function;

public class FunctionFactory {

	public static Operation CreateOperation(String operation) {
		Operation oper = null;
		switch (operation) {
		case "Filter":
			oper = new Filter();
			break;
		case "300":
			oper = new Longer300();
			break;
		case "GC":
			oper = new GC();
			break;
		case "CircosGC":
			oper = new CircosGC();
			break;
		case "Synonym codon shuffle":
			oper = new SynonymCodonShuffle();
			break;
		case "KLD_Pvalue":
			oper = new KLD_P_value();
			break;
		case "KLD":
			oper = new KLD();
			break;
		case "Codon shuffle":
			oper = new CodonShuffle();
			break;
		case "PR2 Plot":
			oper = new PR2Plot();
			break;
		case "Neutrality plot":
			oper = new NeutralityPlot();
			break;
		case "SVMData":
			oper = new SVMData();
			break;
		default:
			break;
		}
		return oper;
	}
}
