package com.spring.mvc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "public",name = "masstool_batches")
public class batchEntity {


    @Id
    private Integer batch_id;
    private Integer batch_size;
    private String file_name;
    private String ip;
    @Transient // to ignore this date by hyphenate
    private String ignored;

}
