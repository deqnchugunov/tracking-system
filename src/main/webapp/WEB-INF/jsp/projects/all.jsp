<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <a class="btn btn-primary" href="/projects/new">Add New Project</a>
<jsp:include page="../footer.jsp"/>