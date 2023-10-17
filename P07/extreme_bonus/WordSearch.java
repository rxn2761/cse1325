import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

import java.util.concurrent.*;
import java.util.TreeSet;
import java.util.SortedSet;


public class WordSearch {
    private static final String usage = "usage: java WordSearch [-h] [-v] [#threads] [#puzzles] [puzzleFile]...";

    public WordSearch(List<String> args) {
    
        // Validate the command line arguments
        if(args.size() > 0 && args.get(0).equals("-h")) {
            System.out.println(usage);
            System.exit(0);
        }
        if(args.size() > 0 && args.get(0).equals("-v")) {
             verbose = true;
             args.remove(0);
        } else {
             verbose = false;
        }
        int threads = 0;
        try {
            // Can't assign NUM_THREADS here because javac worries
            // it may not be assigned a value
            threads = Integer.parseInt(args.get(0));
            args.remove(0);
        } catch(Exception e) {
            System.err.println("Error: Invalid number of threads\n" + usage);
            System.exit(-1);
        }
        NUM_THREADS = threads;
        int numPuzzles = 0;
        try {
            // Can't assign NUM_THREADS here because javac worries
            // it may not be assigned a value
            numPuzzles = Integer.parseInt(args.get(0));
            args.remove(0);
        } catch(Exception e) {
            System.err.println("Error: Invalid number of puzzles\n" + usage);
            System.exit(-1);
        }

        // Load the puzzles!
        for(String puzzleName : args) {
            try(BufferedReader br = new BufferedReader(new FileReader(puzzleName))) {
                puzzles.add(new Puzzle(puzzleName, br));
            } catch(IOException e) {
                System.err.println("Unable to load puzzle " + puzzleName);
            }
        }
        
        // Verify all puzzles loaded successfully
        // No error is printed, as a message should be printed for each failed load above
        if(puzzles.size() != args.size()) System.exit(-3);
        
        // Delete or duplicate puzzles to get the right number
        if(numPuzzles < puzzles.size()) puzzles.subList(numPuzzles, puzzles.size()).clear();
        else if (numPuzzles > puzzles.size()) {
            for(int i=puzzles.size(); i<numPuzzles; ++i)
                puzzles.add(puzzles.get(i%puzzles.size()));
        }
        NUM_PUZZLES = puzzles.size();
        
        // -------- All Puzzles Loaded --------
    }
    
    public void printSolutions() {
        for(Solution s : solutions)
            System.out.println(s);
    }
    public static void main(String[] args) 
    {
        WordSearch ws = new WordSearch(new LinkedList<>(Arrays.asList(args)));
        ExecutorService executor = Executors.newFixedThreadPool(ws.NUM_THREADS);
        try {
            for (int i = 0; i < puzzles.size(); ++i) {
                Puzzle p = puzzles.get(i);
                Solver solver = new Solver(p);
                    Callable<Void> puzzleSolver = new PuzzleSolver(solver,p,ws,solutions);
                    Future<Void> future = executor.submit(puzzleSolver);
                    future.get();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            executor.shutdown();
        }
        if (ws.verbose) ws.printSolutions();
    }

    public final int NUM_THREADS;
    public final int NUM_PUZZLES;
    public final boolean verbose;

    private static List<Puzzle> puzzles = new ArrayList<>();
    private static SortedSet<Solution> solutions = new TreeSet<>();
}

class PuzzleSolver implements Callable<Void> 
{
    private Solver solver;
    private Puzzle p;
    private WordSearch w;
    private SortedSet<Solution> s;
    public PuzzleSolver(Solver solver, Puzzle p, WordSearch w, SortedSet<Solution> s) 
    {
        this.solver = solver;
        this.p = p;
        this.w = w;
        this.s = s;
    }

    @Override
    public Void call() throws Exception 
    {
        solvePuzzle(solver,p,w,s);
        return null;
    }

    public static void solvePuzzle(Solver solver, Puzzle p, WordSearch w, SortedSet<Solution> s) 
    {
    	
        char[][] matrix = new char[p.width()][p.height()];
        for(int x=0; x<p.width(); ++x)
            for(int y=0; y<p.height(); ++y) 
            {
            	char temp = p.getChar(x, y);
                matrix[x][y] = temp; 
            }
        
        String[] words = p.getWords();
        WordPuzzleSearch wp = new WordPuzzleSearch();
        List<String> results = wp.findWords(matrix, words);
    	
    	for (String st : results)
    	{
    		String[] temp = st.split(",");
    		Solution sol = new Solution(p.name(),temp[0],Integer.parseInt(temp[1]),Integer.parseInt(temp[2]),Direction.valueOf(temp[3]));
    		s.add(sol);    		
    	}
    	
    }
}

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
    }
}

class WordPuzzleSearch 
{
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        List<String> result = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                backtrack(board, i, j, root, "", i, j, result);
            }
        }

        return result;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'A';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isEndOfWord = true;
        }
        return root;
    }

    private void backtrack(char[][] board, int i, int j, TrieNode node, String boardWord, int startRow, int startCol, List<String> result) {
        char c = board[i][j];
        if (c == '#' || node.children[c - 'A'] == null) {
            return;
        }

        node = node.children[c - 'A'];
        
        if (node.isEndOfWord) {
            result.add(boardWord + c + "," + startRow + ","  + startCol + "," + this.getDirection(startRow, startCol, i,j)); 
            node.isEndOfWord = false; // To avoid duplicates
        }

        char tmp = board[i][j];
        board[i][j] = '#'; // Mark the cell as visited

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
        
        for (int[] dir : directions) {
            int newRow = i + dir[0];
            int newCol = j + dir[1];

            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
                backtrack(board, newRow, newCol, node, boardWord + c, startRow, startCol, result);
            }
        }

        board[i][j] = tmp; // Restore the cell
    }

    private String getDirection(int x1, int y1, int x2, int y2) 
    {
    	if ((x1 - x2 == 0) && (y1-y2 > 0))

    		return "N";

    	else if ((x1 - x2 == 0) && (y1-y2 < 0))

    		return "S";

    	else if ((x1 - x2 < 0) && (y1-y2 == 0))

    		return "E";

    	else if ((x1 - x2 > 0) && (y1-y2 == 0))

    		return "W";

    	else if ((x1 - x2 < 0) && (y1-y2 < 0))

    		return "SE";

    	else if ((x1 - x2 > 0) && (y1-y2 < 0))

    		return "SW";

    	else if ((x1 - x2 < 0) && (y1-y2 > 0))

    		return "NE";

    	else if ((x1 - x2 > 0) && (y1-y2 > 0))

    		return "NW";

    	return "";
    }
}

