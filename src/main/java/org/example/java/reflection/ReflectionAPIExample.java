package org.example.java.reflection;

import java.lang.reflect.*;

public class ReflectionAPIExample {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        User user = new User(25L, "Ivan");

        // Constructor
        Constructor<? extends User> constructor = user.getClass().getConstructor(Long.class, String.class);
        User testConstructor = constructor.newInstance(32L, "Test");
        System.out.println("testConstructor:" + testConstructor);
        System.out.println();

        // Fields
        Field[] fields = user.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);  // для доступа к приватным полям
            System.out.println("fields: " + field.getModifiers());  // возвращает число
            System.out.println("is field private?: " + Modifier.isPrivate(field.getModifiers()));
            System.out.println("fields: " + field.get(user));
            field.set(user,"Sveta");    // меняем приватное поле
            System.out.println(user);
        }
        System.out.println();

        // Methods
        Method getName = user.getClass().getDeclaredMethod("getName");
        System.out.println(getName.invoke(user));

        Method setName = user.getClass().getDeclaredMethod("setName", String.class);
        setName.invoke(user, "Nikolay");
        System.out.println(user);
    }
}
