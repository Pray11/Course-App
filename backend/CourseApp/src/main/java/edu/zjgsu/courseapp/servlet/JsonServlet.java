package edu.zjgsu.courseapp.servlet;

import edu.zjgsu.courseapp.utils.NetworkUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input = NetworkUtility.exhaustBufferedReader(req.getReader());
        System.out.println(input + "lol");
    }
}
