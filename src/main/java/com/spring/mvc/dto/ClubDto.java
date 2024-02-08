package com.spring.mvc.dto;

import com.spring.mvc.models.Event;
import com.spring.mvc.models.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ClubDto {
    private Integer id;
    @NotEmpty(message = "title can not be empty")
    private String title;
    @NotEmpty(message = "photoUrl can not be empty")
    private String photoUrl;
    @NotEmpty(message = "content can not be empty")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private UserEntity createdBy;
    private List<EventDto> events;
}
