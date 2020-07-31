package p1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class HelloworldPostgres
 */
@WebServlet("/HelloworldPostgres")
public class HelloworldPostgres extends HttpServlet {
	private static final long serialVersionUID = 1L;

	  PreparedStatement pstmt = null;
      ResultSet rset=null;
      boolean found=false;
      Connection connection = null;
    /**
     * Default constructor. 
     */
    public HelloworldPostgres() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
System.out.println("-----------get mehtod-----------------------");
		
		
		/*String jdbcClassName="com.ibm.db2.jcc.DB2Driver";
        String url="jdbc:db2://10.10.2.190:50000/EMP";
        String user="db2inst1";
        String password="password";*/
		
		
        System.out.println("-----------after db properties----------------");
      
        try {
        	Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:jboss/datasources/PostgresDS");
            connection = ds.getConnection();
            //Load class into memory
        	System.out.println("-----------inside try----------------");
           //Class.forName(jdbcClassName);
           System.out.println("-----------after class.forName----------------");
           //Establish connection
           //connection = DriverManager.getConnection(url, user, password);
        	if (connection != null)
            {
            	System.out.println("DB2 Database Connected");
            	
            }
            else
            {
            	System.out.println("Db2 connection Failed ");
            }
            pstmt=connection.prepareStatement("Select * from customer");
            rset=pstmt.executeQuery();
            if(rset!=null)
            {
            while(rset.next())
             {
            	found=true;
            	System.out.println("Cust ID: "+rset.getString("custid"));
            	System.out.println("Name: "+rset.getString("fullname"));
            	System.out.println("phone: "+rset.getString("phone"));
            	System.out.println("------------------------------------");
             }
            }
            if (found ==false)
        	{
        	System.out.println("No Information Found");
        	}
   } catch (SQLException e) {
        e.printStackTrace();
   }
    catch (Exception e){
    	System.out.println("Exception "+e);
    }	
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
