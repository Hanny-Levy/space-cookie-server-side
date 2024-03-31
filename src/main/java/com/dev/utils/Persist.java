
package com.dev.utils;

import com.dev.objects.Recipe;
import com.dev.objects.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class Persist {

    private final SessionFactory sessionFactory;

    @Autowired
    public Persist(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public User getUserByUsername(String username) {
        User found;
        Session session = sessionFactory.openSession();
        found = (User) session.createQuery("FROM User WHERE username = :username")
                .setParameter("username", username)
                .uniqueResult();
        session.close();
        return found;
    }

    public void saveUser(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
    }

    public void addRecipeToFavorite(Recipe recipe) {
        Session session = sessionFactory.openSession();
        session.save(recipe);
        session.close();
    }


    public User getUserByUsernameAndToken(String username, String token) {
        User found;
        Session session = sessionFactory.openSession();
        found = (User) session.createQuery("FROM User WHERE username = :username " +
                        "AND token = :token")
                .setParameter("username", username)
                .setParameter("token", token)
                .uniqueResult();
        session.close();
        return found;
    }

    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> allUsers = session.createQuery("FROM User ").list();
        session.close();
        return allUsers;
    }

    public List<User> getAllUsersWithoutCurrentUser(String token) {
        Session session = sessionFactory.openSession();
        List<User> allUsers = session.createQuery("FROM User ").list();
        session.close();
        List<User> users= allUsers.stream().filter((user -> !Objects.equals(user.getToken(), token))).collect(Collectors.toList());
        return users;
    }
    public User getUserByToken(String token) {
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("From User WHERE token = :token")
                .setParameter("token", token)
                .uniqueResult();
        session.close();
        return user;
    }





    public List<Recipe> getFavoritesRecipes(String token) {
        //        User user = getUserByToken(token);
        Session session = sessionFactory.openSession();
        List <Recipe> recipes = session.createQuery("FROM Recipe WHERE user.token = :token")
                .setParameter("token", token).list();

        session.close();
        return recipes;
    }




}
