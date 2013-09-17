package com.elestar.guidobeta;

public class Option {
	/*private Boolean desa;
	private Boolean school;
	private Boolean hall;
	private Boolean troom;
	private Boolean bus;
	private Boolean rroute;
	private Boolean broute;
	private Boolean yroute;
	private Boolean proute;
	private Boolean block;*/
	private Boolean[] options;
	private final int OPTION_NUMBERS;
	
	Option(){
		OPTION_NUMBERS=10;
		options = new Boolean[OPTION_NUMBERS];
	}
	public Boolean[] getOptions(){
		return options;
	}
	public int getMax(){
		return OPTION_NUMBERS;
	}
	public void setOptions(Boolean[] op){
		for (int i = 0; i < OPTION_NUMBERS; i++)
			options[i]=op[i];
	}
	

}
