package FinalProject;

class NonSquareMapException extends MapFormatException{
	int rows, columns;
	NonSquareMapException(int rows, int columns){
		super();
		this.rows=rows;
		this.columns=columns;
	}
	public String toString(){
		return "Your map is incorrectly formatted!\nYour map had " + rows + " rows and " + columns + " columns.\nYour map should have the same amount of rows and columns!";
	}
}