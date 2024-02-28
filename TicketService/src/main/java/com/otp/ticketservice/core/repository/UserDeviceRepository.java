package com.otp.ticketservice.core.repository;

import com.otp.ticketservice.core.entity.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDeviceRepository extends JpaRepository<UserDevice,Long> {
}
