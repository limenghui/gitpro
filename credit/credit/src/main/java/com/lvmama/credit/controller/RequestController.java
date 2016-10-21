package com.lvmama.credit.controller;



import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lvmama.credit.util.RequestUtil;



@Controller
public class RequestController {

	@RequestMapping(value="/pay")
	public @ResponseBody JSONObject pay(String appName,String interfaceName,String orderId,String purchaseType,String supplierName,String currencyCode,
			String amount,String validFrom,String validTo,String maxTrans,String remark,String platFrom,String fieldName,String fieldValue){
		StringBuilder builder = new StringBuilder();
		if(!StringUtils.isEmpty(amount)){
			builder.append("amount=").append(amount);
		}
		if(!StringUtils.isEmpty(currencyCode)){
			builder.append("&currencyCode=").append(currencyCode);
		}
		if(!StringUtils.isEmpty(fieldName)){
			String[] fieldNames = fieldName.split(",");
			for(String field:fieldNames){
				builder.append("&fieldName=").append(field);
			}
		}
		if(!StringUtils.isEmpty(fieldValue)){
			String[] fieldValues = fieldValue.split(",");
			for(String field:fieldValues){
				builder.append("&fieldValue=").append(field);
			}
		}
		if(!StringUtils.isEmpty(maxTrans)){
			builder.append("&maxTrans=").append(maxTrans);
		}
		if(!StringUtils.isEmpty(orderId)){
			builder.append("&orderId=").append(orderId);
		}
		if(!StringUtils.isEmpty(platFrom)){
			builder.append("&platFrom=").append(platFrom);
		}
		if(!StringUtils.isEmpty(purchaseType)){
			builder.append("&purchaseType=").append(purchaseType);
		}
		if(!StringUtils.isEmpty(remark)){
			builder.append("&remark=").append(remark);
		}
		if(!StringUtils.isEmpty(supplierName)){
			builder.append("&supplierName=").append(supplierName);
		}
		if(!StringUtils.isEmpty(validFrom)){
			builder.append("&validFrom=").append(validFrom);
		}
		if(!StringUtils.isEmpty(validTo)){
			builder.append("&validTo=").append(validTo);
		}
		if(builder.toString().startsWith("&")){
			builder.delete(0, 1);
		}
		return  RequestUtil.requestPost(appName + interfaceName, builder.toString());
	}
	
	@RequestMapping(value="/query")
	public @ResponseBody JSONObject query(String appName,String interfaceName,String orderId){
		String param = "orderId=" +orderId;
		return  RequestUtil.requestPost(appName + interfaceName, param);
	}
	@RequestMapping(value="/apply")
	public @ResponseBody JSONObject apply(String appName,String interfaceName,String orderId,String cardNo){
		String param =  "cardNo=" + cardNo + "&orderId=" +orderId;
		return   RequestUtil.requestPost(appName + interfaceName, param);
	}
}
