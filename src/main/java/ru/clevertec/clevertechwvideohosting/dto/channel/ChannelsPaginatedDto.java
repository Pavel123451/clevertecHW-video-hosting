package ru.clevertec.clevertechwvideohosting.dto.channel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelsPaginatedDto {
    private List<ChannelShortSummaryDto> channelShortSummaries;
    private int totalElements;
    private int totalPages;

}
