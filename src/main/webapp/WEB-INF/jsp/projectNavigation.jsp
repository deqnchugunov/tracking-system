<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<a href="/projects/${project.pattern}">Overview</a> |
<a href="/projects/${project.pattern}/issues">Issues</a> |
<a href="/projects/${project.pattern}/issues/new">New Issue</a> |
<a href="/projects/${project.pattern}/documents">Documents</a> |
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <a href="/projects/${project.pattern}/settings">Settings</a>
</sec:authorize>