import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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
	
    public static void main(String[] args) throws InterruptedException {
        WordSearch ws = new WordSearch(new LinkedList<>(Arrays.asList(args)));
        ExecutorService executor = Executors.newFixedThreadPool(ws.NUM_THREADS);
        try {
            for (int i = 0; i < puzzles.size(); ++i) {
                Puzzle p = puzzles.get(i);
                Solver solver = new Solver(p);
                    Callable<Void> puzzleSolver = new PuzzleSolver(ws.NUM_THREADS,solver,p,ws,solutions);
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

class PuzzleSolver implements Callable<Void> {
    private Solver solver;
    private Puzzle p;
    private WordSearch w;
    private SortedSet<Solution> s;
    public PuzzleSolver(int numberOfThreads, Solver solver, Puzzle p, WordSearch w, SortedSet<Solution> s) {
        this.solver = solver;
        this.p = p;
        this.w = w;
        this.s = s;
    }

    @Override
    public Void call() throws Exception {
        solvePuzzle(solver,p,w,s);
        return null;
    }

    public static void solvePuzzle(Solver solver, Puzzle p, WordSearch w, SortedSet<Solution> s) {
        for (String word : p.getWords())
        {
            Solution sol = solver.solve(word);
            if (sol == null)
                System.err.println("#### Failed to solve " + p.name() + " for '" + word + "'");
            else
                s.add(sol);
        }
    }
}