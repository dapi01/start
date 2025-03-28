package org.codenova.start.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@Builder
public class Comment {
    private int id;
    private String isoCode;
    private String body;
    private LocalDate writedAt;
}
