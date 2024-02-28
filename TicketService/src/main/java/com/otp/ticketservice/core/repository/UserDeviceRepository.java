package com.otp.ticketservice.core.repository;

import com.otp.ticketservice.core.entity.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDeviceRepository extends JpaRepository<UserDevice,Long> {
}
