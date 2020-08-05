package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TcRole;
import com.example.demo.model.TcUser;
import com.example.demo.model.TcUserRole;

@Repository
public interface TcUserRoleRepository extends JpaRepository<TcUserRole, Long> {
	
	List<TcUserRole> findAllByTcUser(TcUser tcUser);
	
	@Query("SELECT ur.tcRole FROM TcUserRole ur WHERE ur.tcUser = :tcUser")
	List<TcRole> findAllRole(@Param("tcUser") TcUser tcUser);

}
