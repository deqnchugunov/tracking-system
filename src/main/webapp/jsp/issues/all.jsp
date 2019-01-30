<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
    <body>
        <div><a href="/">Home</a> |
        <a href="/projects">Projects</a> |
        <a href="/my/account">My Account</a> |
        <a href="my/page">My Page</a></div>
        <br />
        <div><h2>Projects</h2></div>
            <br />
            <c:forEach items="${projects}" var="project">
                <div><a href="/projects/${project.pattern}">${project.name}</a></div>
            </c:forEach>
            <br />
        <a href="/projects/new">New Project</a>
    </body>
</html>