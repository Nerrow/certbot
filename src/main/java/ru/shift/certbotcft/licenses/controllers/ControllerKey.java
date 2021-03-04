package ru.shift.certbotcft.licenses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shift.certbotcft.licenses.dto.LicensePublicPrivatKey;
import ru.shift.certbotcft.licenses.services.LicenseService;

import java.util.HashMap;

@RestController
public class ControllerKey {

    private final LicenseService licenseService;

    @Autowired
    public ControllerKey(LicenseService licenseService){
        this.licenseService = licenseService;
    }

    @PostMapping("/createLicense")
    public HashMap<String, int[]> createLicense(@RequestBody String email) {
        return licenseService.createKeys(email);
    }

    @PostMapping("/validateLicense")
    public boolean validateLicense(@RequestBody LicensePublicPrivatKey licensePublicPrivatKey) {
        return licenseService.validateLicense(licensePublicPrivatKey.getPrivateKey(), licensePublicPrivatKey.getPublicKey());
    }
}
