package org.openiam.selfsrvc.sso;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.net.*;

/**
 * Servlet implementation class PtsmSSO
 */
public class PtsmSSO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PtsmSSO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(request, response);
		System.out.println("In doGet...");
		
		response.setContentType("text/html");
		String appUrl = request.getParameter("url");	
		
		System.out.println("URL = " + appUrl);
		
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute("login");
		if (login == null) {
			login = "sshah";
		}
			
		System.out.println("login = " + login);
		
		try {
			
			PrintWriter pw = response.getWriter();
			
			//URL url = new URL("http://localhost:51616/Default.aspx");
			URL url = new URL(appUrl);
			System.out.println("Url = " + url);
            URLConnection connection = url.openConnection();
            
            System.out.println("Url connection = " + connection);
            
            connection.setRequestProperty("userPrincipalName", login);
            // add the additional header:
            connection.setRequestProperty("Connection", request.getHeader("Connection"));
            connection.setRequestProperty("Keep-Alive", "300");
            connection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8" );
            connection.setRequestProperty("Accept-Charset","ISO-8859-1,utf-8;q=0.7,*;q=0.7" );           
            connection.setRequestProperty("Accept-Encoding","gzip,deflate" );
            connection.setRequestProperty("Accept-Language",request.getLocale().toString());
            connection.setRequestProperty("Host", request.getRemoteHost() );
            connection.setRequestProperty("Referer", request.getRequestURL().toString() );
            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 1.1.4322; .NET CLR 2.0.50727; InfoPath.1; .NET CLR 3.0.04506.30; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)" );
            
            
           
            DataInputStream inStream = new DataInputStream(connection.getInputStream());
            String inputLine;

            while ((inputLine = inStream.readLine()) != null) {
                pw.println(inputLine);
            }
            inStream.close();
        } catch (MalformedURLException me) {
            System.err.println("MalformedURLException: " + me);
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe);
        }

		}

		
		
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(request, response);
		System.out.println("In doPost..");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}

}
