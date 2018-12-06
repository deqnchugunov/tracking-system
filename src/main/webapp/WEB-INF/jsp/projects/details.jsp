<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
    <body>
        <table>
            <tr>
                <td>${project.id}</td>
                <td>${project.name}</td>
                <td>${project.description}</td>
                <td><a href="/projects/${project.pattern}/issues">Issues</a></td>
            </tr>
        </table>
    </body>
</html>