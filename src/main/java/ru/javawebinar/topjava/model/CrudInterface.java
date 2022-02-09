package ru.javawebinar.topjava.model;

import java.util.List;

public interface CrudInterface {
    public void add(Meal meal);

    public void update(Meal meal);

    public  void delete(int id);

    public  List<MealTo> getAllMealsTo();

    public Meal getMealById(int id);
}
