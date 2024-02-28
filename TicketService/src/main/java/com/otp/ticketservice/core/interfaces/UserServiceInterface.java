package com.otp.ticketservice.core.interfaces;

import com.otp.ticketservice.core.entity.User;

public interface UserServiceInterface {
    User getUser(Long userId);
}
