<jsp:include page="../header.jsp"/>
    <h2>Sign up new User</h2>
    <form action="/user/register" method="post">
        <label>
            Username:
            <input type="text" name="username"/>
        </label>
        <div>&nbsp;</div>
        <label>
            Password:
            <input type="text" name="password"/>
        </label>
        <div>&nbsp;</div>
            <label>
            Email:
                <input type="text" name="email"/>
            </label>
        <div>&nbsp;</div>
        <div><button class="btn btn-primary" name="submit" type="submit">Sign Up</button> | <a class="btn btn-link" href="/">Cancel</a></div>
    </form>
<jsp:include page="../footer.jsp"/>