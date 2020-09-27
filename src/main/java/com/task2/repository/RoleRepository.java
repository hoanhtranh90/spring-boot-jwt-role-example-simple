package com.task2.repository;

import com.task2.model.Role;
import com.task2.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    //lay danh sach user theo role
    @Query("select u from User u inner join u.roles r where r.role like %:role%")
    Page<User> findByRole(String role,final Pageable pageable);


}
