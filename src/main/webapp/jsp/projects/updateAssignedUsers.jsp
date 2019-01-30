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
    <form class="border border-light p-5" action="/projects/${project.pattern}/settings/update" method="post">
       <c:forEach items="${allUsers}" var="user">
           <c:set var="isChecked" value="false" />
           <c:forEach items="${assignedUsers}" var="assignedUser">
               <c:if test="${user.id == assignedUser.id}" >
                   <c:set var="isChecked" value="true" />
                </c:if>
            </c:forEach>
            <input type="checkbox" name="${user.id}" value="${user.id}" <c:if test="${isChecked == true}">checked</c:if>>${user.username}<br>
        </c:forEach>
        <br />
       <div>
           <td><input class="btn btn-primary" name="submit" type="submit" value="Save Changes" /></td> | <a class="btn btn-link" href="/projects/${project.pattern}/users">Cancel</a>
       </div>
    </form>
<jsp:include page="../footer.jsp"/>