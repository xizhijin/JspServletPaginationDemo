package com.xizhijin.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xizhijin.dao.EmployeeDAO;
import com.xizhijin.po.Employee;

@SuppressWarnings("serial")
public class EmployeeServlet extends HttpServlet {
	
	public EmployeeServlet() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		int recordsPerPage = 5;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		EmployeeDAO dao = new EmployeeDAO();
		
		List<Employee> list = dao.viewAllEmployees((page - 1) * recordsPerPage, recordsPerPage);
		int numOfRecords = dao.getNumOfRecords();
		int numOfPages = (int) Math.ceil(numOfRecords * 1.0 / recordsPerPage);
		
		request.setAttribute("employeeList", list);
		request.setAttribute("numOfPages", numOfPages);
		request.setAttribute("currentPage", page);
		RequestDispatcher view = request.getRequestDispatcher("displayEmployee.jsp");
		view.forward(request, response);
	}
}
