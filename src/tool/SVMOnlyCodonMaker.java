package tool;

import java.math.BigDecimal;

public class SVMOnlyCodonMaker {
	private SVMOnlyCodonMaker() {
	}
	
	private static SVMOnlyCodonMaker instance = new SVMOnlyCodonMaker();

	public static SVMOnlyCodonMaker getStringToArray() {
		return instance;
	}
	
	double codons[] = new double[62];
	int aminoAcids[] = new int[19];
	
	public String[] genArrays(String genString) {
		int genlength = 0;
		int codonNumber = 0;

		genlength = genString.length();
		codonNumber = genlength / 3;
		String[] genArray = new String[codonNumber];
		for (int i = 0; i < codonNumber; i++) {
			genArray[i] = genString.substring(i * 3, (i + 1) * 3);
		}
		return genArray;
	}

	public void initArrays(){
		for(int i = 0; i < codons.length; i++){
			codons[i]=0;
		}
		for(int i = 0; i < aminoAcids.length; i++){
			aminoAcids[i]=0;
		}
	}
	// 将一条基因变成除去终止密码子的同义密码子含量比例二维数组（各个同义密码子含量加起来等于1）
	public String resultArrays(String[] genArray,int classNumber) {
		// String[] menu = { "Phe", "Leu", "Ser", "Tyr", "Cys", "Pro", "His",
		// "Gin", "Arg", "Ile", "Thr", "Asn", "Lys", "Val", "Ala", "Asp",
		// "Glu", "Gly" };
	
		StringBuilder myStringBuilder = new StringBuilder();
//		int PheCount = 0;int LeuCount = 0;int SerCount = 0;int TyrCount = 0;int CysCount = 0;int ProCount = 0;
//		int HisCount = 0;int GinCount = 0;int ArgCount = 0;int IleCount = 0;int ThrCount = 0;int AsnCount = 0;
//		int LysCount = 0;int ValCount = 0;int AlaCount = 0;int AspCount = 0;int GluCount = 0;int GlyCount = 0;
		
		for (int i = 0; i < genArray.length; i++) {
			switch (genArray[i]) {
				// Phe
				case "TTT":codons[0]++;aminoAcids[0]++;break;
				case "TTC":codons[1]++;aminoAcids[0]++;break;
				//Leu
				case "TTA":codons[2]++;aminoAcids[1]++;break;
				case "TTG":codons[3]++;aminoAcids[1]++;break;
				case "CTT":codons[4]++;aminoAcids[1]++;break;
				case "CTC":codons[5]++;aminoAcids[1]++;break;
				case "CTA":codons[6]++;aminoAcids[1]++;break;
				case "CTG":codons[7]++;aminoAcids[1]++;break;
				//Ser
				case "TCT":codons[8]++;aminoAcids[2]++;break;
				case "TCC":codons[9]++;aminoAcids[2]++;break;
				case "TCA":codons[10]++;aminoAcids[2]++;break;
				case "TCG":codons[11]++;aminoAcids[2]++;break;
				case "AGT":codons[12]++;aminoAcids[2]++;break;
				case "AGC":codons[13]++;aminoAcids[2]++;break;
				//Tyr
				case "TAT":codons[14]++;aminoAcids[3]++;break;
				case "TAC":codons[15]++;aminoAcids[3]++;break;
				//Cys
				case "TGT":codons[16]++;aminoAcids[4]++;break;
				case "TGC":codons[17]++;aminoAcids[4]++;break;
				//Pro
				case "CCT":codons[18]++;aminoAcids[5]++;break;
				case "CCC":codons[19]++;aminoAcids[5]++;break;
				case "CCA":codons[20]++;aminoAcids[5]++;break;
				case "CCG":codons[21]++;aminoAcids[5]++;break;
				//His
				case "CAT":codons[22]++;aminoAcids[6]++;break;
				case "CAC":codons[23]++;aminoAcids[6]++;break;
				//Gin
				case "CAA":codons[24]++;aminoAcids[7]++;break;
				case "CAG":codons[25]++;aminoAcids[7]++;break;
				//Arg
				case "CGT":codons[26]++;aminoAcids[8]++;break;
				case "CGC":codons[27]++;aminoAcids[8]++;break;
				case "CGA":codons[28]++;aminoAcids[8]++;break;
				case "CGG":codons[29]++;aminoAcids[8]++;break;
				case "AGA":codons[30]++;aminoAcids[8]++;break;
				case "AGG":codons[31]++;aminoAcids[8]++;break;
				//Ile
				case "ATT":codons[32]++;aminoAcids[9]++;break;
				case "ATC":codons[33]++;aminoAcids[9]++;break;
				case "ATA":codons[34]++;aminoAcids[9]++;break;
				//Thr
				case "ACT":codons[35]++;aminoAcids[10]++;break;
				case "ACC":codons[36]++;aminoAcids[10]++;break;
				case "ACA":codons[37]++;aminoAcids[10]++;break;
				case "ACG":codons[38]++;aminoAcids[10]++;break;
				//Asn
				case "AAT":codons[39]++;aminoAcids[11]++;break;
				case "AAC":codons[40]++;aminoAcids[11]++;break;
				//Lys
				case "AAA":codons[41]++;aminoAcids[12]++;break;
				case "AAG":codons[42]++;aminoAcids[12]++;break;
				//Val
				case "GTT":codons[43]++;aminoAcids[13]++;break;
				case "GTC":codons[44]++;aminoAcids[13]++;break;
				case "GTA":codons[45]++;aminoAcids[13]++;break;
				case "GTG":codons[46]++;aminoAcids[13]++;break;
				//Ala
				case "GCT":codons[47]++;aminoAcids[14]++;break;
				case "GCC":codons[48]++;aminoAcids[14]++;break;
				case "GCA":codons[49]++;aminoAcids[14]++;break;
				case "GCG":codons[50]++;aminoAcids[14]++;break;
				//Asp
				case "GAT":codons[51]++;aminoAcids[15]++;break;
				case "GAC":codons[52]++;aminoAcids[15]++;break;
				//Glu
				case "GAA":codons[53]++;aminoAcids[16]++;break;
				case "GAG":codons[54]++;aminoAcids[16]++;break;
				//Gly
				case "GGT":codons[55]++;aminoAcids[17]++;break;
				case "GGC":codons[56]++;aminoAcids[17]++;break;
				case "GGA":codons[57]++;aminoAcids[17]++;break;
				case "GGG":codons[58]++;aminoAcids[17]++;break;
				//stop
				case "TAA":codons[59]++;aminoAcids[18]++;break;
				case "TAG":codons[60]++;aminoAcids[18]++;break;
				case "TGA":codons[61]++;aminoAcids[18]++;break;
			}
		}
		myStringBuilder.append(classNumber);
		for (int j = 0; j < codons.length; j++) {
			if (j <= 1) {
				if (aminoAcids[0] != 0) {
					codons[j] = codons[j] / aminoAcids[0];
				} else {
					codons[j] = 0;
				}
			} else if (j <= 7) {
				if (aminoAcids[1] != 0) {
					codons[j] = codons[j] / aminoAcids[1];
				} else {
					codons[j] = 0;
				}
			} else if (j <= 13) {
				if (aminoAcids[2] != 0) {

					codons[j] = codons[j] / aminoAcids[2];
				} else {
					codons[j] = 0;
				}
			} else if (j <= 15) {
				if (aminoAcids[3] != 0) {

					codons[j] = codons[j] / aminoAcids[3];
				} else {
					codons[j] = 0;
				}
			} else if (j <= 17) {
				if (aminoAcids[4] != 0) {

					codons[j] = codons[j] / aminoAcids[4];
				} else {
					codons[j] = 0;
				}
			} else if (j <= 21) {
				if (aminoAcids[5] != 0) {

					codons[j] = codons[j] / aminoAcids[5];
				} else {
					codons[j] = 0;
				}
			} else if (j <= 23) {
				if (aminoAcids[6] != 0) {

					codons[j] = codons[j] / aminoAcids[6];
				} else {
					codons[j] = 0;
				}
			} else if (j <= 25) {
				if (aminoAcids[7] != 0) {

					codons[j] = codons[j] / aminoAcids[7];
				} else {
					codons[j] = 0;
				}
			} else if (j <= 31) {
				if (aminoAcids[8] != 0) {

					codons[j] = codons[j] / aminoAcids[8];
				} else {
					codons[j] = 0;
				}
			} else if (j <= 34) {
				if (aminoAcids[9] != 0) {

					codons[j] = codons[j] / aminoAcids[9];
				} else {
					codons[j] = 0;
				}
			} else if (j <= 38) {
				if (aminoAcids[10] != 0) {

					codons[j] = codons[j] / aminoAcids[10];
				} else {
					codons[j] = 0;
				}
			} else if (j <= 40) {
				if (aminoAcids[11] != 0) {

					codons[j] = codons[j] / aminoAcids[11];
				} else {
					codons[j] = 0;
				}
			} else if (j <= 42) {
				if (aminoAcids[12] != 0) {

					codons[j] = codons[j] / aminoAcids[12];
				} else {
					codons[j] = 0;
				}
			} else if (j <= 46) {
				if (aminoAcids[13] != 0) {

					codons[j] = codons[j] / aminoAcids[13];
				} else {
					codons[j] = 0;
				}
			} else if (j <= 50) {
				if (aminoAcids[14] != 0) {

					codons[j] = codons[j] / aminoAcids[14];
				} else {
					codons[j] = 0;
				}
			} else if (j <= 52) {
				if (aminoAcids[15] != 0) {

					codons[j] = codons[j] / aminoAcids[15];
				} else {
					codons[j] = 0;
				}
			} else if (j <= 54) {
				if (aminoAcids[16] != 0) {

					codons[j] = codons[j] / aminoAcids[16];
				} else {
					codons[j] = 0;
				}
			} else if (j <= 58) {
				if (aminoAcids[17] != 0) {

					codons[j] = codons[j] / aminoAcids[17];
				} else {
					codons[j] = 0;
				}
			} else if (j <= 61) {
				if (aminoAcids[18] != 0) {

					codons[j] = codons[j] / aminoAcids[18];
				} else {
					codons[j] = 0;
				}
			}

//			System.out.println(codons[j]);
		
			
			BigDecimal bg = new BigDecimal(codons[j]);
			
			double valueAftrCut = bg.setScale(6, BigDecimal.ROUND_HALF_UP)
					.doubleValue();
			myStringBuilder.append(" " + (j+1) + ":" + valueAftrCut);
		}

		return myStringBuilder.toString();
	}
}