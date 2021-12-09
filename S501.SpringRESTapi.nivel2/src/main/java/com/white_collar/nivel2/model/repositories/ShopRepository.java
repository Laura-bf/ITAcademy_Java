package com.white_collar.nivel2.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.white_collar.nivel2.model.domain.Shop;

@Repository
public interface ShopRepository extends CrudRepository<Shop, Integer>{

}
