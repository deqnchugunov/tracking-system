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
        <h3>Overview</h3>
        <hr />
        <h4>${project.description}</h4>
    </div>
<jsp:include page="../footer.jsp"/>