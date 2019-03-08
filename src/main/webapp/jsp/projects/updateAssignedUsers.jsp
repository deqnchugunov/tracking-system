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
    <form action="/projects/${project.pattern}/users/update" method="post">
       <table class="table">
         <tr>
           <th>&nbsp;</th>
           <th>Name</th>
           <th>Email</th>
         </tr>
            <c:forEach items="${allUsers}" var="user">
                <c:set var="isChecked" value="false" />
                <c:forEach items="${assignedUsers}" var="assignedUser">
                    <c:if test="${user.id == assignedUser.id}" >
                        <c:set var="isChecked" value="true" />
                    </c:if>
                </c:forEach>
                <tr>
                    <td><input type="checkbox" name="${user.id}" value="${user.id}" <c:if test="${isChecked == true}">checked</c:if>></td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                 </tr>
            </c:forEach>
        </table>
        <button class="btn btn-primary" name="submit" type="submit">Update</button> | <a class="btn btn-link" href="/projects/${project.pattern}/users">Cancel</a>
     </form>
<jsp:include page="../footer.jsp"/>