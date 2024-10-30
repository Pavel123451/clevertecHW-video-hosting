package ru.clevertec.clevertechwvideohosting.dto.channel;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class ChannelUpdateDto {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String language;

    @NotBlank
    private String avatar;

    @NotNull
    @Min(1)
    private Long categoryId;
}
