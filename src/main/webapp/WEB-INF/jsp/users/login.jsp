<!DOCTYPE html>
<html lang="en">
<body>
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
            <td><input name="submit" type="submit" value="Login" /> | <a href="/">Cancel</a></td>
        </tr>
    </table>
</form>
</body>
</html>