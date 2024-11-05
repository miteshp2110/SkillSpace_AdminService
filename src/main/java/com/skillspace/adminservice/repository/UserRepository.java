package com.skillspace.adminservice.repository;

import com.skillspace.adminservice.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users,String> {

    @Query(value = "select * from users where role = 'admin' ", nativeQuery = true)
    public List<Users> getAllAdmin();

    @Query(value = "select * from users where role ='teacher' ",nativeQuery = true)
    public List<Users> getAllTeacher();

    @Query(value = "select emailStatus from users where email =:email",nativeQuery = true)
    public boolean getEmailStatus(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "delete from users where email =:email",nativeQuery = true)
    public void deleteEmail(@Param("email") String email);
}
