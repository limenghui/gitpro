package com.lvmama.credit.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

public class RequestUtil {
	
	public static JSONObject requestPost(String url,String param){
		JSONObject result = new JSONObject();
		URL u ;
		HttpURLConnection conn = null ;
		InputStream in = null;
		PrintWriter out = null;
		ByteArrayOutputStream bos = null;
		try {
			String text = param + "asiuepf8ur98cvjh823498qewufsdujfklasef";
			String sign = DigestUtils.md5Hex(text.getBytes("UTF-8"));
			param += "&signature=" + sign;
			u = new URL(url);
			result.put("url", url + "?" + param);
			conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty(
					"user-agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.120 Safari/537.36");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new PrintWriter(conn.getOutputStream());
			out.print(param );
			out.flush();
			in = conn.getInputStream();
			bos = new ByteArrayOutputStream();
			IOUtils.copy(in, bos);
		    result.put("result",JSONObject.fromObject(new String(bos.toByteArray())));
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(conn != null){
					conn.disconnect();
				}
			} catch (Exception e) {
			}
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(bos);
		}
		return result;
	}
}