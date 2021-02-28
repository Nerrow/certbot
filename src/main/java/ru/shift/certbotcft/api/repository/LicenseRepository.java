package ru.shift.certbotcft.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.shift.certbotcft.api.dto.License;

public interface LicenseRepository extends CrudRepository<License, Integer>, JpaRepository<License, Integer> {
//TODO доделать
//    @Query(value =
//            "SELECT usr.email, license.certificate, license.certificate_type\n" +
//            "FROM license INNER JOIN (public.user usr INNER JOIN user_license ON usr.id = user_license.user_id) " +
//            "ON license.id = user_license.license_id;", nativeQuery = true);
//    Iterable<License> getAllLicenseByUser(String email);




}
