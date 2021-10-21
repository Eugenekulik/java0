package by.training.task8.controller;

import by.training.task8.bean.Gem;
import by.training.task8.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Set;

@WebServlet(urlPatterns = "/XMLServlet")
@MultipartConfig
public class XMLServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(XMLServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("") + File.separator;
        File uploadDir = new File(uploadPath);
        response.getWriter().println("<html><h1>");
        if (!uploadDir.exists()) uploadDir.mkdir();
        String file = null;
        String parsertype = request.getParameter("parserType");
        for (Part part : request.getParts()) {
            if (part.getName().equals("file")) {
                String fileName = getFileName(part);
                file = uploadPath + fileName;
                part.write(uploadPath + File.separator + fileName);
            }
        }
        XMLGemBuilder builder = null;
        switch (parsertype){
            case "dom":
                builder = new GemDomBuilder();
                break;
            case "sax":
                builder = new GemSaxBuilder();
                break;
            case "stax":
                builder = new GemStaxBuilder();
                break;
        }

        if (file != null) {
            try {
                builder.buildSetGems(file);
            } catch (ServiceException e) {

            }
            Set<Gem> gemSet = builder.getGems();
            response.getWriter().println("<table>" +
                    "<tr>" +
                    "<th>Name</th>" +
                    "<th>ID</th>" +
                    "<th>Preciousness</th>" +
                    "<th>Value</th>" +
                    "<th>Date</th>" +
                    "<th>Color</th>" +
                    "<th>Facetedness</th>" +
                    "<th>Transparency</th>" +
                    "</tr>");
            for (Gem gem : gemSet) {
                response.getWriter().println("<tr>" +
                        "<td>" + gem.getName() + "</td>" +
                        "<td>" + gem.getId() + "</td>" +
                        "<td>" + gem.getPreciousness() + "</td>" +
                        "<td>" + gem.getValue() + "</td>" +
                        "<td>" + gem.getDate() + "</td>" +
                        "<td>" + gem.getColor() + "</td>" +
                        "<td>" + gem.getFacetedness() + "</td>" +
                        "<td>" + gem.getTransparency() + "</td>" +
                        "</tr>");
            }
        }
        response.getWriter().println("</h1></html>");
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return null;
    }
}
