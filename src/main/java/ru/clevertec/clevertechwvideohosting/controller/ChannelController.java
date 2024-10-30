package ru.clevertec.clevertechwvideohosting.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.clevertechwvideohosting.dto.channel.*;
import ru.clevertec.clevertechwvideohosting.service.ChannelService;

@RestController
@RequestMapping("/channels")
@RequiredArgsConstructor
public class ChannelController {
    private final ChannelService channelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChannelResponse createChannel(@Valid @RequestBody ChannelCreateDto channelCreateDto) {
        return channelService.createChannel(channelCreateDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ChannelsPaginatedDto getChannels(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            ChannelsDto channelsDto) {

        return channelService.getChannels(page, size, channelsDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ChannelResponse updateChannel(@PathVariable Long id,
                                                         @Valid @RequestBody ChannelUpdateDto channelUpdateDto) {
        return channelService.updateChannel(id, channelUpdateDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ChannelResponse partialUpdateChannel(
            @PathVariable Long id,
            @RequestBody ChannelPartialUpdateDto updateChannel) {
        return channelService.partialUpdateChannel(id, updateChannel);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ChannelSummaryDto getChannelDetails(@PathVariable Long id) {
        return channelService.getChannelDetails(id);
    }

    @PostMapping("/{channelId}/subscribers/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public String subscribeToChannel(@PathVariable Long channelId,
                                                     @PathVariable Long userId) {
        channelService.subscribeUserToChannel(userId, channelId);
        return "Successfully subscribed to the channel";
    }

    @DeleteMapping("/{channelId}/subscribers/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public String unsubscribeFromChannel(@PathVariable Long channelId,
                                                         @PathVariable Long userId) {
        channelService.unsubscribeFromChannel(channelId, userId);
        return"Successfully unsubscribed from the channel";
    }
}
