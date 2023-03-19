package org.backendmealplan.backendmealplan.bl;
import org.backendmealplan.backendmealplan.beans.*;
import org.backendmealplan.backendmealplan.dao.PlansDAO;
import org.backendmealplan.backendmealplan.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InitDataBL {
    @Autowired
    UserBL userBL;

    @Autowired
    PlanBL planBL;

    @Autowired
    GoalBL goalBL;

    @Autowired
    RecipeBL recipeBL;

    @Autowired
    MealBL mealBL;

    @Autowired
    IngredientBL ingredientBL;

    Ingredient[] ingredients = new Ingredient[52];
    Meal[] meals = new Meal[10];
    Plan freemuimPlan, basicPlan, premiumPlan;

    DayPlanId[] dayPlanIds = new DayPlanId[28];

    @Autowired
    PlansDAO plansDAO;

    public void run() {
        createGoals();
        createIngredients();
        createMeals();
        createMealIngredients();
        createPlans();
        createDays();
        createDayMeals();
        createDayPlan();
    }

    private void createDays() {
        for (int i = 0; i < dayPlanIds.length; i++) {
            dayPlanIds[i] = insertDays();
        }
    }

    private DayPlanId insertDays() {
        DayPlanId dayPlanId1 = new DayPlanId();
        return planBL.addDayPlanId(dayPlanId1);
    }

    private void createPlans() {
        freemuimPlan = insertPlan(PlanType.Freemium.name(), "0", 0, new ArrayList<>(), new ArrayList<>());
        List<String> includes = new ArrayList<>();
        List<String> benefits = new ArrayList<>();
        includes.add("14 Breakfasts");
        includes.add("14 Lunches");
        includes.add("14 Dinners");
        includes.add("14 Snacks");
        benefits.add("Build healthier habits");
        benefits.add("Introduce new recipes into you rotation");
        benefits.add("Eat AND enjoy the foods you're preparing&Work towards a lifestyle shift");
        basicPlan = insertPlan(PlanType.Basic.name(), "14", 29, includes, benefits);

        includes = new ArrayList<>();
        benefits = new ArrayList<>();
        includes.add("28 Breakfasts");
        includes.add("28 Lunches");
        includes.add("28 Dinners");
        includes.add("28 Snacks");
        benefits.add("Become a pro at managing your health");
        benefits.add("Build habits that actually stick");
        benefits.add("Prepare foods you'll never get sick of");
        benefits.add("Bulk up your recipe rotation with over 30 delicious and healthy recipes");
        premiumPlan = insertPlan(PlanType.Premium.name(), "28", 34, includes, benefits);
    }

    private Plan insertPlan(String planName, String length, double price, List<String> includes, List<String> benefits) {
        Plan plan = new Plan();
        plan.setPlanName(planName);
        plan.setPrice(price);
        plan.setLength(length);
        plan.setIncludes("<ul>" + includes.stream().map(include -> "<li class=\"secondary-font\">" + "<span class=\"primary-color bullet\">&#8226;</span>" + "<span>" + include + "</span>" + "</li>").collect(Collectors.joining()) + "</ul>");
        plan.setBenefits("<ul>" + benefits.stream().map(benefit -> "<li class=\"secondary-font\">" + "<span class=\"primary-color bullet\">&#8226;</span>" + "<span>" + benefit + "</span>" + "</li>").collect(Collectors.joining()) + "</ul>");
        return planBL.addPlan(plan);
    }

    private void createIngredients() {

        //breakfast1
        ingredients[0] = insertIngredient("unsweetened almond milk", FoodCategories.Dairy.name());
        ingredients[1] = insertIngredient("peanut butter", FoodCategories.Dairy.name());
        ingredients[2] = insertIngredient("chia seeds", FoodCategories.Others.name());
        ingredients[3] = insertIngredient("bananas – sliced", FoodCategories.Fruit.name());

        //breakfast2
        ingredients[19] = insertIngredient("grated carrot", FoodCategories.Vegetables.name());
        ingredients[20] = insertIngredient("a big pinch of mixed spice", FoodCategories.Others.name());
        ingredients[21] = insertIngredient("cinnamon", FoodCategories.Others.name());
        ingredients[22] = insertIngredient("rolled porridge oats", FoodCategories.Others.name());
        ingredients[23] = insertIngredient("honey", FoodCategories.Others.name());
        ingredients[24] = insertIngredient("sultanas", FoodCategories.Others.name());
        ingredients[25] = insertIngredient("Greek yogurt", FoodCategories.Dairy.name());

        //breakfast3
        ingredients[36] = insertIngredient("porridge oats", FoodCategories.Others.name());
        ingredients[37] = insertIngredient("milled seeds with flax and chia", FoodCategories.Others.name());
        ingredients[38] = insertIngredient("milk", FoodCategories.Dairy.name());
        ingredients[39] = insertIngredient("coconut yogurt", FoodCategories.Dairy.name());
        ingredients[40] = insertIngredient("toasted flaked almonds", FoodCategories.Others.name());
        ingredients[41] = insertIngredient("Mixed berries", FoodCategories.Fruit.name());

        //lunch1
        ingredients[4] = insertIngredient("smoked salmon", FoodCategories.Meat.name());
        ingredients[5] = insertIngredient("Philadelphia cream cheese", FoodCategories.Dairy.name());
        ingredients[6] = insertIngredient("capers", FoodCategories.Fruit.name());
        ingredients[7] = insertIngredient("spring onions", FoodCategories.Vegetables.name());
        ingredients[8] = insertIngredient("cucumbers", FoodCategories.Vegetables.name());
        ingredients[9] = insertIngredient("multi-grain bread", FoodCategories.Others.name());
        ingredients[10] = insertIngredient("lettuce", FoodCategories.Vegetables.name());
        //lunch2
        ingredients[26] = insertIngredient("Chicken Breast, Cooked (shredded or chopped)", FoodCategories.Meat.name());
        ingredients[27] = insertIngredient("Cream Cheese, Regular (divided)", FoodCategories.Dairy.name());
        ingredients[28] = insertIngredient("Whole Wheat Tortilla", FoodCategories.Others.name());
        ingredients[29] = insertIngredient("Arugula", FoodCategories.Others.name());
        ingredients[30] = insertIngredient("stalks Celery (chopped)", FoodCategories.Others.name());

        //lunch3
        ingredients[42] = insertIngredient("olive oil", FoodCategories.Others.name());
        ingredients[43] = insertIngredient("red onion, chopped", FoodCategories.Vegetables.name());
        ingredients[44] = insertIngredient("garlic cloves, chopped", FoodCategories.Vegetables.name());
        ingredients[45] = insertIngredient("large carrots", FoodCategories.Vegetables.name());
        ingredients[46] = insertIngredient("large parsnip", FoodCategories.Vegetables.name());
        ingredients[47] = insertIngredient("low sodium vegetable stock cube", FoodCategories.Vegetables.name());
        ingredients[48] = insertIngredient("milk", FoodCategories.Dairy.name());

        //dinner1
        ingredients[11] = insertIngredient("Sweet Potato", FoodCategories.Vegetables.name());
        ingredients[12] = insertIngredient("Broccoli", FoodCategories.Vegetables.name());
        ingredients[13] = insertIngredient("Extra Virgin Olive Oil", FoodCategories.Vegetables.name());
        ingredients[14] = insertIngredient("Sea Salt", FoodCategories.Vegetables.name());
        ingredients[15] = insertIngredient("Lemon Juice", FoodCategories.Vegetables.name());
        ingredients[16] = insertIngredient("Garlic(clove, minced)", FoodCategories.Vegetables.name());
        ingredients[17] = insertIngredient("Parsley (finely chopped)", FoodCategories.Vegetables.name());
        ingredients[18] = insertIngredient("Salmon Fillet", FoodCategories.Meat.name());


        //dinner3
        ingredients[31] = insertIngredient("wild or brown Rice (dry, uncooked)", FoodCategories.Others.name());
        ingredients[32] = insertIngredient("chicken breast (cubed)", FoodCategories.Meat.name());
        ingredients[33] = insertIngredient("Sea Salt & Black Pepper (to taste)", FoodCategories.Others.name());
        ingredients[34] = insertIngredient("large Eggs (whisked)", FoodCategories.Others.name());
        ingredients[35] = insertIngredient("Frozen Vegetable Mix", FoodCategories.Vegetables.name());

        //snack1
        ingredients[49] = insertIngredient("apple", FoodCategories.Fruit.name());
        ingredients[50] = insertIngredient("sugar salt free peanut butter", FoodCategories.Others.name());

        //snack2
        ingredients[51] = insertIngredient("chickpeas", FoodCategories.Vegetables.name());
    }

    private Ingredient insertIngredient(String ingredientName, String categoryOfFood) {
        Ingredient ingredient = new Ingredient();
        ingredient.setProductName(ingredientName);
        ingredient.setCategory(categoryOfFood);
        return ingredientBL.addIngredient(ingredient);
    }


    private void createGoals() {
        for (GoalType goalType : GoalType.values()) {
            Goal goal = new Goal();
            goal.setText(goalType.getValue());
            goalBL.addGoal(goal);
        }
    }


    private void createMeals() {
        List<String> instructions = new ArrayList<>();
        List<String> tips = new ArrayList<>();
        instructions.add("Add the milk, peanut butter, to a jar and shake well until the peanut butter has been incorporated with the milk. " +
                "Add the chia seeds and shake again to combine. Add the chia seeds and shake again to combine." +
                " The next morning, stir the muesli well. If it’s too thick, add a little more water until it reaches your preferred consistency.");
        instructions.add("Refrigerate for at least three hours or until chilled.");
        instructions.add("To serve, divide the chia pudding between bowls and top with the sliced banana.");
        tips.add("Want more flavour : Add cinnamon, sea salt, and/or vanilla extract.");
        tips.add("Want additional toppings : Berries, honey, or extra peanut butter");
        meals[0] = insertMeal(
                "Peanut butter and banana chia pudding",
                300, 15, 10, 9, 25,
                "assets/images/foods/meat-courses/1/medium.jpg", instructions,
                "10 minutes",
                "3 hours", tips);

        instructions = new ArrayList<>();
        instructions.add("Mix the grated carrot, mixed spice, cinnamon, and oats");
        instructions.add("Add 2⁄3 cup (X4) water and a pinch of salt");
        instructions.add("Place in 4 serving jars and cover");
        instructions.add("Place in fridge overnight");
        instructions.add("Add honey and sultanas and Greek yogurt in the morning");
        meals[1] = insertMeal("Carrot cake overnight oats",
                319, 9, 9, 6, 29,
                "assets/images/foods/meat-courses/1/medium.jpg", instructions,
                "5 minutes", "Overnight",  new ArrayList<>());

        instructions = new ArrayList<>();
        instructions.add("Soak the oats and chia seeds in 800ml water overnight");
        instructions.add("Add the oats and seeds to a pan the next morning with milk");
        instructions.add("Place in 4 bowls and serve with yogurt and fruit");
        meals[2] = insertMeal("Oat and Chia porridge",
                370, 19, 11, 8, 34,
                "assets/images/foods/meat-courses/1/medium.jpg", instructions, "5 minutes", "Overnight", new ArrayList<>());

        instructions = new ArrayList<>();
        instructions.add("Cut the slices of salmon into small pieces.");
        instructions.add("In a small mixing bowl combine salmon with cream cheese, chopped capers, finely diced spring onion and diced cucumber, mix well.");
        instructions.add("Place mixture onto half the slices of bread, top with lettuce and remaining bread slices.");
        meals[3] = insertMeal("Smoked salmon sandwich",
                667, 6.6, 5.6, 2, 10.5,
                "assets/images/foods/meat-courses/1/medium.jpg", instructions, "10 minutes", "", new ArrayList<>());

        instructions = new ArrayList<>();
        instructions.add("In a small bowl, mix together the chicken and half the cream cheese.");
        instructions.add("Lay the tortilla flat and spread the remaining cream cheese, then add the arugula, chicken, and celery. Roll the tortilla tightly and enjoy!");
        meals[4] = insertMeal("Chicken and cream cheese wrap",
                151, 3.5, 3.4, 7, 23,
                "assets/images/foods/meat-courses/1/medium.jpg", instructions, "5 minutes", "Overnight", new ArrayList<>());

        instructions = new ArrayList<>();
        instructions.add("Heat the oil in a saucepan and fry the onion until softened");
        instructions.add("Add the garlic and cook for 2mins");
        instructions.add("Add the carrots, parsnip, and stock to the saucepan");
        instructions.add("Bring to boil and then reduce and simmer for 20mins");
        instructions.add("Add the milk");
        instructions.add("Blend until smooth");
        meals[5] = insertMeal("Carrot and parsnip soup",
                400, 12, 12, 4, 33,
                "assets/images/foods/meat-courses/1/medium.jpg", instructions,
                "10 minutes", "20 minutes", new ArrayList<>());

        instructions = new ArrayList<>();
        tips = new ArrayList<>();
        instructions.add("Preheat the oven to 375oF and line a baking sheet with parchment paper.");
        instructions.add("Add the sweet potato and broccoli to the pan and drizzle with 2⁄3 of the oil and season with half of the salt. Stir to evenly coat the vegetables and bake for 10 minutes.");
        instructions.add("the salt. Stir to evenly coat the vegetables and bake for 10 minutes.");
        instructions.add("Meanwhile, in a small bowl combine the remaining oil, lemon juice, garlic, and parsley.");
        instructions.add("Remove the pan from the oven. Stir the vegetables and make room for the salmon in the centre of the pan.");
        instructions.add("Place the salmon on the pan and season with the remaining salt. Spoon the lemon garlic sauce over top of the fillets. " +
                "Continue to bake for 15 minutes or until the salmon is cooked through and the vegetables are tender. Divide between plates and enjoy!");
        tips.add("Optional : Add cauliflower, Brussels sprouts, cabbage, zucchini, or bell pepper");
        meals[6] = insertMeal("Lemon garlic salmon, broccoli and sweet potatoes",
                450, 20, 40, 6, 30,
                "assets/images/foods/meat-courses/1/medium.jpg", instructions,
                "10 minutes", "15 minutes", tips);

        instructions = new ArrayList<>();
        instructions.add("Cook the rice according to the package instructions and set aside.");
        instructions.add("Heat a large non-stick pan over medium-high heat. Add the cubed chicken and season with salt and pepper. Cook to your desired doneness, then transfer to a bowl.");
        instructions.add("Add the eggs to the same pan and stir to scramble as it cooks, about two to three minutes.");
        instructions.add("Push the eggs to the side of the pan, and add the frozen vegetables. Season with salt and pepper and cook until warmed through, about three minutes.");
        instructions.add("Add the rice and cooked chicken. Stir until well combined and season with additional salt and pepper if needed. Divide into bowls and enjoy!");
        meals[7] = insertMeal("Chicken fried rice",
                450, 15, 30, 5, 40,
                "assets/images/foods/meat-courses/1/medium.jpg", instructions, "10 minutes", "15 minutes", new ArrayList<>());

        instructions = new ArrayList<>();
        instructions.add("1 apple cut into wedges dipped into 1⁄3 oz sugar salt free peanut butter");
        meals[8] = insertMeal("apple and peanut butter",
                159, 21, 2.6, 3, 7,
                "assets/images/foods/meat-courses/1/medium.jpg",instructions,"","",new ArrayList<>());

        meals[9] = insertMeal("Roasted chickpeas",
                157, 16.1, 4.1, 7.2, 7.1,
                "assets/images/foods/meat-courses/1/medium.jpg",new ArrayList<>(),"30 minutes","30 minutes",new ArrayList<>());
    }

    private Meal insertMeal(String MealName, int calories, double fat, double protein, double fibre, double carbs, String imageUrl, List<String> instructions, String prepareTime, String cookTime, List<String> tips) {
        Meal meal = new Meal();
        meal.setMealName(MealName);
        meal.setCalories(calories);
        meal.setCarbs(carbs);
        meal.setFibre(fibre);
        meal.setProtein(protein);
        meal.setFat(fat);
        meal.setImageUrl(imageUrl);
        meal.setInstructions("<ul>" + instructions.stream().map(instruction -> "<li>" + instruction + "</li>").collect(Collectors.joining()) + "</ul>");
        meal.setPrepareTime(prepareTime);
        meal.setCookTime(cookTime);
        meal.setTips("<ul>" + tips.stream().map(tip -> "<li>" + tip + "</li>").collect(Collectors.joining()) + "</ul>");
        return mealBL.addMeal(meal);
    }

    private void createMealIngredients() {
        //breakfast1
        insertMealIngredients(meals[0], ingredients[0], Optional.of(4.0), Optional.of(Unit.cup.name()));
        insertMealIngredients(meals[0], ingredients[1], Optional.of(4.5), Optional.of(Unit.oz.name()));
        insertMealIngredients(meals[0], ingredients[2], Optional.of(4.25), Optional.of(Unit.cup.name()));
        insertMealIngredients(meals[0], ingredients[3], Optional.of(4.0), Optional.empty());
        //breakfast2
        insertMealIngredients(meals[1], ingredients[19], Optional.of(5.6), Optional.of(Unit.oz.name()));
        insertMealIngredients(meals[1], ingredients[20], Optional.empty(), Optional.empty());
        insertMealIngredients(meals[1], ingredients[21], Optional.of(0.25), Optional.of(Unit.teaspoon.name()));
        insertMealIngredients(meals[1], ingredients[22], Optional.of(7.0), Optional.of(Unit.oz.name()));
        insertMealIngredients(meals[1], ingredients[23], Optional.of(1.0), Optional.of(Unit.teaspoon.name()));
        insertMealIngredients(meals[1], ingredients[24], Optional.of(1.0), Optional.of(Unit.teaspoon.name()));
        insertMealIngredients(meals[1], ingredients[25], Optional.of(1.0), Optional.of(Unit.tablespoon.name()));
        //breakfast3
        insertMealIngredients(meals[2], ingredients[36], Optional.of(5.3), Optional.of(Unit.oz.name()));
        insertMealIngredients(meals[2], ingredients[37], Optional.of(1.8), Optional.empty());
        insertMealIngredients(meals[2], ingredients[38], Optional.of(1.6667), Optional.of(Unit.cup.name()));
        insertMealIngredients(meals[2], ingredients[39], Optional.of(7.0), Optional.of(Unit.oz.name()));
        insertMealIngredients(meals[2], ingredients[40], Optional.of(1.4), Optional.of(Unit.oz.name()));
        insertMealIngredients(meals[2], ingredients[41], Optional.empty(), Optional.empty());
        //lunch1
        insertMealIngredients(meals[3], ingredients[4], Optional.of(7.0), Optional.of(Unit.oz.name()));
        insertMealIngredients(meals[3], ingredients[5], Optional.of(4.0), Optional.of(Unit.tablespoon.name()));
        insertMealIngredients(meals[3], ingredients[6], Optional.of(20.0), Optional.empty());
        insertMealIngredients(meals[3], ingredients[7], Optional.of(4.0), Optional.of(Unit.tablespoon.name()));
        insertMealIngredients(meals[3], ingredients[8], Optional.of(0.5), Optional.of(Unit.cup.name()));
        insertMealIngredients(meals[3], ingredients[9], Optional.of(8.0), Optional.of(Unit.slice.name()));
        insertMealIngredients(meals[3], ingredients[10], Optional.of(2.0), Optional.of(Unit.cup.name()));

        //lunch2
        insertMealIngredients(meals[4], ingredients[26], Optional.of(12.0), Optional.of(Unit.oz.name()));
        insertMealIngredients(meals[4], ingredients[27], Optional.of(4.2), Optional.of(Unit.oz.name()));
        insertMealIngredients(meals[4], ingredients[28], Optional.of(4.0), Optional.empty());
        insertMealIngredients(meals[4], ingredients[29], Optional.of(2.5), Optional.of(Unit.cup.name()));
        insertMealIngredients(meals[4], ingredients[30], Optional.of(4.0), Optional.empty());

        //lunch3
        insertMealIngredients(meals[5], ingredients[42], Optional.of(1.0), Optional.of(Unit.tablespoon.name()));
        insertMealIngredients(meals[5], ingredients[43], Optional.of(1.0), Optional.empty());
        insertMealIngredients(meals[5], ingredients[44], Optional.of(2.0), Optional.empty());
        insertMealIngredients(meals[5], ingredients[45], Optional.of(3.0), Optional.empty());
        insertMealIngredients(meals[5], ingredients[46], Optional.of(2.0), Optional.empty());
        insertMealIngredients(meals[5], ingredients[47], Optional.of(1.0), Optional.empty());
//        insertMealIngredients(meals[5], ingredients[48], Optional.of(0.5), Optional.of(Unit.cup.name()));

        //dinner1
        insertMealIngredients(meals[6], ingredients[11], Optional.of(4.0), Optional.empty());
        insertMealIngredients(meals[6], ingredients[12], Optional.of(5.0), Optional.of(Unit.cup.name()));
        insertMealIngredients(meals[6], ingredients[13], Optional.of(3.0), Optional.of(Unit.tablespoon.name()));
        insertMealIngredients(meals[6], ingredients[14], Optional.empty(), Optional.empty());
        insertMealIngredients(meals[6], ingredients[15], Optional.of(2.0), Optional.of(Unit.tablespoon.name()));
        insertMealIngredients(meals[6], ingredients[16], Optional.of(2.0), Optional.empty());
        insertMealIngredients(meals[6], ingredients[17], Optional.of(2.0), Optional.of(Unit.tablespoon.name()));
        insertMealIngredients(meals[6], ingredients[18], Optional.of(24.0), Optional.of(Unit.oz.name()));

        //dinner2
        insertMealIngredients(meals[7], ingredients[31], Optional.of(0.667), Optional.of(Unit.cup.name()));
        insertMealIngredients(meals[7], ingredients[32], Optional.of(16.0), Optional.of(Unit.oz.name()));
        insertMealIngredients(meals[7], ingredients[33], Optional.empty(), Optional.empty());
        insertMealIngredients(meals[7], ingredients[34], Optional.of(3.0), Optional.empty());
        insertMealIngredients(meals[7], ingredients[35], Optional.of(2.5), Optional.of(Unit.cup.name()));

        //snack1
        insertMealIngredients(meals[8],ingredients[49],Optional.of(1.0),Optional.empty());
        insertMealIngredients(meals[8],ingredients[50],Optional.of(0.333),Optional.of(Unit.oz.name()));

        //snack2
        insertMealIngredients(meals[9],ingredients[51],Optional.of(3.5),Optional.of(Unit.oz.name()));
        insertMealIngredients(meals[9],ingredients[42],Optional.of(1.0),Optional.of(Unit.teaspoon.name()));

    }

    private void insertMealIngredients(Meal meal, Ingredient ingredient, Optional<Double> amount, Optional<String> unit) {
        MealIngredients mealIngredients = new MealIngredients();
        if (amount.isPresent())
            mealIngredients.setAmount(amount.get());
        if (unit.isPresent())
            mealIngredients.setUnit(unit.get());
        mealIngredients.setId(new MealIngredientId(meal, ingredient));
        mealBL.addMealIngredients(mealIngredients);
    }

    private void insertDayMeals(Meal meal, DayPlanId dayPlanId, String type) {
        DayMeal dayMeals = new DayMeal();
        dayMeals.setId(new DayMealKey(meal, dayPlanId));
        dayMeals.setType(type);
        mealBL.addDayMeals(dayMeals);
    }

    private void createDayMeals() {
        insertDayMeals(meals[0], dayPlanIds[0], MealTime.Breakfast.name());
        insertDayMeals(meals[3], dayPlanIds[0], MealTime.Lunch.name());
        insertDayMeals(meals[6], dayPlanIds[0], MealTime.Dinner.name());
        insertDayMeals(meals[8], dayPlanIds[0], MealTime.Snacks.name());
        insertDayMeals(meals[9], dayPlanIds[0], MealTime.Snacks.name());

        insertDayMeals(meals[1], dayPlanIds[1], MealTime.Breakfast.name());
        insertDayMeals(meals[4], dayPlanIds[1], MealTime.Lunch.name());
        insertDayMeals(meals[7], dayPlanIds[1], MealTime.Dinner.name());
        insertDayMeals(meals[8], dayPlanIds[1], MealTime.Snacks.name());
        insertDayMeals(meals[9], dayPlanIds[1], MealTime.Snacks.name());

        insertDayMeals(meals[2], dayPlanIds[2], MealTime.Breakfast.name());
        insertDayMeals(meals[5], dayPlanIds[2], MealTime.Lunch.name());
        insertDayMeals(meals[8], dayPlanIds[2], MealTime.Snacks.name());
        insertDayMeals(meals[9], dayPlanIds[2], MealTime.Snacks.name());
    }

    private void createDayPlan() {
        insertDayPlan(basicPlan, dayPlanIds[0], 1);
        insertDayPlan(basicPlan, dayPlanIds[1], 2);
        insertDayPlan(basicPlan, dayPlanIds[2], 3);
    }

    private void insertDayPlan(Plan plan, DayPlanId dayPlanId, Integer dayNumber) {
        DayPlan dayPlan = new DayPlan();
        dayPlan.setDayPlanKey(new DayPlanKey(plan, dayPlanId));
        dayPlan.setDayNumber(dayNumber);
        planBL.addDayPlan(dayPlan);
        plan.getDayPlanIdList().add(dayPlanId);
    }
}
