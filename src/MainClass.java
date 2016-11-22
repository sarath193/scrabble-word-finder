import java.util.Random;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter grid size by rows, columns.. ");
		System.out.print("Rows: ");
		int R = sc.nextInt();
		System.out.print("Columns: ");
		int C = sc.nextInt();
		sc.close();
		char[][] grid = new char[R][C];

		Random rand = new Random();
		System.out.println("\nGenerated grid with random characters: \n");
		
		/************** Random character grid generation ****************/
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				grid[i][j] = (char) (97 + rand.nextInt(26));
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();

		WordPuzzle wp = new WordPuzzle();

		long start = 0, stop = 0;
		
		start = 0;
		stop = 0;

		start = System.currentTimeMillis();
		System.out.println("\nSearching in tree... ");
		wp.process(grid, 1);     // 1 is for AVL Tree
		stop = System.currentTimeMillis();
		System.out.println(
				wp.tWord + " valid words found in the matrix in " + (stop - start) + " milliseconds using AVL Tree");

		start = 0;
		stop = 0;

		start = System.currentTimeMillis();
		System.out.println("\nSearching in HashTable... ");
		wp.process(grid, 2);	// 2 is for HashTable
		stop = System.currentTimeMillis();
		System.out.println(
				wp.hWord + " valid words found in the matrix in " + (stop - start) + " milliseconds using HashTable\n");
		
		System.out.println("################ Hash Table Stats ###############\n");
		wp.hashStats();
		System.out.println("\n#################################################");
		
		
		start = System.currentTimeMillis();
		System.out.println("\nSearching in LinkedList... ");
		wp.process(grid, 0);     //  0 is for linkedlist
		stop = System.currentTimeMillis();
		System.out.println(
				wp.lWord + " valid words found in the matrix in " + (stop - start) + " milliseconds using LinkedList");

	}

}
