package org.example.repository;


import jakarta.annotation.PostConstruct;

abstract class ForexRepositoryBase implements ForexRepository{
    @PostConstruct
    abstract void init();
}
