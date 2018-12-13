<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
    <a href="/">Home</a> |
    <a href="/projects">Projects</a> |
    <a href="/my/account">My Account</a> |
    <a href="/my/page">My Page</a> ||
    <c:choose>
        <c:when test="${pageContext.request.userPrincipal.authenticated}">
            <a href="/logout">Log out</a> |
        </c:when>
        <c:otherwise>
            <a href="/login">Log In</a> |
            <a href="/users/register">Sign Up</a>
        </c:otherwise>
    </c:choose>
</div>
