package com.baidu.FaceAI_Demo;

import java.io.File;

/**
 *@Create by QSJ on 2019年5月25日上午9:44:42
 **/
public class Main {

	public static void main(String[] args) {
		String str = FaceIdentifyUtil.detectFace(new File("C:/zhao2.jpg"),"10");
		//对json数据做数据处理
		DetectFace_DataFactory date = new DetectFace_DataFactory(str);
		date.show();
		
	}
}