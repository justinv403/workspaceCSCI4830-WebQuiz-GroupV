

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Servlet implementation class QuizProcessorServlet
 */
@WebServlet("/QuizProcessorServlet")
public class QuizProcessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// MySQL Database Information (get from text file)
	private String url = "";
	private String user = "";
	private String password = "";
	private Connection conn = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizProcessorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// automatically redirect to doPost
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get decided action from HTML page
		String action = request.getParameter("action");
		System.out.println("Action = " + action);
		
		// get url, user, and password
		getLoginInfo();
		
		// connect to the SQL database
		conn = connectSQL(url, user, password);
		if(conn == null) {
			cleanClose("QuizLanding.html?SQLFail=true", conn, response);
			return;
		}
		
		// determine action, then attempt action
		if("submit".equals(action) && conn != null) {
			// print action
			System.out.println("Action = " + action);
			
			// get the answers from the User
			ArrayList<Integer> answerResults = new ArrayList<Integer>();
			answerResults = getAnswers(request);
			System.out.println("Answers = " + answerResults);
			
			
			// get the user's score for the quiz
			int totalScore = 0;
			for(int i=0; i<answerResults.size(); i++) {
				totalScore += answerResults.get(i);
			}
			System.out.println("Total Score = " + totalScore);
			
			
			// if SQL database connection is successful, then process the answers
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String quizName = request.getParameter("quizName");
			submitDatabaseAction(conn, firstName, lastName, totalScore, quizName);
			
			
			// display results
			ResultSet rs = null;
			rs = viewDatabaseAction(conn, quizName);
			drawHTMLTablePage(response, quizName, rs);
			
			
			//cleanClose("NONE", conn, response);
			
			
			
		} else if("view".equals(action) && conn != null) {
			// print action
			System.out.println("Action = " + action);
			
			// if SQL database connection is successful, display SQL results to user
			ResultSet rs = null;
			String quizName = request.getParameter("quizName");
			rs = viewDatabaseAction(conn, quizName);
			drawHTMLTablePage(response, quizName, rs);
		}
		
		
		cleanClose("NONE", conn, response);
		
	}
	
	// Get answers from HTML page
	// takes Parameters from web page labeled integer0, integer1, etc...)
	ArrayList<Integer> getAnswers(HttpServletRequest request) throws ServletException, IOException {
		
		int i = 0;
		ArrayList<Integer> answerResults = new ArrayList<Integer>();
		while (true) {
				String param = request.getParameter("integer" + i);
				if (param == null) {
					break;
				}
				answerResults.add(Integer.parseInt(param));
				i++;
				
				// emergency catch for infinte loop
				if(i > 100) {
					break;
				}
			}
		return answerResults;
	}
	
	// Connect to the MySQL Database
	Connection connectSQL(String url, String user, String password) {	
		// attempt servlet connection
		Connection conn = null;
	    try {
	    	// load MySQL JDBC driver
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	
	    	// make connection
	    	conn = DriverManager.getConnection(url, user, password);
	    	
	    	// log successful connection
	    	System.out.println("Successful MySQL Connection\nConnection ID = " + conn);
	    } catch (ClassNotFoundException | SQLException e) {
	        System.out.println("Unable to connect to database: " + e.getMessage());
	    }
	    return conn;
	}
	
	// Close the MySQL connection
	void closeSQL(Connection conn) {
		// try closing the connection
		if(conn != null) {
			try {
	            conn.close();
	        } catch (SQLException e) {
	            System.out.println("Failed to close connection: " + e.getMessage());
	        }
		}
	}
	
	// Submit data to the MySQL database
	void submitDatabaseAction(Connection conn, String firstName, String lastName, int totalScore, String quizName) {
	    PreparedStatement stmt = null;

	    if (conn != null && firstName.length() > 0 && lastName.length() > 0) {
	        try {
	            // Attempt to insert the result into the database
	            stmt = conn.prepareStatement("INSERT INTO " + quizName + " (firstName, lastName, score) VALUES (?, ?, ?)");
	            stmt.setString(1, firstName);
	            stmt.setString(2, lastName);
	            stmt.setInt(3, totalScore);
	            int rowsAffected = stmt.executeUpdate();
	            
	            // verify the number of rows affected by the changes
	            if (rowsAffected > 0) {
	                System.out.println("Data successfully inserted into the database.");
	            } else {
	                System.out.println("Failed to insert data into the database.");
	            }
	            
	        } catch (SQLException e) {
	            System.out.println("SQL error during data insertion: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	            try {
	            	if (stmt != null) {
	                    stmt.close();
	                }
	            } catch (SQLException e) {
	                System.out.println("Failed to close statement: " + e.getMessage());
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	// Display database information to the user on a generated HTML page
	ResultSet viewDatabaseAction(Connection conn, String quizName) {
	    ResultSet rs = null;
	    int topScoresDisplayed = 20;
	    try {
	    	// print status
	        System.out.println("Max of " + topScoresDisplayed + " top scores can be displayed");
	    	
	    	// Assuming there is a table named 'quiz1' for quiz results
	        String query = "SELECT * FROM " + quizName + " ORDER BY score DESC LIMIT " + topScoresDisplayed;
	        Statement stmt = conn.createStatement();
	        rs = stmt.executeQuery(query);
	        
	        // Count the number of rows in the ResultSet
	        int rowCount = 0;
	        while (rs.next()) {
	            rowCount++;
	        }

	        // Output the count to the console
	        System.out.println("Actual number of scores retrieved: " + rowCount);
	        
	        // Rewind the ResultSet to the beginning
	        rs.beforeFirst();
	        
	    } catch (SQLException e) {
	        System.out.println("SQL error during data retrieval: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return rs;
	}
	
	
	// Create the HTML page with the table for results
	void drawHTMLTablePage(HttpServletResponse response, String quizName, ResultSet rs) {
		// make sure rs is not null first
		if(rs != null) {
			try {
	        	PrintWriter out = response.getWriter();
	        	out.println("<html><head><style>"
	                	+ "table {width: 100%;border-collapse: collapse;margin: 15px 0;}"
	                	+ "th, td {border: 1px solid #999;padding: 0.5rem;text-align: left;}"
	                	+ "th {background-color: #f3f3f3;}"
	                	+ "</style></head><body><table>");
	        	out.println("<tr><th>First Name</th><th>Last Name</th><th>Total Score</th></tr>");
	        	while (rs.next()) {
	            	String fName = rs.getString("firstName");
	            	String lName = rs.getString("lastName");
	            	int totalScore = rs.getInt("score");

	            	out.println("<tr><td>" + fName + "</td><td>" + lName + "</td><td>" + totalScore + "</td></tr>");
	        	}
	        	out.println("</table><br>");
	        	out.println("<button style=\"font-size:1em;padding:10px;background-color:#4CAF50;color:white;border:none;border-radius:5px;cursor:pointer;\" onclick=\"location.href='QuizLanding.html'\">Menu</button>");
	        	out.println("</body></html>");
	    	} catch (Exception e) {
	        	System.out.println("Failed to create HTML table for MySQL results.");
	        	e.printStackTrace();
	    	}
		}
		
	}
	
	// Get the user login info from the text file
	void getLoginInfo() {
		try {
	        // open inputstream as a Java classpath (better portability)
			InputStream is = getClass().getClassLoader().getResourceAsStream("pass.txt");
	        
			// scan the lines of the file to get the input
			Scanner scanner = new Scanner(is);
	        url = scanner.nextLine();
	        user = scanner.nextLine();
	        password = scanner.nextLine();
	        
	        // close the file scanner
	        scanner.close();
	    
		} catch (Exception e) {
	        System.out.println("Failed to get login info. See below for more information.");
			e.printStackTrace();
	    }
	}
	
	// Cleanly close the program, make sure everything is cleaned up
	// "NONE" may be used to prevent redirection if necessary
	void cleanClose(String redirectionLocation, Connection conn, HttpServletResponse response) {
	    try {
	    	// attempt to close all used components
	    	closeSQL(conn);
	    	if(redirectionLocation != "NONE") {
	    		response.sendRedirect(redirectionLocation);
	    	}
	    	System.out.println("Clean close successful. Redirecting to: " + redirectionLocation);
	    } catch (IOException e) {
	        // log the exception
	    	System.out.println("Failed to close cleanly. See below for more information.");
	    	e.printStackTrace();
	    }
	    return;
	}

}
