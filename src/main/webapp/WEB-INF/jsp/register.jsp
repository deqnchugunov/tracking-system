<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
    <h2>Sign up new User</h2>

    <form action="/register" method="post">
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
        <div><button name="submit" type="submit">Sign Up</button> | <a href="/">Cancel</a></div>
    </form>
</body>
</html>