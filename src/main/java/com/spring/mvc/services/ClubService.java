package com.spring.mvc.services;

import com.spring.mvc.dto.ClubDto;
import com.spring.mvc.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    Club saveClub(ClubDto clubDto);

    ClubDto findClubById(Integer clubId);

    void updateClub(ClubDto clubdto);

    void delete(Integer clubId);

    List<ClubDto> serachClubs(String query);
}
