package com.genielogiciel.gestiondevente.dao;

import com.genielogiciel.gestiondevente.domain.User;

import java.util.List;

public interface UserDAOInterface {

    Long create(User user);

    void update(User user);

    User findById(Long id);

    void delete(User user);

    List<User> findAll();

    User findByUsernameAndPassword(String username, String password);

}
