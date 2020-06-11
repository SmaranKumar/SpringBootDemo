package com.explorelearning.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReposirory extends JpaRepository<User,Integer>{

}
