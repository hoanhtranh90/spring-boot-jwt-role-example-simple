package com.task2.repository;
import com.task2.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    @Query("select u from User u where u.username like %:username")
//    Page<Osp_user> findByFirstnameEndsWith(Pageable pageable,String name);
    Page<User> findByFirstnameEndsWith(String username, final Pageable pageable);


    @Query("select u from User u")
    Page<User> showAllUser(final Pageable pageable);

}