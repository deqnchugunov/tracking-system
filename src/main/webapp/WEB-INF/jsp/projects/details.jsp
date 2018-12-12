<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
    <body>
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
            <h3>${project.description}</h3>
        </div>
    </body>
</html>