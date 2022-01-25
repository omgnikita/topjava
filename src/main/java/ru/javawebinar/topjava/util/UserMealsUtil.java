package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMeal> filteredList = new ArrayList<>();
        for (UserMeal currentMeal : meals){
            if (TimeUtil.isBetweenHalfOpen(currentMeal.getDateTime().toLocalTime(), startTime, endTime)){
                filteredList.add(currentMeal);
            }
        }

                Map<LocalDate, Integer> valueCaloriesForDate = new HashMap<>();
        for (UserMeal currentMeal : filteredList){
            valueCaloriesForDate.merge(currentMeal.getDateTime().toLocalDate(), currentMeal.getCalories(), Integer::sum);
        }

        List<UserMealWithExcess> filteredListWithExcess = new ArrayList<>();
        for (UserMeal currentMeal : filteredList){
          if (valueCaloriesForDate.get(currentMeal.getDateTime().toLocalDate())>caloriesPerDay){
              filteredListWithExcess.add(new UserMealWithExcess(currentMeal.getDateTime(), currentMeal.getDescription(), currentMeal.getCalories(), true));
          } else{
              filteredListWithExcess.add(new UserMealWithExcess(currentMeal.getDateTime(), currentMeal.getDescription(), currentMeal.getCalories(), false));
          }
        }
        return filteredListWithExcess;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        return null;
    }
}
