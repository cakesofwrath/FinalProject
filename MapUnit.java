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

	//Should I change this later os that each MapUnit has its own info on standard representations? 

	private char print;//Change to enums later?
	private byte status, prevStatus;//2nd byte is if the player is occupying the space
	//No need for a constructor accepting char and byte argument...
	//the status is read from the print char
	MapUnit(char print){//overloaded constructor for the custom maps...converts print into default status string for the associated character
		this.print=print;
	}
	// MapUnit(){
	// 	this.print='0';
	// }
	public byte getStatus(){
		return status;
	}
	public byte getPrevStatus(){
		return prevStatus;
	}
	public char getRep(){//Get representations
		return print;
	}
	public void flip(Byte status){ //fix later w/ loops and update
		if(status==0)
			status=1;
		else if(status==1)
			status=0;
	}
	public void update(byte status, char print){ 
		if(status == 2)
			prevStatus = this.status;
		this.status = status;
		this.print = print; 
	}
	public void update(char print){//if the player is already there
		this.status = prevStatus;
		this.print = print;
		
	}
	public String toString(){
		return Character.toString(this.print);
	}
}