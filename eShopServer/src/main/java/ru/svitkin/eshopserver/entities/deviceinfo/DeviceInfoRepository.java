package ru.svitkin.eshopserver.entities.deviceinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceInfoRepository extends JpaRepository<DeviceInfo, Integer> {
}
