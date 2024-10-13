package ru.clevertec.clevertechwvideohosting.handler.channel.impl;

import ru.clevertec.clevertechwvideohosting.dto.channel.ChannelUpdateDto;
import ru.clevertec.clevertechwvideohosting.handler.channel.ChannelUpdateHandler;
import ru.clevertec.clevertechwvideohosting.model.Channel;

public class ChannelNameUpdateHandler implements ChannelUpdateHandler {
    @Override
    public void handle(Channel channel, ChannelUpdateDto updateChannel) {
        if (updateChannel.getName() != null && !updateChannel.getName().isBlank()) {
            channel.setName(updateChannel.getName());
        }
    }
}
