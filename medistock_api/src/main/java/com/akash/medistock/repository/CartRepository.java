package com.akash.medistock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akash.medistock.pojo.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}