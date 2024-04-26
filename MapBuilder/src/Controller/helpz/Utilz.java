package controller.helpz;

import java.util.ArrayList;

public class Utilz {

	public static int[][] ArrayListTo2Dint(ArrayList<Integer> list, int ySize, int xSize) {
	    int[][] newArr = new int[ySize][xSize];

	    int index = 0;

	    for (int y = 0; y < ySize; y++) {
	        for (int x = 0; x < xSize; x++) {
	            if (index < list.size()) {
	                newArr[y][x] = list.get(index);
	                index++;
	            } else {
	                break;
	            }
	        }
	    }

	    return newArr;
	}



	public static int[] TwoDto1DintArr(int[][] twoArr) {
		int[] oneArr = new int[twoArr.length * twoArr[0].length];

		for (int j = 0; j < twoArr.length; j++)
			for (int i = 0; i < twoArr[j].length; i++) {
				int index = j * twoArr.length + i;
				oneArr[index] = twoArr[j][i];
			}

		return oneArr;
	}


}
