package com.jnn.bots;

import java.util.ArrayList;


public class PlayerCode {

	public ArrayList<String> code = new ArrayList<String>();
	public String fileName;
	public int editPoint;
	public int editMode=0;
	
	public PlayerCode() {

	}
	public PlayerCode(String fName) {

		String content = ANR.getFileContentsAsString(fName);
		content.replace("\r","\n");
		content.replace("\n\n","\n");
		code=tokenizeString(content,"\n");
		fileName=fName;
	}
	
	public void save(String fileName){
		
		String content="";
		for (int j=0; j< code.size();j++){
			content += code.get(j)+"\n";
		}
		
		 ANR.saveXMLFile(content,fileName);
		
	}
public static String getCommand(String code){
	String cCommand=null;
	if (code.indexOf(' ',0) >= 0)
	{
		cCommand=code.substring(0,code.indexOf(' ',0));
	}
	else
		cCommand=code;
	
	return cCommand;
	
}
	
public static ArrayList<String> tokenizeString_OLD(String iString){
		
		ArrayList<String> rList = new ArrayList<String>();
		
		int cPos=iString.indexOf(' ',0)+1;
		int newPos;
		while (cPos > 0)
		{
			newPos= iString.indexOf(' ', cPos);
			if (newPos > 0)
				{rList.add(iString.substring(cPos,newPos));
				 cPos=newPos+1;
				}
			else
				{rList.add(iString.substring(cPos,iString.length()));
				cPos=-1;
				}
		}
		
		return rList;
	}
	
public static ArrayList<String> tokenizeString(String iString, String token){
	
	ArrayList<String> rList = new ArrayList<String>();
	
	int cPos=iString.indexOf(token,0)+1;
	int newPos;
	
	if (cPos>0)
		rList.add(iString.substring(0,cPos-1));
	
	while (cPos > 0)
	{
		newPos= iString.indexOf(token, cPos);
		if (newPos > 0)
			{rList.add(iString.substring(cPos,newPos));
			 cPos=newPos+1;
			}
		else
			{rList.add(iString.substring(cPos,iString.length()));
			cPos=-1;
			}
	}
	
	return rList;
}
}
