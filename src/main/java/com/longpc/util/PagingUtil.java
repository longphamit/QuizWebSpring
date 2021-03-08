package com.longpc.util;

public class PagingUtil {
	private int currentPage;
	private int minPage;
	private int maxPage;
	public static final int SIZE = 5;

	public PagingUtil() {
		currentPage = 1;
		minPage = 0;
		maxPage = 0;
	}

	public int calculatorMinMax(int sizeList) {
		if (sizeList <= SIZE) {
			minPage = 1;
			maxPage = 1;
			return 1;
		} else {
			if (currentPage * SIZE - SIZE > sizeList) {
				return 0;
			}
			if (currentPage == 1) {
				minPage = 1;
				maxPage = 3;
				int numPage = (int) Math.ceil((double) sizeList / (double) SIZE);
				if (maxPage > numPage) {
					maxPage = numPage;
				}
				return numPage;
			} else {
				if (sizeList < SIZE) {
					minPage = 1;
					maxPage = 1;
					return 1;
				}
				if (sizeList > SIZE) {
					minPage = currentPage - 1;
					maxPage = currentPage + 1;
					int numPage = (int) Math.ceil((double) sizeList / (double) SIZE);
					if (maxPage > numPage) {
						maxPage = numPage;
						if (minPage - 1 == 0) {
							minPage = 1;
						} else {
							minPage -= 1;
						}

					}
					return numPage;
				}
			}
		}
		return -1;
	}

	public int getMaxPage() {
		System.out.println(maxPage);
		return maxPage;
	}

	public int getMinPage() {
		System.out.println(minPage);
		return minPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
