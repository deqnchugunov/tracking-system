<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp"/>
    <div>
        <jsp:include page="../headerNavigation.jsp"/>
    </div>
    <div>
        <h2>${project.name}</h2>
    </div>
    <div>
        <jsp:include page="../projectNavigation.jsp"/>
    </div>
    <div>
        <h3>Assigned Users</h3>
    </div>
    <hr />
   <div>
        <h4>Users Assigned to Project</h4>
   </div>
   <c:forEach items="${assignedUsers}" var="assignedUser">
        <div><a href="/users/${assignedUser.username}">${assignedUser.username}</a></div>
    </c:forEach>
    <br />
   <div>
        <a class="btn btn-primary" href="/projects/${project.pattern}/users/update">Update</a>
   </div>
<jsp:include page="../footer.jsp"/>