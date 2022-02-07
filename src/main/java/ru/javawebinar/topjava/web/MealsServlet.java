package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealsContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect to meals");

        MealsContainer.getContainer();

        String action = req.getParameter("action");
        if(action==null){
            action = "";
            req.setAttribute("ml", MealsContainer.getAllMealsTo());
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        } else if (action.equalsIgnoreCase("delete")){
            int mealId = Integer.parseInt(req.getParameter("mealId"));
            MealsContainer.delete(mealId);
            req.setAttribute("ml", MealsContainer.getAllMealsTo());
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("method doPost");
        super.doPost(req, resp);
    }

}
