<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../header.jsp"/>
    <body>
        <div>
            <jsp:include page="../headerNavigation.jsp"/>
        </div>
        <div>
            <h2>Projects</h2>
        </div>
        <c:forEach items="${projects}" var="project">
            <div>
                <a href="/projects/${project.pattern}">${project.name}</a>
            </div>
        </c:forEach>
        <br />
        <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')">
            <a class="btn btn-primary" href="/projects/new">Add New Project</a>
        </sec:authorize>
<jsp:include page="../footer.jsp"/>