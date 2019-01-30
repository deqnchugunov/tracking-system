<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"/>
    <div>
        <jsp:include page="headerNavigation.jsp"/>
    </div>
    <br />
    <div>
        <h2>Welcome, ${username}!</h2>
    </div>
    <div>
        <div>user authorities:</div>
        <c:forEach items="${authorities}" var="authority">
            <span>${authority}</span><br />
        </c:forEach>
    </div>
<jsp:include page="footer.jsp"/>