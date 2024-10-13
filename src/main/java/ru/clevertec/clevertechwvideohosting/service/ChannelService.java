package ru.clevertec.clevertechwvideohosting.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.clevertec.clevertechwvideohosting.dto.channel.*;
import ru.clevertec.clevertechwvideohosting.handler.channel.ChannelUpdateHandler;
import ru.clevertec.clevertechwvideohosting.handler.channel.impl.AvatarUpdateHandler;
import ru.clevertec.clevertechwvideohosting.handler.channel.impl.ChannelNameUpdateHandler;
import ru.clevertec.clevertechwvideohosting.handler.channel.impl.DescriptionUpdateHandler;
import ru.clevertec.clevertechwvideohosting.handler.channel.impl.LanguageUpdateHandler;
import ru.clevertec.clevertechwvideohosting.mapper.ChannelMapper;
import ru.clevertec.clevertechwvideohosting.model.Category;
import ru.clevertec.clevertechwvideohosting.model.Channel;
import ru.clevertec.clevertechwvideohosting.model.User;
import ru.clevertec.clevertechwvideohosting.repository.CategoryRepository;
import ru.clevertec.clevertechwvideohosting.repository.ChannelRepository;
import ru.clevertec.clevertechwvideohosting.repository.UserRepository;
import ru.clevertec.clevertechwvideohosting.util.ChannelUtil;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChannelService {
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ChannelMapper channelMapper;

    public ChannelResponse createChannel(ChannelCreateDto channelCreateDto) {
        if (ChannelUtil.channelCreateDtoIsBlank(channelCreateDto)) {
            throw new IllegalArgumentException("Channel fields are blank");
        }
        User author = userRepository.findById(channelCreateDto.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("User with this id not found"));
        Category category = categoryRepository.findById(channelCreateDto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category with this id not found"));
        Channel channel = channelMapper.toChannel(channelCreateDto);
        channel.setAuthorId(author);
        channel.setCategoryId(category);
        channel.setCreatedAt(ZonedDateTime.now());

        Channel savedChannel = channelRepository.save(channel);
        return channelMapper.toChannelResponse(savedChannel);
    }

    public Map<String, Object> getChannels(int page,
                                           int size,
                                           String title,
                                           String language,
                                           Long categoryId) {
        Page<Channel> channelPage = channelRepository.findAllByFilters(title,
                language, categoryId, PageRequest.of(page, size));

        List<ChannelShortSummaryDto> channelSummaries = channelPage.stream()
                .map(channel -> {
                    Long subscribersCount = (long) (channel.getSubscribers() != null
                            ? channel.getSubscribers().size() : 0
                    );
                    ChannelShortSummaryDto dto = channelMapper.toChannelShortSummaryDto(channel);
                    dto.setSubscriberCount(subscribersCount);
                    return dto;
                })
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("channels", channelSummaries);
        response.put("totalElements", channelPage.getTotalElements());
        response.put("totalPages", channelPage.getTotalPages());

        return response;
    }

    public ChannelSummaryDto getChannelDetails(Long id) {
        Channel channel = channelRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new EntityNotFoundException("Channel with with id not found"));

        Long subscribersCount = (long) (channel.getSubscribers() != null
                ? channel.getSubscribers().size() : 0);

        ChannelSummaryDto channelDetailDto = channelMapper.toChannelSummaryDto(channel);
        channelDetailDto.setSubscribersCount(subscribersCount);

        return channelDetailDto;
    }

    public void subscribeUserToChannel(Long userId, Long channelId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with this id not found"));

        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new EntityNotFoundException("Channel with this id not found"));

        if (!user.getSubscribedChannels().contains(channel)) {
            user.getSubscribedChannels().add(channel);
            userRepository.save(user);
        } else {
            throw new IllegalStateException("User is already subscribed to this channel");
        }
    }

    public void unsubscribeFromChannel(Long channelId, Long userId) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new EntityNotFoundException("Channel not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (!channel.getSubscribers().contains(user)) {
            throw new IllegalStateException("User is not subscribed to this channel");
        }

        user.getSubscribedChannels().remove(channel);
        userRepository.save(user);
    }

    public ChannelResponse updateChannel(Long id, ChannelUpdateDto channelUpdateDto) {
        Channel existingChannel = channelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Channel not found"));
        if (ChannelUtil.channelUpdateDtoIsBlank(channelUpdateDto)) {
            throw new IllegalArgumentException("Channel fields are blank");
        }

        existingChannel.setName(channelUpdateDto.getName());
        existingChannel.setDescription(channelUpdateDto.getDescription());
        existingChannel.setLanguage(channelUpdateDto.getLanguage());
        existingChannel.setAvatar(channelUpdateDto.getAvatar());
        Category category = new Category();
        category.setId(channelUpdateDto.getCategoryId());
        existingChannel.setCategoryId(category);

        Channel updatedChannel = channelRepository.save(existingChannel);
        return channelMapper.toChannelResponse(updatedChannel);
    }

    public ChannelResponse partialUpdateChannel(Long id, ChannelUpdateDto updateChannel) {
        Channel channel = channelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Channel with this id not found"));

        List<ChannelUpdateHandler> handlers = List.of(
                new ChannelNameUpdateHandler(),
                new DescriptionUpdateHandler(),
                new LanguageUpdateHandler(),
                new AvatarUpdateHandler()
        );

        handlers.forEach(handler -> handler.handle(channel, updateChannel));

        Channel updatedChannel = channelRepository.save(channel);
        return channelMapper.toChannelResponse(updatedChannel);
    }
}
