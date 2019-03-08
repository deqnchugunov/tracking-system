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
            <table>
             <tr>
                 <td>Name</td>
                 <td><input type="text" name="name"/></td>
             </tr>
             <tr>
                 <td>Description</td>
                 <td><textarea type="text" name="description"></textarea></td>
            </tr>
           <tr>
               <td><button class="btn btn-primary" type="submit">Create</button> | <a href="/projects">Cancel</a></td>
            </tr>
          </table>
        </form>
<jsp:include page="../footer.jsp"/>