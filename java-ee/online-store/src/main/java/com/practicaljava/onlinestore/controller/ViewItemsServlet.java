package com.practicaljava.onlinestore.controller;

import com.practicaljava.onlinestore.service.ItemService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/items")
public class ViewItemsServlet extends HttpServlet {

    @EJB
    private ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
                                                                                          ServletException {

        request.setAttribute("items", itemService.listItems());

        request.getRequestDispatcher("/view/items.jspx").forward(request, response);
    }
}
