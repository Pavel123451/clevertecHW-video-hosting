package ru.clevertec.clevertechwvideohosting.handler.channel.impl;

import ru.clevertec.clevertechwvideohosting.dto.channel.ChannelUpdateDto;
import ru.clevertec.clevertechwvideohosting.handler.channel.ChannelUpdateHandler;
import ru.clevertec.clevertechwvideohosting.model.Channel;

public class LanguageUpdateHandler implements ChannelUpdateHandler {
    @Override
    public void handle(Channel channel, ChannelUpdateDto updateChannel) {
        if (updateChannel.getLanguage() != null && !updateChannel.getLanguage().isBlank()) {
            channel.setLanguage(updateChannel.getLanguage());
        }
    }
}