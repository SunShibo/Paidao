<%--<html>--%>
<%--<body>--%>
<%--<h2>Welcome to Paidao's back-end services!</h2>--%>

    <%--<form name="userForm2" action="/user/completeProfile" enctype="multipart/form-data" method="post">--%>
        <%--<div id="newUpload2">--%>
            <%--<input type="file" name="file">--%>
            <%--<input type="text" name="userId" value="15">--%>
            <%--<input type="text" name="nickname" value="shibo">--%>
            <%--<input type="text" name="latitude" value="40.0439">--%>
            <%--<input type="text" name="mediaType" value="image">--%>
        <%--</div>--%>
        <%--<input type="submit" value="submit">--%>
    <%--</form>--%>
<%--</body>--%>
<%--</html>--%>


<html>
<body>
<h2>Welcome to Paidao's back-end services!</h2>

<form name="userForm2" action="/login/signIn" enctype="multipart/form-data" method="post">
    <div id="newUpload2">
        <%--<input type="file" name="file">--%>
        <input type="text" name="account" value="395831708@qq.com">
        <input type="text" name="password" value="123456">
        <%--<input type="text" name="latitude" value="40.0439">--%>
        <%--<input type="text" name="mediaType" value="image">--%>
    </div>
    <input type="submit" value="submit">
</form>
</body>
</html>