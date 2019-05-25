package com.baidu.FaceAI_Demo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DetectFace_DataFactory {
	
	JSONArray array;
	DetectFace_DataFactory() {}
	
	//age,beauty,expression,faceshape,gender,glasses,race
	DetectFace_DataFactory(String str) {
		JSONObject obj = JSONObject.fromObject(str);
		JSONObject res = (JSONObject) obj.get("result");
		array = res.getJSONArray("face_list");
		
	}
	public void show() {
		for (int i = 0; i < array.size(); i++) {
			System.out.println("\n********第"+ (i+1) +"张人脸*******");
			showInfo((JSONObject) array.get(i));
		}
	}
	private void showInfo(JSONObject faceID) {
		System.out.println("年龄:"+getAge(faceID));
		System.out.println("颜值:"+getBeauty(faceID));
		System.out.println("肤色:"+getRace(faceID));
		System.out.println("脸型:"+getFaceshape(faceID));
		System.out.println(""+getExpression(faceID));
		System.out.println(""+getGender(faceID));
		System.out.println(""+getGlasses(faceID));
	}

	private String getGlasses(JSONObject faceID) {
		JSONObject glasses = (JSONObject) faceID.get("glasses");
		String str= glasses.get("type").toString();
		if(!str.equals("none")) {
			return "佩戴眼镜";
		}else {
			return "未佩戴眼镜";
		}
	}

	private String getGender(JSONObject faceID) {
		JSONObject gender = (JSONObject) faceID.get("gender");
		String str = gender.get("type").toString();
		if(str.equals("male")) {
			return "男";
		}
		if(str.equals("female")){
			return "女";
		}
		return null;
	}

	private String getRace(JSONObject faceID) {
		JSONObject race = (JSONObject) faceID.get("race");
		String str = race.get("type").toString();
		if(str.equals("yellow")) {
			return "黄色";
		}
		if(str.equals("white")){
			return "白色";
		}
		if(str.equals("black")){
			return "黑色";
		}
		if(str.equals("arabs")){
			return "阿拉伯人";
		}
		return null;
	}
	private String getFaceshape(JSONObject faceID) {
		//square/triangle/oval/heart/round
		JSONObject faceshape = (JSONObject) faceID.get("face_shape");
		String str = faceshape.get("type").toString();
		if(str.equals("square")) {
			return "方形";
		}
		if(str.equals("triangle")){
			return "三角形";
		}
		if(str.equals("oval")){
			return "椭圆形";
		}
		if(str.equals("heart")){
			return "心形";
		}
		if(str.equals("round")){
			return "圆形";
		}
		return null;
	}

	private String getExpression(JSONObject faceID) {
		JSONObject expression = (JSONObject) faceID.get("expression");
		String str = expression.get("type").toString();
		if(str.equals("none")) {
			return "不笑";
		}
		if(str.equals("smile")) {
			return "微笑";
		}
		if(str.equals("laugh")) {
			return "大笑";
		}
		return null;
	}

	private String getBeauty(JSONObject faceID) {
		return faceID.get("beauty").toString();
	}

	private String getAge(JSONObject faceID) {
		return faceID.get("age").toString();
	}
	
}
