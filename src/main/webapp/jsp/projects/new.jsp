<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp"/>
    <body>
        <div>
            <jsp:include page="../headerNavigation.jsp"/>
        </div>
        <div>
            <h2>Add new Project</h2>
        </div>
        <br />
        <form class="form-horizontal" action="/projects/new" method="post">
             <div class="form-group">
                <label>Name</label>
                    <input type="text" name="name"/>
             </div>
            <div class="form-group">
                <label>Description</label>
                    <input type="text" name="description"/>
            </div>
            <div>&nbsp;</div>
            <div><button class="btn btn-primary" type="submit">Create</button> | <a href="/projects">Cancel</a></div>
        </form>
<jsp:include page="../footer.jsp"/>