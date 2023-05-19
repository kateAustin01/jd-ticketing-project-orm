package com.cydeo.repository;


import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    //build all queries that will bring data from db
    //you can use ready repository from spring-data(JPARepository)
    //derive,@Query(JPA-Native)
    User findByUserName(String username);
    void deleteByUserName(String username);

List<User> findAllByRoleDescriptionIgnoreCase(String description);


}
