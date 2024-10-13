package ru.clevertec.clevertechwvideohosting.dto.channel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelShortSummaryDto {
    private String name;
    private Long subscriberCount;
    private String language;
    private String avatar;
    private Long categoryId;
}
