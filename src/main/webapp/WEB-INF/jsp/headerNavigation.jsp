<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div>
    <a href="/">Home</a> |
    <a href="/projects">Projects</a> |
    <a href="/my/account">My Account</a> |
    <a href="/my/page">My Page</a> |
    <sec:authorize access="hasRole('ROLE_ADMIN')">
       <a href="/users/manage">Manage Users</a> |
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <a class="btn btn-primary" href="/logout">Log out</a>
    </sec:authorize>
    <sec:authorize access="isAnonymous()">
       <a class="btn btn-primary" href="/login">Log In</a> |
       <a class="btn btn-primary" href="/users/register">Sign Up</a>
    </sec:authorize>
</div>
