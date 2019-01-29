<jsp:include page="../header.jsp"/>
    <form action="/projects/new" method="post">
        <h2>Add New Project</h2>
        <label>
            Name:
            <input type="text" name="name"/>
        </label>
        <div>&nbsp;</div>
        <label>
            Description:
            <input type="text" name="description"/>
        </label>
        <div>&nbsp;</div>
        <div><button class="btn btn-primary" type="submit">Create</button> | <a href="/projects">Cancel</a></div>
    </form>
<jsp:include page="../footer.jsp"/>