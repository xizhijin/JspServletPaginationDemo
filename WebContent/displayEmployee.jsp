<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees</title>
</head>
<body>
	<div align="center">
		<table border="1">
			<tr>
				<th>Emp ID</th>
				<th>Emp Name</th>
				<th>Salary</th>
				<th>Dept Name</th>
			</tr>

			<!-- This JSP page uses JSP Standard Tag Library (JSTL) along with Expression Language (EL),
			 It retrieves the attributes from request scope and displays the result. -->
			<c:forEach var="emp" items="${employeeList}">
				<tr>
					<td>${emp.employeeId}</td>
					<td>${emp.employeeName}</td>
					<td>${emp.salary}</td>
					<td>${emp.deptName}</td>
				</tr>
			</c:forEach>
		</table>

		<table>
			<tr>
				<%-- For displaying Previous link except for the 1st page --%>
				<c:if test="${currentPage != 1}">
					<td><a href="employee.do?page=${currentPage -1}">Previous</a></td>
				</c:if>

				<td>
					<%-- For displaying Page numbers, The when condition does not display a link for the current page --%>
					<table>
						<tr>
							<c:forEach begin="1" end="${numOfPages}" var="i">
								<c:choose>
									<c:when test="${currentPage eq i}">
										<td>${i}</td>
									</c:when>
									<c:otherwise>
										<td><a href="employee.do?page=${i}">${i}</a></td>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</tr>
					</table>
				</td>

				<%-- For displaying Next link --%>
				<c:if test="${currentPage lt numOfPages}">
        		<td><a href="employee.do?page=${currentPage + 1}">Next</a></td>
    		</c:if>
			</tr>
		</table>
	</div>
</body>
</html>