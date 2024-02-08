package com.spring.mvc.mappers;

import com.spring.mvc.dto.ClubDto;
import com.spring.mvc.models.Club;
import com.spring.mvc.models.UserEntity;

import java.util.stream.Collectors;

import static com.spring.mvc.mappers.EventMapper.mapToEventDto;

public class ClubMapper {

    public static Club mapToClub(ClubDto clubdto) {
        Club club = Club.builder()
                .id(clubdto.getId())
                .title(clubdto.getTitle())
                .content(clubdto.getContent())
                .photoUrl(clubdto.getPhotoUrl())
                .updatedOn(clubdto.getUpdatedOn())
                .createdOn(clubdto.getCreatedOn())
                .createdBy(clubdto.getCreatedBy())
                .build();
        return club;
    }

    public static ClubDto mapToClubDto(Club club){
        ClubDto clubDto = ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .content(club.getContent())
                .photoUrl(club.getPhotoUrl())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .createdBy(club.getCreatedBy())
                .events(club.getEvents().stream().map((event) ->mapToEventDto(event)).collect(Collectors.toList()))
                .build();
        return clubDto;
    }

//    public static ClubDto searchClubsMap(Club club, UserEntity user){
//        club.setCreatedBy(user);
//        ClubDto clubDto = mapToClubDto(club);
//        return clubDto;
//    }
}
