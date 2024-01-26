package com.spring.mvc.repositorys;

import com.spring.mvc.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Integer> {
}
