package com.cydeo.repository;

import com.cydeo.entity.Role;
import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    //build all queries that will bring data from db
    //you can use ready repository from spring-data(JPARepository)
    //derive,@Query(JPA-Native)

}
