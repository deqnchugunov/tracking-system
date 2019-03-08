<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div>
    <c:choose>
        <c:when test="${un == 'Guest'}">
            <span class="h6">Hi, ${un}!</span> |
        </c:when>
        <c:otherwise>
            <span class="h6">Hi, </span><a href="/user/page">${un}</a>! |
        </c:otherwise>
    </c:choose>
    <a href="/">Home</a> |
    <a href="/projects">Projects</a> |
    <a href="/user/account">My Account</a> |
    <a href="/user/page">My Page</a> |
    <sec:authorize access="hasRole('ROLE_ADMIN')">
       <a href="/user/manage">Manage Users</a> |
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <a class="btn btn-default" href="/logout">Log out</a>
    </sec:authorize>
    <sec:authorize access="isAnonymous()">
       <a class="btn btn-default" href="/login">Log In</a> |
       <a class="btn btn-default" href="/user/register">Sign Up</a>
    </sec:authorize>
</div>
