package tool;

/**
 * @author WenYan
 * @return gene array transform a gene string to a array
 */
public class EasyStringToArray {
	
	public String[] genArrays(String genString) {
		int genlength = genString.length();
		int codonNumber = genlength / 3;
		String[] genArray = new String[codonNumber];
		for (int i = 0; i < codonNumber; i++) {
			genArray[i] = genString.substring(i * 3, (i + 1) * 3);
		}
		return genArray;
	}
}