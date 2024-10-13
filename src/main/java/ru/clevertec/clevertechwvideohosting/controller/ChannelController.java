package ru.clevertec.clevertechwvideohosting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.clevertechwvideohosting.dto.channel.ChannelCreateDto;
import ru.clevertec.clevertechwvideohosting.dto.channel.ChannelResponse;
import ru.clevertec.clevertechwvideohosting.dto.channel.ChannelSummaryDto;
import ru.clevertec.clevertechwvideohosting.dto.channel.ChannelUpdateDto;
import ru.clevertec.clevertechwvideohosting.service.ChannelService;

import java.util.Map;

@RestController
@RequestMapping("/channels")
@RequiredArgsConstructor
public class ChannelController {
    private final ChannelService channelService;

    @PostMapping
    public ResponseEntity<ChannelResponse> createChannel(@RequestBody ChannelCreateDto channelCreateDto) {
        ChannelResponse channelResponse = channelService.createChannel(channelCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(channelResponse);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getChannels(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) Long categoryId) {

        Map<String, Object> response = channelService.getChannels(page, size,
                title, language, categoryId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChannelResponse> updateChannel(@PathVariable Long id,
                                                         @RequestBody ChannelUpdateDto channelUpdateDto) {
        ChannelResponse updatedChannel = channelService.updateChannel(id, channelUpdateDto);
        return ResponseEntity.ok(updatedChannel);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ChannelResponse> partialUpdateChannel(
            @PathVariable Long id,
            @RequestBody ChannelUpdateDto updateChannel) {
        ChannelResponse updatedChannel = channelService.partialUpdateChannel(id, updateChannel);
        return ResponseEntity.ok(updatedChannel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChannelSummaryDto> getChannelDetails(@PathVariable Long id) {
        ChannelSummaryDto channelDetails = channelService.getChannelDetails(id);
        return ResponseEntity.ok(channelDetails);
    }

    @PostMapping("/{id}/subscriptions")
    public ResponseEntity<String> subscribeToChannel(@PathVariable Long id,
                                                     @RequestParam Long userId) {
        channelService.subscribeUserToChannel(userId, id);
        return ResponseEntity.ok("Successfully subscribed to the channel");
    }

    @DeleteMapping("/{id}/subscriptions")
    public ResponseEntity<String> unsubscribeFromChannel(@PathVariable Long id,
                                                         @RequestParam Long userId) {
        channelService.unsubscribeFromChannel(id, userId);
        return ResponseEntity.ok("Successfully unsubscribed from the channel");
    }
}
