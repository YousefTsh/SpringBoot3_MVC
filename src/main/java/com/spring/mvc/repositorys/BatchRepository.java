package com.spring.mvc.repositorys;

import com.spring.mvc.models.batchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<batchEntity,Integer> {

}
