package ru.shift.certbotcft.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.shift.certbotcft.api.dto.License;

public interface LicenseRepository extends CrudRepository<License, Integer>, JpaRepository<License, Integer> {
}
