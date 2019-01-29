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
        <h3>Settings</h3>
    </div>
    <hr />
    <div>
        <h4>Assigned Users to ${project.name}</h4>
    </div>
    <div>
    <form action="/projects/${project.pattern}/settings/update" method="post">
        <c:forEach items="${allUsers}" var="user">
            <c:set var="isChecked" value="false" />
            <c:forEach items="${assignedUsers}" var="assignedUser">
                <c:if test="${user.id == assignedUser.id}" >
                    <c:set var="isChecked" value="true" />
                </c:if>
            </c:forEach>
            <input type="checkbox" name="${user.id}" value="${user.id}" <c:if test="${isChecked == true}">checked</c:if>>${user.username}<br>
        </c:forEach>
        </div>
        <br />
       <div>
           <td><input class="btn btn-primary" name="submit" type="submit" value="Update" /> | <a class="btn btn-link" href="/projects/${project.pattern}/settings">Cancel</a></td>
       </div>
    </form>
<jsp:include page="../footer.jsp"/>