package ru.clevertec.clevertechwvideohosting.dto.channel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Setter
@Getter
public class ChannelResponse {
    private Long id;
    private String name;
    private String description;
    private String language;
    private String avatar;
    private Long categoryId;
    private Long authorId;
    private ZonedDateTime createdAt;
}
