package com.example.smallgame.game.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer gain;
    private Integer cost;

    @Override
    public String toString() {
        return "{" +
                "name='" + getName() + "'" +
                ", gain='" + getGain() + "'" +
                ", cost='" + getCost() + "'" +
                "}";
    }

    public Integer getCost() {
        return this.cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGain() {
        return this.gain;
    }

    public void setGain(Integer gain) {
        this.gain= gain;
    }
}