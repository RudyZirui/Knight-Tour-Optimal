import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;

public class Greedy {
	private static int N;
	private static boolean isVisited[][]; 
	private static boolean isCompleted;
	private final static int[] xMove = {1,2,2,1,-1,-2,-2,-1};
	private final static int[] yMove = {2,1,-1,-2,-2,-1,1,2};
	public static void main(String[] args) {
		N = 8;
		int[][] path0 = new int[N][N];
		int[][] path = new int[N][N];
		isVisited = new boolean[N][N];
		traversalChessboard(path0, 0, 0, 1);
		isCompleted = false;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				isVisited[i][j] = false;
			}
		}
		long start = System.currentTimeMillis();
		traversalChessboard(path, 0, 0, 1);
		
		long end = System.currentTimeMillis();
		System.out.println((end - start) + "ms");
		for (int[] rows : path) {
			for (int step : rows) {
				System.out.printf("%4d", step);
			}
			System.out.println();
		}
	}

	
	public static void traversalChessboard(int[][] path, int x, int y, int step) {
		path[x][y] = step;
		isVisited[x][y] = true;
		ArrayList<Point> nextPList = nextPList(new Point(x, y));
		sort(nextPList);
		while (!nextPList.isEmpty()) {
			Point p = nextPList.remove(0);// 
			if (!isVisited[p.x][p.y]) {
				traversalChessboard(path, p.x, p.y, step + 1);
			}
		}
		if (step < N * N && !isCompleted) {
			path[x][y] = -1;
			isVisited[x][y] = false;
		} else {
			isCompleted = true;
		}
	}

	public static ArrayList<Point> nextPList(Point currentP){
		ArrayList<Point> pList = new ArrayList<Point>();
		Point nextP = new Point();
		for(int i = 0; i < 8; i++) {
			nextP.x = currentP.x + xMove[i];
			nextP.y = currentP.y + yMove[i];
			if(isOutOfChessboard(nextP.x, nextP.y)){
				pList.add(new Point(nextP));
			}
		}
		
		return pList;
	}

	
	public static boolean isOutOfChessboard(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N) {
			return false;
		}
		else {
			return true;
		}
	}
	public static void sort(ArrayList<Point> ps) {
		ps.sort(new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				int count1 = nextPList(o1).size();
				int count2 = nextPList(o2).size();
				if (count1 < count2) {
					return -1;
				} else if (count1 == count2) {
					return 0;
				} else {
					return 1;
				}
			}
		});
	}
}