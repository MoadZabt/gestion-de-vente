package com.genielogiciel.gestiondevente.service;

import com.genielogiciel.gestiondevente.dao.UserDAO;
import com.genielogiciel.gestiondevente.domain.User;
import jakarta.ejb.Stateless;

import java.io.Serializable;
import java.util.List;

@Stateless
public class UserService implements Serializable {
    private static UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public Long create(User user) {
        userDAO.openCurrentSessionWithTransaction();
        Long userCreatedId = userDAO.create(user);
        userDAO.closeCurrentSessionWithTransaction();
        return userCreatedId;
    }

    public User findByUsernameAndPassword(String username, String password) {
        userDAO.openCurrentSessionWithTransaction();
        User user = userDAO.findByUsernameAndPassword(username, password);
        userDAO.closeCurrentSessionWithTransaction();
        return user;
    }

    public List<User> findAll() {
        userDAO.openCurrentSessionWithTransaction();
        List<User> allUsers = userDAO.findAll();
        userDAO.closeCurrentSessionWithTransaction();
        return allUsers;
    }

    public User findById(Long id) {
        userDAO.openCurrentSessionWithTransaction();
        User users = userDAO.findById(id);
        userDAO.closeCurrentSessionWithTransaction();
        return users;
    }
}
