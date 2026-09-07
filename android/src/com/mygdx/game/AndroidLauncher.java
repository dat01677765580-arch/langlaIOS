package com.mygdx.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.tgame.model.Caption;

import beatdz.AppListener;
import beatdz.Utlis;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(AppListener.gI(), config);
		Caption.loadVN();
//		int[] ip = new int[]{832, 928, 928, 896, 920, 464, 376, 376, 864, 776, 880, 824, 864, 776, 872,
//				808, 872, 808, 368, 792, 888, 872, 376, 920, 808, 912, 944, 808, 912, 864,
//				840, 920, 928, 832, 888, 776, 880, 824, 800, 976, 368, 928, 960, 928};
//		Utlis.ipsv = "https://thegioininja.com/serverlisthoangdz.txt";
		Utlis.ipsv = "https://thegioinhangia.com/serverlist000.txt";
	}
	public static String decode(int[] ipArray) {
		StringBuilder sb = new StringBuilder();

		for (int encodedChar : ipArray) {
			char originalChar = (char) (encodedChar >> 3);
			sb.append(originalChar);
		}

		return sb.toString();
	}
}
