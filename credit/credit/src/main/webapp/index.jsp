<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口测试</title>

</head>
<body>
<div name="left" style="width:45%;border:1px solid;float:left;">
	<from action="" method="POST">
	应用：<input type="text" name ="appName" value="" style="width:40%"><br/><br/>
	接口:<select name="interfaceName">
		<option value="pay/applyCard.do" selected>申请虚拟信用卡</option>
		<option value="query/queryVirtualCard.do">查询虚拟信用卡</option>
		<option value="apply/cancel.do">取消虚拟信用卡</option>
	</select><br/><br/>
	<div name="a_pay">
		订单编号：<input type="text" name="orderId"/><br/><br/>
		购买类型：<input type="text" name="purchaseType"/><br/><br/>
		供应商名称：<input type="text" name="supplierName"/><br/><br/>
		货币代码：<input type="text" name="currencyCode"/><br/><br/>
		交易金额：<input type="text" name="amount"/><br/><br/>
		有效期：<input type="text" name="validFrom"/> ~ <input type="text" name="validTo"/>(格式为yyyy-MM-dd)<br/><br/>
		最大交易次数：<input type="text" name="maxTrans"/><br/><br/>
		备注：<input type="text" name="remark" style="width:40%"/><br/><br/>
		来源：<input type="text" name="platFrom"/><br/><br/>
		附加字段名称：<input type="text" name="fieldName" style="width:40%"/>(多个字段以,进行分割)<br/><br/>
		附加字段值：<input type="text" name="fieldValue" style="width:40%"/>(多个值以,进行分割)<br/><br/>
	</div>
	<div name="a_query" style="display:none;">
		订单编号：<input type="text" name="orderId"/><br/><br/>
	</div>
	<div name="a_apply" style="display:none;">
		订单编号：<input type="text" name="orderId"/><br/><br/>
		卡号：<input type="text" name="cardNo"/><br/><br/>
	</div>
	<input type="button" value="提交" id="button"/>
</from>
</div>
<div name="right" style="width:45%;border:1px solid;float:right;">
请求url:<input type="text" name="url" style="width:99%"/><br/>
结果显示：<textarea id="content" name="content"  style="width: 99%; min-height: 500px;"></textarea> 
</div>
	
</body>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jsformat.js"></script>
<script type="text/javascript" src="js/htmlformat.js"></script>
<script type="text/javascript">
	$(function(){
		$("select").find("option:eq(0)").attr("selected","selected");
		$("#button").click(function(){
			if($("input[name=appName]").val() == null ){
				alert("应用不能为空！");
			}else{
				var url1 =  $("select[name=interfaceName]").val().split("/")[0];
				var data = {"appName":$("input[name=appName]").val(),"interfaceName":$("select[name=interfaceName]").val()};
				$("div[name*=" + url1 + "]").find("input").each(function(){
					data[$(this).attr("name")] = $(this).val();	
					
				});
				  $.ajax({
						type:"POST",
						data:data,
						url:"/credit/" + url1 + ".do",
						dataType:"JSON",
						success:function(result){
							result = JSON.parse(result);
							$("input[name=url]").val(result.url);
							delete result['url'];
							if(result.result){
								var data = JSON.stringify(result.result);
								console.info(data);
								data = data.replace(/^\s+/, '');
								var html = "";
								if (data && data.charAt(0) === '<') {
									html = style_html(data, 4,  ' ', 80);
						        } else {
						        	html = js_beautify(data, 4,  ' ');
						        }
								$("#content").text(html);
							}
							
						}
					});	  
			}
		});
		
	   $("select[name=interfaceName]").change(function(){
		   	$("div[name*=a]").hide();
		   	var value = $(this).val().split("/")[0];
		   	$("div[name*=" + value + "]").show();
	   });
	});

</script>
</html>