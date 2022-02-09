package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.CrudInterface;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealsContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsServlet extends HttpServlet {
    private static String INSERT_OR_EDIT = "/addMeal.jsp";
    private static String LIST_MEAL = "/meals.jsp";
    CrudInterface dao = new MealsContainer();
    private static final Logger log = getLogger(MealsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        log.debug("redirect to meals");

        String forward = "";
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
            req.setAttribute("ml", dao.getAllMealsTo());
            forward = LIST_MEAL;
        } else if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(req.getParameter("mealId"));
            dao.delete(mealId);
            resp.sendRedirect("meals");
        } else if (action.equalsIgnoreCase("edit")) {
            int mealId = Integer.parseInt(req.getParameter("mealId"));
            Meal meal = dao.getMealById(mealId);
            forward = INSERT_OR_EDIT;
            req.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("insert")) {
            forward = INSERT_OR_EDIT;
            req.setAttribute("meal", null);
        } else {
            req.setAttribute("ml", dao.getAllMealsTo());
            forward = LIST_MEAL;
        }
        if (!action.equalsIgnoreCase("delete"))
        req.getRequestDispatcher(forward).forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        System.out.println("method doPost");

        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("date"));
        req.setCharacterEncoding("UTF-8");
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        String mealId = req.getParameter("mealId");
        if (mealId == null || mealId.isEmpty()) {
            Meal meal = new Meal(MealsContainer.count++, localDateTime, description, calories);
            dao.add(meal);
        } else {
            int id = Integer.parseInt(mealId);
            Meal meal = dao.getMealById(id);
            meal.setCalories(calories);
            meal.setDateTime(localDateTime);
            meal.setDescription(description);
            dao.update(meal);
        }
        req.setAttribute("ml", dao.getAllMealsTo());
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
