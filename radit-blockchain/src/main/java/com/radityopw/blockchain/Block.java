package com.radityopw.blockchain;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;

public class Block 
{
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;
	private UUID uuid;
 
    public Block(String data, String previousHash, long timeStamp) 
	{
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = timeStamp;
        this.hash = calculateBlockHash();
		this.uuid = UUID.randomUUID();
    }
	
	protected String calculateBlockHash() 
	{
		String dataToHash = previousHash 
		  + Long.toString(timeStamp) 
		  + Integer.toString(nonce) 
		  + data;
		MessageDigest digest = null;
		byte[] bytes = null;
		StringBuffer buffer = new StringBuffer();
		try {
			digest = MessageDigest.getInstance("SHA-256");
			bytes = digest.digest(dataToHash.getBytes("UTF-8"));
			for (byte b : bytes) 
			{
				buffer.append(String.format("%02x", b));
			}
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return buffer.toString();
	}
	
	protected String mineBlock(int prefix) 
	{
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!hash.substring(0, prefix).equals(prefixString)) 
		{
            nonce++;
            hash = calculateBlockHash();
        }
        return hash;
    }
    
	
	public UUID getUuid(){
		return uuid;
	}
	
	public String getHash()
	{
		return hash;
	}
	
	public String getPreviousHash()
	{
		return previousHash;
	}
	
	public String getData()
	{
		return data;
	}
	
	public long getTimeStamp()
	{
		return timeStamp;
		
	}
	
	public int getNonce()
	{
		return nonce;
	}
}