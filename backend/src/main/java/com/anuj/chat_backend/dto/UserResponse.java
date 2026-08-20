package com.anuj.chat_backend.dto;

import com.anuj.chat_backend.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String profilePicture;
    private String status;

    public static UserResponse fromUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .profilePicture(user.getProfilePicture())
                .status(user.getStatus().name())
                .build();
    }
}