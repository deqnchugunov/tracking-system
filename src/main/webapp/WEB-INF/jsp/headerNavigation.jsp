<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
    <a href="/">Home</a> |
    <a href="/projects">Projects</a> |
    <a href="/my/account">My Account</a> |
    <a href="/my/page">My Page</a> ||
    <c:choose>
        <c:when test="${pageContext.request.userPrincipal.authenticated}">
            <a class="btn btn-primary" href="/logout">Log out</a>
        </c:when>
        <c:otherwise>
            <a class="btn btn-primary" href="/login">Log In</a> |
            <a class="btn btn-primary" href="/users/register">Sign Up</a>
        </c:otherwise>
    </c:choose>
</div>
