package com.spring.mvc.repositorys;

import com.spring.mvc.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club,Integer> {

    @Query("SELECT c FROM Club c where c.title like concat('%' ,:query,'%')")
    Optional<List<Club>> searchClubs(String query);
}
