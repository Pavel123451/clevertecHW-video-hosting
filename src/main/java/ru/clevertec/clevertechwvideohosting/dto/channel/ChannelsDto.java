package ru.clevertec.clevertechwvideohosting.dto.channel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class ChannelsDto {
    private String title;
    private String language;
    private Long categoryId;
}
