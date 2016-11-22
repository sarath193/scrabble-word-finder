import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class WordPuzzle {
	BufferedReader br;
	final InputStream is;
	private int maxWordLength = 0;

	AvlTree<String> tree;
	LinkedList<String> linkedlist;
	MyHashTable<String> mHash;

	public WordPuzzle() {

		tree = new AvlTree<>();
		linkedlist = new LinkedList<>();
		mHash = new MyHashTable<>();
		File file;

		/****** Dictionary is copied into src folder ******/

		is = ClassLoader.getSystemResourceAsStream("dictionary.txt");
		System.out.println("Loading words into HashTable, AvlTree, LinkedList... ");

		/*
		 * Loading dictionary words into LinkedList, AVLTree, HashTable
		 */
		try {
			String s = "";
			// to load dictionary file into Word puzzle project uncomment the
			// following section
			/*
			 * file = new File("path_to_dictionary_file"); br = new
			 * BufferedReader(new FileReader(file));
			 */

			br = new BufferedReader(new InputStreamReader(is));
			while ((s = br.readLine()) != null) {
				if (maxWordLength < s.length())
					maxWordLength = s.length();
				tree.insert(s);
				linkedlist.add(s);
				mHash.add(s);
			}
			System.out.println("\nLoading finished successfully.");
		} catch (IOException e) {
			System.out.println("File doesn't exists");
		}
	}

	/*
	 * creates words from the grid in all possible combinations and pass the
	 * formed words into checkWord method
	 */
	public void process(char[][] grid, int code) {
		int n = grid.length;
		int n1 = grid[0].length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n1; j++) {
				for (int k = 1; k <= 8; k++) {
					switch (k) {
					case 1: {
						// towards right
						String temp = "";
						for (int l = j; l < n1; l++) {
							temp += grid[i][l];
							if (temp.length() > maxWordLength)    // no need of checking words 
								break;							  // longer than longest dictionary word
							checkWord(temp, code);
						}
					}
						break;

					case 2: {
						// towards left
						String temp = "";
						for (int l = j; l >= 0; l--) {
							temp += grid[i][l];
							if (temp.length() == 1)
								continue;
							if (temp.length() > maxWordLength)
								break;
							checkWord(temp, code);
						}
					}
						break;

					case 3: {
						// towards up
						String temp = "";
						for (int l = i; l >= 0; l--) {
							temp += grid[l][j];
							if (temp.length() == 1)
								continue;
							if (temp.length() > maxWordLength)
								break;
							checkWord(temp, code);
						}
					}
						break;

					case 4: {
						// towards down
						String temp = "";
						for (int l = i; l < n; l++) {
							temp += grid[l][j];
							if (temp.length() == 1)
								continue;
							if (temp.length() > maxWordLength)
								break;
							checkWord(temp, code);
						}
					}
						break;

					case 5: {
						// towards top left
						String temp = "";
						for (int x = i, y = j; x >= 0 && y >= 0; x--, y--) {
							temp += grid[x][y];
							if (temp.length() == 1)
								continue;
							if (temp.length() > maxWordLength)
								break;
							checkWord(temp, code);
						}
					}
						break;

					case 6: {
						// towards top right
						String temp = "";
						for (int x = i, y = j; x >= 0 && y < n1; x--, y++) {
							temp += grid[x][y];
							if (temp.length() == 1)
								continue;
							if (temp.length() > maxWordLength)
								break;
							checkWord(temp, code);
						}
					}
						break;
					case 7: {
						// towards bottom right
						String temp = "";
						for (int x = i, y = j; x < n && y < n1; x++, y++) {
							temp += grid[x][y];
							if (temp.length() == 1)
								continue;
							if (temp.length() > maxWordLength)
								break;
							checkWord(temp, code);
						}
					}
						break;
					case 8: {
						// towards bottom left
						String temp = "";
						for (int x = i, y = j; x < n && y >= 0; x++, y--) {
							temp += grid[x][y];
							if (temp.length() == 1)
								continue;
							if (temp.length() > maxWordLength)
								break;
							checkWord(temp, code);
						}
					}
						break;

					}
				}
			}
		}
	}

	/*
	 * no .of words found by linkedlist, tree, hashtable
	 */
	int lWord = 0;
	int tWord = 0;
	int hWord = 0;
	int Hword = 0;
	int newWord = 0;
	public long masterCount = 0;

	/*
	 * checkWord method checks if the word is present in the hashTable or Linked
	 * List or Tree based on the code value.
	 */
	public void checkWord(String word, int code) {
		masterCount++;
		switch (code) {
		case 0:
			if (linkedlist.contains(word))
				lWord++;
			break;
		case 1:
			if (tree.contains(word))
				tWord++;
			break;
		case 2:
			if (mHash.contains(word))
				hWord++;
			break;
		}

	}

	public void hashStats() {
		System.out.println("# Total no. of buckets : " + mHash.getBucketSize() + "\t\t\t#");
		System.out.println("# No. of filled buckets: " + mHash.getFilledBuckets() + "\t\t\t#");
		System.out.println("# Total no. of words in the table : " + mHash.getTotalWords() + "\t#");
		System.out.println("# Max Chain Length in a bucket : " + mHash.getMaxLength() + "\t\t#");
	}
}
