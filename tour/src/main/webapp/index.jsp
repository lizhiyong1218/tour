<html>
<link rel="stylesheet" href="http://localhost:8080/tour/resources/assets/kindeditor/themes/default/default.css" />
<body>
<h2>Hello World!</h2>
<textarea id="editor_id" name="content" style="width:700px;height:300px;">内容</textarea>

<script charset="utf-8" src="http://localhost:8080/tour/resources/assets/kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="http://localhost:8080/tour/resources/assets/kindeditor/lang/zh_CN.js"></script>
<script>
    KindEditor.ready(function(K) {
            window.editor = K.create('#editor_id');
            
    });
</script>
</body>
</html>
