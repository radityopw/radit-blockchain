package com.radityopw.blockchain;

import java.util.ArrayList;
import java.util.Date;

public class Blockchain
{
	private int prefix;
	private ArrayList<Block> listData;
	private String prefixString;
	private String lastHash;
	
	public Blockchain()
	{
		prefix = 4;
		prefixString = new String(new char[prefix]).replace('\0', '0');
		listData = new ArrayList<Block>();
	}
	
	public void addBlock(String data)
	{
		Block b = new Block(data,lastHash,new Date().getTime());
		b.mineBlock(prefix);
		if(b.getHash().substring(0,prefix).equals(prefixString))
		{
			listData.add(b);
			lastHash = b.getHash();
		}
	}
	
	public boolean validateFast()
	{
		if(listData.size() > 0)
		{
			Block b = listData.get(listData.size() - 1);
			
			if(!b.getHash().equals(lastHash))
			{
				b = null;
				return false;
			}
		}
		
		return true;
	}
}