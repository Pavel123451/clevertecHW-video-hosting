package ru.clevertec.clevertechwvideohosting.dto.channel;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class ChannelUpdateDto {
    private String name;
    private String description;
    private String language;
    private String avatar;
    private Long categoryId;
}
