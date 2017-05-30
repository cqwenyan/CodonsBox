package tool;

import java.util.Random;

public class EasyRandomizeDiArray {

	public String[] theShuffle(String[] twoDiArray, int RandomTimes) {

		int counter = 0;
		Random random = new Random();
		while (counter <= RandomTimes) {
			for (int j = 1; j < twoDiArray.length - 1; j++) {
				counter++;
				exchange(random.nextInt(twoDiArray.length - 2) + 1, j,
						twoDiArray);
				if (RandomTimes == counter) {
					break;
				}
			}
		}
		return twoDiArray;
	}

	private void exchange(int p1, int p2, String[] twoDiArray) {
		String temp = twoDiArray[p1];
		twoDiArray[p1] = twoDiArray[p2];
		twoDiArray[p2] = temp;
	}
}
