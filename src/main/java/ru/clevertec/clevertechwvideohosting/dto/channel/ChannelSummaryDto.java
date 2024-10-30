package ru.clevertec.clevertechwvideohosting.dto.channel;

import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelSummaryDto {
    private String name;
    private String description;
    private String language;
    private String avatar;
    private Long categoryId;
    private String authorName;
    private ZonedDateTime createdAt;
    private Long subscribersCount;
}
