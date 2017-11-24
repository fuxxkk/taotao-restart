	<%	
		String callback = request.getParameter("callback") ;
		out.print(callback+"({\"test\":\"hello\"});");
	%>
