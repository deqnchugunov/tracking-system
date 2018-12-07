<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
    <body>
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
            <div><button type="submit">Create</button> | <a href="/projects">Cancel</a></div>
        </form>
    </body>
</html>