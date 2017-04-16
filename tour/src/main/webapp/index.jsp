<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<body>
<h2>Hello World!</h2>
<input type="file" id="upload" name="imagefile" accept="image/png,image/gif">
<input  type="button"  value="上传" onclick="ajaxFileUpload();"/>
<img id="logo" width="100" height="100" src=""/>

<script src="<%=basePath%>/resources/js/jquery-1.8.2.min.js" type="text/javascript"></script>
<script src="<%=basePath%>/resources/assets/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
	var ctx='http://localhost:8080/tour';
	//图片上传
	function ajaxFileUpload(){
	    $.ajaxFileUpload({
	        url:ctx+'/upload/uploadImage',//用于文件上传的服务器端请求地址
	        secureuri:false ,//一般设置为false
	        fileElementId:'upload', 
	        dataType: 'JSON',//返回值类型 一般设置为json
	        success: function (res){  //服务器成功响应处理函数
	        	console.log(res);
	        	$('#logo').attr('src',ctx+res);
	        },
	        error: function (data, status, e){//服务器响应失败处理函数
	        	console.log(e);
	        }
	    });
	}
</script> 
</body>
</html>
