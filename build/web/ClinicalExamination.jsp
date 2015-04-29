<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<%
	String result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
					"<tns:ExamRequest id=\"0\" exam=\"ClinicalExamination\" xmlns:tns=\"http://www.example.org/ExamRequest\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.example.org/ExamRequest ExamRequest.xsd\">" +
					"<tns:State name=\"FluidGlands\" type=\"simple\"/>" +
					"</tns:ExamRequest>";
	
					try {
				        String data = URLEncoder.encode("exam", "UTF-8") + "=" + URLEncoder.encode(result, "UTF-8");
				    
				        URL url = new URL("http://localhost:8080/SemPathService/sempath");
				        URLConnection conn = url.openConnection();
				        conn.setDoOutput(true);
				        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
				        wr.write(data);
				        wr.flush();
				    
				        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				        String line;
				        while ((line = rd.readLine()) != null) {
				        	System.out.println(line);
				        }
				        wr.close();
				        rd.close();
				    } catch (Exception e) {
				    	e.printStackTrace();
				    }
%>
