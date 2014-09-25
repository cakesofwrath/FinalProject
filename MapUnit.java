package FinalProject;

public class MapUnit{

	/*print/standard values:
	print:
	i dunno=character-get from player, maybe?
	0=open
	1=taken
	?=open, but mixes up map
	status:
	-1=mixUp
	0=open
	1=taken
	2=player
	*/

	//Should I change this later so that each MapUnit has its own info on standard representations? 
	private char print, withPlayerPrint;//Change to enums later?
	private byte status, withPlayerStatus;//2nd byte is if the player is occupying the space
	//No need for a constructor accepting char and byte argument...
	//the status is read from the print char
	MapUnit(char print){//overloaded constructor for the custom maps...converts print into default status string for the associated character
		this.print = print;
	}
	// MapUnit(){
	// 	this.print='0';
	// }
	public byte getStatus(){
		return status;
	}
	public char getRep(){//Get representations, only here b/c of a map throw exc clause
		return print;
	}

	public void flip(){ //fix later w/ loops and update
		if(status==0)
			status=1;
		else if(status==1)
			status=0;
	}
	public void preserve(){
		//this.print = withPlayerPrint;
		this.status = withPlayerStatus;
	}
	public void update(byte status){ 
		if(status == 2){
			//withPlayerPrint = this.print;
			withPlayerStatus = this.status;
		}
		this.status = status;
	}
	public String toString(){
		return Character.toString(this.print);
	}
}