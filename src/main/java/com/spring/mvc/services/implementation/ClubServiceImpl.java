package com.spring.mvc.services.implementation;

import com.spring.mvc.dto.ClubDto;
import com.spring.mvc.models.Club;
import com.spring.mvc.models.UserEntity;
import com.spring.mvc.repositorys.ClubRepository;
import com.spring.mvc.repositorys.UserRepository;
import com.spring.mvc.security.SecurityUtil;
import com.spring.mvc.services.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.spring.mvc.mappers.ClubMapper.mapToClub;
import static com.spring.mvc.mappers.ClubMapper.mapToClubDto;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {
    private final ClubRepository clubRepository;
    private final UserRepository userRepository;


    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public Club saveClub(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        return clubRepository.save(club);
    }

    @Override
    public ClubDto findClubById(Integer clubId) {
        Club club = clubRepository.findById(clubId).get();
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubdto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Club club = mapToClub(clubdto);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }

    @Override
    public void delete(Integer clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> serachClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query).get();
        return clubs.stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
    }


}
