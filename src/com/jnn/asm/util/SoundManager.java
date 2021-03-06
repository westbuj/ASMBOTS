package com.jnn.asm.util;

import java.util.HashMap;

import com.jnn.asm.bots.BotLoader;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;



public class SoundManager {
	
	private  SoundPool mSoundPool; 
	private  HashMap<Integer, Integer> mSoundPoolMap; 
	public  AudioManager  mAudioManager;
	private  Context mContext;
	
	
	public SoundManager()
	{
		
	}
		
	public void initSounds(Context theContext) { 
		 mContext = theContext;
	     mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0); 
	     mSoundPoolMap = new HashMap<Integer, Integer>(); 
	     mAudioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE); 
	     mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
	     
	} 
	
	public void addSound(int Index,int SoundID)
	{
		mSoundPoolMap.put(Index, mSoundPool.load(mContext, SoundID, 1));
	}
	
	public void playSound(int index) { 
		
		if (!BotLoader.isMute)
	     {int streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC); 
	     mSoundPool.play(mSoundPoolMap.get(index), streamVolume, streamVolume, 1, 0, 1f); 
	     }
	}
	
	public void playLoopedSound(int index) { 
		
		if (!BotLoader.isMute){
	     int streamVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC); 
	    int returnID= mSoundPool.play(mSoundPoolMap.get(index), streamVolume, streamVolume, 1, -1, 1f);
		}
	}
	
}