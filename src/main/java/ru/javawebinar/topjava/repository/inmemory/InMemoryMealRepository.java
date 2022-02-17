package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        for (Meal meal : MealsUtil.meals) {
            save(meal, authUserId());
        }
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            repository.put(meal.getId(), meal);
            return meal;
        }
        if (repository.get(meal.getId()) == null || meal.getUserId() != userId) {
            return null;
        }
        return repository.replace(meal.getId(), meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        if (repository.get(id) == null || repository.get(id).getUserId() != userId) {
            return false;
        }
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        if (repository.get(id) == null || repository.get(id).getUserId() != userId) {
            return null;
        }
        return repository.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        List<Meal> mealListUser = new ArrayList<>();
        List<Meal> mealList = new ArrayList<>(repository.values());
        for (Meal meal : mealList){
            if (meal.getUserId().equals(userId)) mealListUser.add(meal);
        }
        Collections.sort(mealListUser);
        return mealListUser;
    }
}

