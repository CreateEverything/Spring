<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛软件CRM-首页</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <jsp:include page="../include/css.jsp"/>
    <link rel="stylesheet" href="/static/plugins/webuploader/webuploader.css">
    <link rel="stylesheet" href="/static/plugins/layer/mobile/need/layer.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <!-- 顶部导航栏部分 -->
    <jsp:include page="../include/top.jsp"/>

    <!-- =============================================== -->

    <!-- 左侧菜单栏 -->
    <jsp:include page="../include/left.jsp">
        <jsp:param name="menu" value="dropbox"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <div class="box">
            <!-- Main content -->
            <section class="content">
                <div class="box-header with-border">
                    <h3 class="box-title">公司网盘</h3>
                </div>

                    <button class="btn btn-danger" ><i class="fa fa-arrow-left">　</i>返回上一级</button>

                <div class="box-tools pull-right">

                    <button class="btn btn-success" style="display: inline-block;margin-top: -34px;padding: 9px"><i class="fa fa-folder">　</i>新建文件夹</button>

                    <div id="picker" style="display: inline-block"><i class="fa fa-file-excel-o">　</i> 上传文件</div>
                </div>
                <table class="table table-hover">

                    <c:if test="${not empty diskList}">
                        <tbody>
                        <tr>
                            <td width="10px"></td>
                            <td></td>
                            <td>文件名</td>
                            <td>文件大小</td>
                            <td>文件创建时间</td>
                        </tr>
                        <c:forEach items="${diskList}" var="disk">
                            <tr class="fileList">
                                <td></td>
                                <c:if test="${disk.type != 'dir'}">
                                    <td><i class="fa fa-file fa-3x" aria-hidden="true"></i></td>
                                </c:if>
                                <c:if test="${disk.type == 'dir'}">
                                    <td><i class="fa fa-folder fa-3x" aria-hidden="true"></i></td>
                                </c:if>
                                <td style="font-size: 30px">${disk.name}</td>
                                <td style="font-size: 30px">${disk.size}</td>
                                <td style="font-size: 30px"><fmt:formatDate value="${disk.updateTime}"/></td>
                                <td>
                                    <c:if test="${disk.type != 'dir'}">
                                        <button  class="layui-btn layui-btn-warm"  >下载</button>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </c:if>
                    <c:if test="${empty diskList}">
                        <div class="alert alert-danger" role="alert" style="margin-top: 40px">
                            <div style="padding-left: 500px">网盘中空空如也~~~</div>
                        </div>
                    </c:if>
                </table>
                <%--文件--%>


            </section>
        </div>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- 底部 -->
    <jsp:include page="../include/footer.jsp"/>

</div>
<!-- ./wrapper -->

<!-- jQuery 2.2.3 -->
<jsp:include page="../include/script.jsp"/>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/webuploader/webuploader.js"></script>
<script>
    (function(){
        var uploader = WebUploader.create({
            pick:'#picker',
            swf:'/static/plugins/webuploader/Uploader.swf',
            server:'/dropbox/fileUpload',
            auto:true,
            fileVal:'file',
        });
        $(".fileList").mouseenter(function(){
            $(".layui-btn layui-btn-warm").show();
        });
    })()
</script>
</body>
</html>
