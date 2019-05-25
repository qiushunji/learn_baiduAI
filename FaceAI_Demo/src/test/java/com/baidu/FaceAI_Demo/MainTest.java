package com.baidu.FaceAI_Demo;

import java.io.File;

import junit.framework.TestCase;

/**
 *@Create by QSJ on 2019年5月25日上午10:22:29
 **/
public class MainTest extends TestCase {

	public void FaceIdentity() {
		//String str = FaceIdentifyUtil.detectFace(new File("C:/zhao.jpg"),"10");
		//String str = FaceIdentifyUtil.matchFace(new File("C:/zhao.jpg"), new File("C:/zhao1.jpg"));
		//String str = FaceIdentifyUtil.faceverify(new File("C:/zhao.jpg"));
		String str = FaceIdentifyUtil.personVerify(new File("C:/zhao.jpg"), "420211199111256524", "赵小莹");
		System.out.println(str);
	}
}
