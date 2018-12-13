<jsp:include page="../header.jsp"/>
<h1>Login</h1>
    <form action="/login" method="post">
        <table>
            <tr>
                <td>User:</td>
                <td><input type='text' name='username' value=''></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='text' name='password' /></td>
            </tr>
            <tr>
                <td><input class="btn btn-primary" name="submit" type="submit" value="Login" /> | <a class="btn btn-link" href="/">Cancel</a></td>
            </tr>
        </table>
    </form>
<jsp:include page="../footer.jsp"/>