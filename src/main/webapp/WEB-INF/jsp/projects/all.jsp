<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
    <body>
        <div>Projects</div>
        <table>
            <c:forEach items="${projects}" var="project">
                <tr>
                    <td><a href="/projects/${project.pattern}">${project.name}</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>