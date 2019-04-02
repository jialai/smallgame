package com.example.smallgame.game.Dao;

import java.util.List;

import com.example.smallgame.game.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByName(String name);

}