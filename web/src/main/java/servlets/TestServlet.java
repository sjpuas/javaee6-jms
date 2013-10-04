package servlets;

import batchMQ.TestMQ;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Bennu Ltda.
 * User: Sergio Puas
 * Date: 04-10-13
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {

    @EJB
    private TestMQ testMQ;

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        testMQ.sendMessage(request.getParameter("msg"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(request.getParameter("msg"));
        out.close();
    }

}
