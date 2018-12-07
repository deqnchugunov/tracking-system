<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
    <body>
        <div><a href="/">Home</a> |
        <a href="/projects">Projects</a> |
        <a href="/my/account">My Account</a> |
        <a href="my/page">My Page</a></div>
        <br />
        <div><h2>${project.name}</h2></div>
        <div><a href="/projects/${project.pattern}">Overview</a> |
        <a href="/projects/${project.pattern}/issues/new">New Issue</a> |
        <a href="/projects/${project.pattern}/issues">Issues</a> |
        <a href="/projects/${project.pattern}/documents">Documents</a> |
        <a href="/projects/${project.pattern}/settings">Settings</a></div>
        <div><h3>${project.description}</h3></div>
    </body>
</html>