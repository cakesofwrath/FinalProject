package FinalProject;

class UnknownSymbolInMapException extends MapFormatException{
	char wrongChar;
	UnknownSymbolInMapException(char wrongChar){
		super();
		this.wrongChar = wrongChar;
	}
	public String toString(){
		return "Your map is incorrectly formatted!\nYour map had a " + wrongChar + ", wasn't specified as one of your map values!";
	}
}