package com.lxz.service;

import org.springframework.stereotype.Service;

@Service
public class StatTest {
	public void doRun(){
		System.out.println("doRun.........");
	}
	public void doRun1(){
		try{
			Thread.sleep(1000*3);
			System.out.println("doRun1.........");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
