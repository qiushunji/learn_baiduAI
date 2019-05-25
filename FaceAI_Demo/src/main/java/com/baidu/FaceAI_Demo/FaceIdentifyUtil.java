package com.baidu.FaceAI_Demo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.FaceVerifyRequest;
import com.baidu.aip.face.MatchRequest;
import com.baidu.aip.util.Base64Util;

/**
 * @Create by QSJ on 2019年5月14日上午10:45:26
 **/
public class FaceIdentifyUtil {

	// 设置APPID/AK/SK
	public static final String APP_ID = "16243325";
	public static final String API_KEY = "AageFzNeWBgPL9bQdOAQyMuN";
	public static final String SECRET_KEY = "ywUZtBXydoNFvgQSKAmyq4V6Xy0UR7Or";

	static AipFace client = null;

	static {
		client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
	}

	/**
	 * *人脸检测
	 * 
	 * @param file
	 * @param max_face_num
	 * @return
	 */
	public static String detectFace(File file, String max_face_num) {
		try {
			HashMap<String, String> options = new HashMap<String, String>();
			options.put("face_field", "age,beauty,expression,faceshape,gender,glasses,race");
			options.put("max_face_num", max_face_num);
			options.put("face_type", "LIVE");

			// 图片数据
			String imgStr = Base64Util.encode(FileToByte(file));
			String imageType = "BASE64";
			JSONObject res = client.detect(imgStr, imageType, options);
			System.out.println(res.toString(2));
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * *人脸对比
	 * 
	 * @param file1
	 * @param file2
	 * @return
	 */
	public static String matchFace(File file1, File file2) {
		try {
			String imgStr1 = Base64Util.encode(FileToByte(file1));
			String imgStr2 = Base64Util.encode(FileToByte(file2));
			MatchRequest req1 = new MatchRequest(imgStr1, "BASE64");
			MatchRequest req2 = new MatchRequest(imgStr2, "BASE64");
			ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
			requests.add(req1);
			requests.add(req2);
			JSONObject res = client.match(requests);
			System.out.println(res.toString(2));
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * *活体检测
	 * 
	 * @param arg0
	 * @return
	 */
	public static String faceverify(File file) {
		try {
			String imgStr = Base64Util.encode(FileToByte(file));
			String imageType = "BASE64";
			FaceVerifyRequest req = new FaceVerifyRequest(imgStr, imageType);
			ArrayList<FaceVerifyRequest> list = new ArrayList<FaceVerifyRequest>();
			list.add(req);
			JSONObject res = client.faceverify(list);
			System.out.println(res.toString(2));
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * *身份验证
	 * 
	 * @param arg0
	 * @param idCardNumber
	 * @param name
	 * @return
	 */
	public static String personVerify(File file, String idCardNumber, String name) {
		try {
			String imgStr = Base64Util.encode(FileToByte(file));
			String imageType = "BASE64";
			// 传入可选参数调用接口
			HashMap<String, String> options = new HashMap<String, String>();
			options.put("quality_control", "NORMAL");
			options.put("liveness_control", "LOW");

			// 身份验证
			JSONObject res = client.personVerify(imgStr, imageType, idCardNumber, name, options);
			System.out.println(res.toString(2));
			return res.toString(2);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	/**
	 * *图片数据转换为流数据
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static byte[] FileToByte(File file) throws IOException {
		// 将数据转为流
		InputStream content = new FileInputStream(file);
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = content.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		return swapStream.toByteArray();
	}
}
