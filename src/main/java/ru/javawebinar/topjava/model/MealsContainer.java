package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.MealsUtil.createTo;

public class MealsContainer implements CrudInterface{

//    private static MealsContainer mealsContainer;
//
//    private MealsContainer() {
//    }
//
//    public static MealsContainer getContainer() {
//        if (mealsContainer == null) {
//            mealsContainer = new MealsContainer();
//        }
//        return mealsContainer;
//    }

    public static int count = 1;

    static List<Meal> meals = new ArrayList<Meal>(
            Arrays.asList(
                    new Meal(count++, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                    new Meal(count++, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                    new Meal(count++, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                    new Meal(count++, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                    new Meal(count++, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                    new Meal(count++, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                    new Meal(count++, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)));

    public static List<MealTo> mealTos(List<Meal> meals) {
        int caloriesPerDay = 2000;
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
                );

        return meals.stream()
                .map(meal -> createTo(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public void delete(int id) {
        meals.removeIf(meal -> meal.getId() == id);
    }

    public List<MealTo> getAllMealsTo() {
        return MealsContainer.mealTos(meals);
    }

    public Meal getMealById(int id) {
        return meals.stream().filter(meal -> meal.getId() == id).findFirst().orElse(null);
    }

    public void add(Meal meal) {
        meals.add(meal);
    }

    public void update(Meal meal) {
        for (Meal mealUpd : meals) {
            if (mealUpd.getId().equals(meal.getId()))
               meals.set(meals.indexOf(mealUpd), meal);
        }
    }
}
