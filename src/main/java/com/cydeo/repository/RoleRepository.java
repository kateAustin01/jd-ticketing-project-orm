package com.cydeo.repository;

import com.cydeo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    //build all queries that will bring data from db
    //you can use ready repository from spring-data(JPARepository)
    //derive,@Query(JPA-Native)
    //there are 20 methods here available to use from JPARepository
}
