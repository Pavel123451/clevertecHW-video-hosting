package ru.clevertec.clevertechwvideohosting.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.clevertec.clevertechwvideohosting.dto.channel.*;
import ru.clevertec.clevertechwvideohosting.mapper.ChannelMapper;
import ru.clevertec.clevertechwvideohosting.model.Category;
import ru.clevertec.clevertechwvideohosting.model.Channel;
import ru.clevertec.clevertechwvideohosting.model.User;
import ru.clevertec.clevertechwvideohosting.repository.CategoryRepository;
import ru.clevertec.clevertechwvideohosting.repository.ChannelRepository;
import ru.clevertec.clevertechwvideohosting.repository.UserRepository;
import ru.clevertec.clevertechwvideohosting.specification.ChannelSpecification;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelService {
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ChannelMapper channelMapper;

    public ChannelResponse createChannel(ChannelCreateDto channelCreateDto) {
        User author = userRepository.findById(channelCreateDto.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("User with this id not found"));
        Category category = categoryRepository.findById(channelCreateDto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category with this id not found"));
        Channel channel = channelMapper.toChannel(channelCreateDto);
        channel.setAuthor(author);
        channel.setCategory(category);
        channel.setCreatedAt(ZonedDateTime.now());

        Channel savedChannel = channelRepository.save(channel);
        return channelMapper.toChannelResponse(savedChannel);
    }

    public ChannelsPaginatedDto getChannels(int page, int size, ChannelsDto channelsDto) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<Channel> spec = new ChannelSpecification(channelsDto.getTitle(),
                channelsDto.getLanguage(), channelsDto.getCategoryId());

        Page<Channel> channelPage = channelRepository.findAll(spec, pageable);

        List<ChannelShortSummaryDto> channelShortSummaries = channelPage.stream()
                .map(channelMapper::toChannelShortSummaryDto)
                .toList();

        return channelMapper.toChannelsPaginatedDto(channelShortSummaries, channelPage);
    }

    public ChannelSummaryDto getChannelDetails(Long id) {
        Channel channel = channelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Channel with with id not found"));
        return channelMapper.toChannelSummaryDto(channel);
    }

    public void subscribeUserToChannel(Long userId, Long channelId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with this id not found"));

        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new EntityNotFoundException("Channel with this id not found"));

        if (user.getSubscribedChannels().contains(channel)) {
            throw new IllegalStateException("User is already subscribed to this channel");
        }

        user.getSubscribedChannels().add(channel);
        userRepository.save(user);
    }

    public void unsubscribeFromChannel(Long channelId, Long userId) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new EntityNotFoundException("Channel with this id not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with this id not found"));

        if (!channel.getSubscribers().contains(user)) {
            throw new IllegalStateException("User is not subscribed to this channel");
        }

        user.getSubscribedChannels().remove(channel);
        userRepository.save(user);
    }

    public ChannelResponse updateChannel(Long id, ChannelUpdateDto channelUpdateDto) {
        Channel existingChannel = channelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Channel not found"));

        Category category = categoryRepository.findById(channelUpdateDto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category with this id not found"));
        existingChannel.setCategory(category);

        channelMapper.updateChannelFromDto(channelUpdateDto, existingChannel);

        Channel updatedChannel = channelRepository.save(existingChannel);
        return channelMapper.toChannelResponse(updatedChannel);
    }


    public ChannelResponse partialUpdateChannel(Long id, ChannelPartialUpdateDto updateChannelDto) {
        Channel existingChannel = channelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Channel with this id not found"));

        if (updateChannelDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(updateChannelDto.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Category with this id not found"));
            existingChannel.setCategory(category);
        }

        channelMapper.partialUpdateChannelFromDto(updateChannelDto, existingChannel);

        Channel updatedChannel = channelRepository.save(existingChannel);
        return channelMapper.toChannelResponse(updatedChannel);
    }
}
