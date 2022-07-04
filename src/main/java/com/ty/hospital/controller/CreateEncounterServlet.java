package com.ty.hospital.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ty.hospital.dao.EncounterDao;
import com.ty.hospital.dto.Encounter;

@WebServlet(value="/createencounter")
public class CreateEncounterServlet extends HttpServlet{

	 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Encounter encounter=new Encounter();
	
	HttpSession httpSession=req.getSession();	
	Integer branchId=(Integer)httpSession.getAttribute("branchid");
	String name=(String)httpSession.getAttribute("name");
	String reason=	req.getParameter("encounterReason");
	
    String date=req.getParameter("encounterDate");
   LocalDateTime localDateTime=LocalDateTime.parse(date,DateTimeFormatter.ISO_DATE_TIME);
		
	encounter.setReason(reason);
	encounter.setCreatorName(name);
	encounter.setCreatedDateTime(localDateTime);
	
	int personId=Integer.parseInt(req.getParameter("personid"));
	
	EncounterDao encounterDao=new EncounterDao();
	encounterDao.createEncounter(personId, branchId, encounter);

	PrintWriter printWriter = resp.getWriter();
	printWriter.print("<html><body><h1> Encounter Created </h1></body></html>");

	RequestDispatcher dispatcher = req.getRequestDispatcher("CreateEncounter.jsp");
	dispatcher.include(req, resp);
	}
}
