<jsp:include page="../header.jsp"/>
    <h2>Sign up new User</h2>
    <form action="/user/register" method="post">
        <table>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="text" name="password"/></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="text" name="email"/></td>
            </tr>
            <tr>
                <td><button class="btn btn-primary" name="submit" type="submit">Sign Up</button> | <a class="btn btn-link" href="/">Cancel</a></td>
            </tr>
        </table>
    </form>
<jsp:include page="../footer.jsp"/>