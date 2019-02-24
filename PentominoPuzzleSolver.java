//PentominoPuzzleSolver.java
//Liz Demin

package project2;

import java.util.*;

public class PentominoPuzzleSolver {
	
	//board to fill
	static int[][] board;
	
	static int boardYdim;
	static int boardXdim;
	
	//dimensions of pieces
	static int pieceYdim = 5;
	static int pieceXdim = 5;

	//list of solutions
	static ArrayList<int[][]> solutions = new ArrayList<int[][]>();
	
	//map of piece number to its permutations
	static HashMap<Integer,ArrayList<int[][]>> pieces = new HashMap<Integer,ArrayList<int[][]>>();
	
	//depth of recursion
	static int depth = 0;
	
	//number of recursive calls
	static int numCalls = 0;
	
	public static void main(String[] args) {
		System.out.println("Welcome to Pentomino Puzzle Solver!");
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter board's y dimension: ");
		boardYdim = scan.nextInt();
		System.out.print("Enter board's x dimension: ");
		boardXdim = scan.nextInt();
		
		board = new int[boardYdim][boardXdim];
		
		int[] usablePieces = setup();
		
		long startTime = System.nanoTime();
		//start solving
		solve(board, usablePieces, depth);
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		
		for(int[][] sol : solutions) {
			print2DArray(sol);
		}
		System.out.println(solutions.size()+" solution(s) were found in "+totalTime/1000000+" ms.");
		System.out.println("There were "+numCalls+" recursive calls made.");
	}
	
	//adds all pieces and their permutations
	//each permutation is stored as a 2D array
	//permutations are stored in an ArrayList of 2D arrays
	//each ArrayList is mapped using the piece number
	//returns an int[] of numbers representing the pieces
	public static int[] setup() {
		//---------------------PIECE 1---------------------//
		int[][] piece1a = {{0,1,1,0,0},
				           {1,1,0,0,0},
				           {0,1,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece1b = {{0,1,0,0,0},
				           {1,1,1,0,0},
				           {0,0,1,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece1c = {{0,1,0,0,0},
		           		   {0,1,1,0,0},
				           {1,1,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece1d = {{1,0,0,0,0},
		        		   {1,1,1,0,0},
				           {0,1,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece1e = {{1,1,0,0,0},
				           {0,1,1,0,0},
				           {0,1,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece1f = {{0,0,1,0,0},
				           {1,1,1,0,0},
				           {0,1,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece1g = {{0,1,0,0,0},
		        		   {1,1,0,0,0},
				           {0,1,1,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece1h = {{0,1,0,0,0},
		     		       {1,1,1,0,0},
				           {1,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
	
		ArrayList<int[][]> piece1 = new ArrayList<int[][]>();
		
		piece1.add(piece1a);
		piece1.add(piece1b);
		piece1.add(piece1c);
		piece1.add(piece1d);
		piece1.add(piece1e);
		piece1.add(piece1f);
		piece1.add(piece1g);
		piece1.add(piece1h);
		
		//---------------------PIECE 2---------------------//
		
		int[][] piece2a = {{2,0,0,0,0},
				           {2,0,0,0,0},
				           {2,0,0,0,0},
				           {2,2,0,0,0},
				           {0,0,0,0,0}};

		int[][] piece2b = {{2,2,2,2,0},
				           {2,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece2c = {{2,2,0,0,0},
		        		   {0,2,0,0,0},
				           {0,2,0,0,0},
				           {0,2,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece2d = {{0,0,0,2,0},
		     		   	   {2,2,2,2,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece2e = {{0,2,0,0,0},
				           {0,2,0,0,0},
				           {0,2,0,0,0},
				           {2,2,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece2f = {{2,0,0,0,0},
				           {2,2,2,2,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece2g = {{2,2,0,0,0},
		     		       {2,0,0,0,0},
				           {2,0,0,0,0},
				           {2,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece2h = {{2,2,2,2,0},
		  		           {0,0,0,2,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		ArrayList<int[][]> piece2 = new ArrayList<int[][]>();
		
		piece2.add(piece2a);
		piece2.add(piece2b);
		piece2.add(piece2c);
		piece2.add(piece2d);
		piece2.add(piece2e);
		piece2.add(piece2f);
		piece2.add(piece2g);
		piece2.add(piece2h);
		
		//---------------------PIECE 3---------------------//
		
		int[][] piece3a = {{0,3,0,0,0},
				           {0,3,0,0,0},
				           {3,3,0,0,0},
				           {3,0,0,0,0},
				           {0,0,0,0,0}};

		int[][] piece3b = {{3,3,0,0,0},
				           {0,3,3,3,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece3c = {{0,3,0,0,0},
		        		   {3,3,0,0,0},
				           {3,0,0,0,0},
				           {3,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece3d = {{3,3,3,0,0},
		     		   	   {0,0,3,3,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece3e = {{3,0,0,0,0},
				           {3,0,0,0,0},
				           {3,3,0,0,0},
				           {0,3,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece3f = {{0,3,3,3,0},
				           {3,3,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece3g = {{3,0,0,0,0},
		     		       {3,3,0,0,0},
				           {0,3,0,0,0},
				           {0,3,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece3h = {{0,0,3,3,0},
		  		           {3,3,3,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		ArrayList<int[][]> piece3 = new ArrayList<int[][]>();
		
		piece3.add(piece3a);
		piece3.add(piece3b);
		piece3.add(piece3c);
		piece3.add(piece3d);
		piece3.add(piece3e);
		piece3.add(piece3f);
		piece3.add(piece3g);
		piece3.add(piece3h);

		//---------------------PIECE 4---------------------//
		
		int[][] piece4a = {{4,4,0,0,0},
				           {4,4,0,0,0},
				           {4,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};

		int[][] piece4b = {{4,4,4,0,0},
				           {0,4,4,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece4c = {{0,4,0,0,0},
		        		   {4,4,0,0,0},
				           {4,4,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece4d = {{4,4,0,0,0},
		     		   	   {4,4,4,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece4e = {{4,4,0,0,0},
				           {4,4,0,0,0},
				           {0,4,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece4f = {{0,4,4,0,0},
				           {4,4,4,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece4g = {{4,0,0,0,0},
		     		       {4,4,0,0,0},
				           {4,4,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece4h = {{4,4,4,0,0},
		  		           {4,4,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		ArrayList<int[][]> piece4 = new ArrayList<int[][]>();
		
		piece4.add(piece4a);
		piece4.add(piece4b);
		piece4.add(piece4c);
		piece4.add(piece4d);
		piece4.add(piece4e);
		piece4.add(piece4f);
		piece4.add(piece4g);
		piece4.add(piece4h);

		//---------------------PIECE 5---------------------//
		
		int[][] piece5a = {{0,5,0,0,0},
				           {5,5,0,0,0},
				           {0,5,0,0,0},
				           {0,5,0,0,0},
				           {0,0,0,0,0}};

		int[][] piece5b = {{0,0,5,0,0},
				           {5,5,5,5,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece5c = {{5,0,0,0,0},
		        		   {5,0,0,0,0},
				           {5,5,0,0,0},
				           {5,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece5d = {{5,5,5,5,0},
		     		   	   {0,5,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece5e = {{5,0,0,0,0},
				           {5,5,0,0,0},
				           {5,0,0,0,0},
				           {5,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece5f = {{5,5,5,5,0},
				           {0,0,5,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece5g = {{0,5,0,0,0},
		     		       {0,5,0,0,0},
				           {5,5,0,0,0},
				           {0,5,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece5h = {{0,5,0,0,0},
		  		           {5,5,5,5,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		ArrayList<int[][]> piece5 = new ArrayList<int[][]>();
		
		piece5.add(piece5a);
		piece5.add(piece5b);
		piece5.add(piece5c);
		piece5.add(piece5d);
		piece5.add(piece5e);
		piece5.add(piece5f);
		piece5.add(piece5g);
		piece5.add(piece5h);
		
		//---------------------PIECE 6---------------------//
		
		int[][] piece6a = {{6,6,6,0,0},
				           {0,6,0,0,0},
				           {0,6,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};

		int[][] piece6b = {{0,0,6,0,0},
				           {6,6,6,0,0},
				           {0,0,6,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece6c = {{0,6,0,0,0},
		        		   {0,6,0,0,0},
				           {6,6,6,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece6d = {{6,0,0,0,0},
		     		   	   {6,6,6,0,0},
				           {6,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		ArrayList<int[][]> piece6 = new ArrayList<int[][]>();
		
		piece6.add(piece6a);
		piece6.add(piece6b);
		piece6.add(piece6c);
		piece6.add(piece6d);
		
		//---------------------PIECE 7---------------------//
		
		int[][] piece7a = {{7,0,7,0,0},
				           {7,7,7,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};

		int[][] piece7b = {{7,7,0,0,0},
				           {7,0,0,0,0},
				           {7,7,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece7c = {{7,7,7,0,0},
		        		   {7,0,7,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece7d = {{7,7,0,0,0},
		     		   	   {0,7,0,0,0},
				           {7,7,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		ArrayList<int[][]> piece7 = new ArrayList<int[][]>();
		
		piece7.add(piece7a);
		piece7.add(piece7b);
		piece7.add(piece7c);
		piece7.add(piece7d);
		
		//---------------------PIECE 8---------------------//
		
		int[][] piece8a = {{8,0,0,0,0},
				           {8,0,0,0,0},
				           {8,8,8,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};

		int[][] piece8b = {{8,8,8,0,0},
				           {8,0,0,0,0},
				           {8,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece8c = {{8,8,8,0,0},
		        		   {0,0,8,0,0},
				           {0,0,8,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece8d = {{0,0,8,0,0},
		     		   	   {0,0,8,0,0},
				           {8,8,8,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		ArrayList<int[][]> piece8 = new ArrayList<int[][]>();
		
		piece8.add(piece8a);
		piece8.add(piece8b);
		piece8.add(piece8c);
		piece8.add(piece8d);
		
		//---------------------PIECE 9---------------------//
		
		int[][] piece9a = {{9,0,0,0,0},
				           {9,9,0,0,0},
				           {0,9,9,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};

		int[][] piece9b = {{0,9,9,0,0},
				           {9,9,0,0,0},
				           {9,0,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece9c = {{9,9,0,0,0},
		        		   {0,9,9,0,0},
				           {0,0,9,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		int[][] piece9d = {{0,0,9,0,0},
		     		   	   {0,9,9,0,0},
				           {9,9,0,0,0},
				           {0,0,0,0,0},
				           {0,0,0,0,0}};
		
		ArrayList<int[][]> piece9 = new ArrayList<int[][]>();
		
		piece9.add(piece9a);
		piece9.add(piece9b);
		piece9.add(piece9c);
		piece9.add(piece9d);
		
		//---------------------PIECE 10---------------------//
		
		int[][] piece10a = {{10,10,0,0,0},
				            {0,10,0,0,0},
				            {0,10,10,0,0},
				            {0,0,0,0,0},
				            {0,0,0,0,0}};

		int[][] piece10b = {{0,0,10,0,0},
				            {10,10,10,0,0},
				            {10,0,0,0,0},
				            {0,0,0,0,0},
				            {0,0,0,0,0}};
		
		int[][] piece10c = {{0,10,10,0,0},
		        		    {0,10,0,0,0},
				            {10,10,0,0,0},
				            {0,0,0,0,0},
				            {0,0,0,0,0}};
		
		int[][] piece10d = {{10,0,0,0,0},
		     		   	    {10,10,10,0,0},
				            {0,0,10,0,0},
				            {0,0,0,0,0},
				            {0,0,0,0,0}};
		
		ArrayList<int[][]> piece10 = new ArrayList<int[][]>();
		
		piece10.add(piece10a);
		piece10.add(piece10b);
		piece10.add(piece10c);
		piece10.add(piece10d);
		
		//---------------------PIECE 11---------------------//
		
		int[][] piece11a = {{11,0,0,0,0},
				            {11,0,0,0,0},
				            {11,0,0,0,0},
				            {11,0,0,0,0},
				            {11,0,0,0,0}};

		int[][] piece11b = {{11,11,11,11,11},
				            {0,0,0,0,0},
				            {0,0,0,0,0},
				            {0,0,0,0,0},
				            {0,0,0,0,0}};
		
		ArrayList<int[][]> piece11 = new ArrayList<int[][]>();
		
		piece11.add(piece11a);
		piece11.add(piece11b);
		
		//---------------------PIECE 12---------------------//
		
		int[][] piece12a = {{0,12,0,0,0},
				            {12,12,12,0,0},
				            {0,12,0,0,0},
				            {0,0,0,0,0},
				            {0,0,0,0,0}};
		
		ArrayList<int[][]> piece12 = new ArrayList<int[][]>();
		
		piece12.add(piece12a);
		
		//--------------------------------------------------//
		
		//add all pieces into the map
		pieces.put(1, piece1);
		pieces.put(2, piece2);
		pieces.put(3, piece3);
		pieces.put(4, piece4);
		pieces.put(5, piece5);
		pieces.put(6, piece6);
		pieces.put(7, piece7);
		pieces.put(8, piece8);
		pieces.put(9, piece9);
		pieces.put(10, piece10);
		pieces.put(11, piece11);
		pieces.put(12, piece12);
		
		//add piece numbers to the list of pieces to use
		int[] pieceKeyList = new int[pieces.size()];
		for(int index = 0; index < pieces.size(); index++) {
			pieceKeyList[index] = index+1;
		}
		
		return pieceKeyList;
	}
	
	//recursive function
	public static void solve(int[][] board, int[] usablePieces, int depth) {
		numCalls++;
		if(solutions.size() == 1) {
			return;
		}
		
		//for every piece in pieces
		for(int index = 0; index < usablePieces.length; index++) {
			ArrayList<int[][]> permutations = pieces.get(usablePieces[index]);
			
			//for each permutation for the given piece
			for(int perm = 0; perm < permutations.size(); perm++) {
				
				//for each point on the board
				for(int boardY = 0; boardY < boardYdim; boardY++) {
					for(int boardX = 0; boardX < boardXdim; boardX++) {
						
						//create a copy of the board -- to edit 
						int[][] newBoard = new int[board.length][board[0].length];
						for(int y = 0; y < board.length; y++) {
							for(int x = 0; x < board[0].length; x++) {
								newBoard[y][x] = board[y][x];
							}
						}
						
						//try to place piece
						boolean returnValue = placePiece(boardY, boardX, usablePieces[index], 
								    					 permutations.get(perm), newBoard);
						
						//if piece has been placed
						if(returnValue) {	
							int[] newPieces = new int[usablePieces.length-1];
							int indexCounter = 0;
							
							//create a new int[] containing all pieces other than the one that was just placed
							for(int i = 0; i < usablePieces.length; i++) {
								if(usablePieces[i] != usablePieces[index]) {
									newPieces[indexCounter] = usablePieces[i];
									indexCounter++;
								}
							}
							
							//if newPieces is empty -- no pieces remain
							if(newPieces.length == 0) {
								
								//always add the first solution
								if(solutions.isEmpty()) {
									solutions.add(newBoard);
								}
								
								//check if solution already exists
								else if(doesSolutionExist(newBoard) == false) {
									solutions.add(newBoard);
							    }
							}
							//recursion
							else {
								solve(newBoard, newPieces, depth+1);
							}
						}
					}
				}
			}
		}
	}
	
	//tries to place a piece on the board
	//if placed successfully, returns true
	//if piece cannot be placed, returns false
	public static boolean placePiece(int boardY, int boardX, int currentPiece, 
			                         int[][] currentPerm, int[][] currentBoard) {
		
		//for each point in the piece
		for(int pieceY = 0; pieceY < pieceYdim; pieceY++) {
			for(int pieceX = 0; pieceX < pieceXdim; pieceX++) {
				
				//if the piece has a filled square
				if(currentPerm[pieceY][pieceX] != 0) {
							
					int y = boardY+pieceY;
					//check y boundary
					if(y >= boardYdim) {
						return false;
					}
					
					int x = boardX+pieceX;
					//check x boundary
					if(x >= boardXdim) {
						return false;
					}
					
					//check if board has empty spot
					if(currentBoard[y][x] != 0) {
						return false;
					}
					
					//fill the square
					currentBoard[y][x] = currentPiece;
				}
			}
		}
		return true;
	}
	
	//checks if given solution already exists in solutions
	//to avoid adding double solutions -- since algorithm is exhaustive
	public static boolean doesSolutionExist(int[][] sol) {
		boolean match;
		
		//for every existing solution in solutions
		for(int index = 0; index < solutions.size(); index++) {
			match = true;
			
			//for each square on the board
			for(int y = 0; y < 2; y++) {
				for(int x = 0; x < 2; x++) {
					//if two squares don't match, then the solutions are different
					if((solutions.get(index))[y][x] != sol[y][x]) {
						match = false;
					}
				}
			}
			if(match == true) {
				return true;
			}
		}
		return false;
	}
	
	//prints a 2D array with some basic formatting
	public static void print2DArray(int[][] myArray) {
		int yLength = myArray.length;
		int xLength = myArray[0].length;
		for(int top = 0; top < xLength*3+2; top++) {
			System.out.print("-");
		}
		System.out.println();
		for(int row = 0; row < yLength; row++) {
			System.out.print("|"); 
			for(int col = 0; col < xLength; col++) {
				if(myArray[row][col] < 10) {
					System.out.print(" "+myArray[row][col]+" ");
				}
				else System.out.print(" "+myArray[row][col]);
			}
			System.out.println("|");
		}
		for(int top = 0; top < xLength*3+2; top++) {
			System.out.print("-");
		}
		System.out.println();
	}
}