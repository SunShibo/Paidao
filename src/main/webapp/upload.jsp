<%--<html>--%>
<%--<body>--%>
<%--<h2>Welcome to Paidao's back-end services!</h2>--%>

    <%--<form name="userForm2" action="/user/completeProfile" enctype="multipart/form-data" method="post">--%>
        <%--<div id="newUpload2">--%>
            <%--<input type="file" name="file">--%>
            <%--<input type="text" name="userId" value="15">--%>
            <%--<input type="text" name="nickname" value="shibo">--%>
            <%--<input type="text" name="latitude" value="40.0439">--%>
            <%--<input type="text" name="mediavalue="image">--%>
        <%--</div>--%>
        <%--<input type="submit" value="submit">--%>
    <%--</form>--%>
<%--</body>--%>
<%--</html>--%>


<html>
<body>
<h2>Welcome to Paidao's back-end services!</h2>

<form name="userForm1" action="/login/signIn" enctype="multipart/form-data" method="post">
    <div id="newUpload1">
    <input type="text" name="account" value="395831708@qq.com">
    <input type="text" name="password" value="123456">
    </div>
    <input type="submit" value="submit">
</form>

<form name="userForm2" action="/activity/issueActivity" enctype="multipart/form-data" method="post">
    <div id="newUpload2">
        <%--<input type="file" name="file">--%>
        <input type="text" name="mediaType" value="image">
        <input type="text" name="position" value="Beijing Chaoyang">
        <input type="text" name="description" value="This is my first activity">
        <input type="text" name="longitude" value="116.477316">
        <input type="text" name="latitude" value="39.963053">
        <input type="text" name="title" value="title">
        <input type="text" name="areaCode" value="7737">
        <input type="file" name="file">
    </div>
    <input type="submit" value="submit">
</form>
</body>
</html>