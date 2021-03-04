package ru.shift.certbotcft.licenses.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LicensePublicPrivatKey {
    public int[] publicKey;
    public int[] privateKey;
}
