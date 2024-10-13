package ru.clevertec.clevertechwvideohosting.handler.channel;

import ru.clevertec.clevertechwvideohosting.dto.channel.ChannelUpdateDto;
import ru.clevertec.clevertechwvideohosting.model.Channel;

public interface ChannelUpdateHandler {
    void handle(Channel channel, ChannelUpdateDto updateChannel);
}
