package ru.shift.certbotcft.licenses.services;

import org.aspectj.weaver.ast.HasAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shift.certbotcft.api.repository.LicenseRepository;
import ru.shift.certbotcft.common.utils.Encryption;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Service
public class LicenseService {
    private final LicenseRepository licenseRepository;

    @Autowired
    public LicenseService(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    private int validateMessage(String msg) {
        int digitalMessage = 0;

        for (Character sym : msg.toCharArray()) {
            digitalMessage += (int) sym;
        }
        return digitalMessage;
    }

    private boolean isPrimeNumber(int num) {
        if (num % 2 == 0) {
            return num == 2;
        }

        int d = 3;
        while (d * d <= num && num % d != 0) {
            d += 2;
        }
        return d * d > num;
    }

    private HashMap<String, Integer> getRSAPrimeNumbers(String email) {
        HashMap<String, Integer> primeNumbers = new HashMap<>();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int p = (int) timestamp.getTime() % 1509 + 100;
        int q = 0;

        for (Character sym : email.toCharArray()) {
            q += (int) sym;
        }

        while (!isPrimeNumber(p)) {
            p += 1;
        }
        while (!isPrimeNumber(q)) {
            q += 1;
        }

        primeNumbers.put("p", p);
        primeNumbers.put("q", q);
        return primeNumbers;
    }

    private int eulerFunction(int p, int q) {
        return (p - 1) * (q - 1);
    }

    private int GCD(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    private int[] extendedGCD(int num1, int num2) {
        int[] resultArray = new int[3];
        int[] GCDArray;

        if (num1 == 0) {
            resultArray[0] = num2;
            resultArray[1] = 0;
            resultArray[2] = 1;
            return resultArray;
        } else {
            GCDArray = extendedGCD(num2 % num1, num1);
        }
        resultArray[0] = GCDArray[0];
        resultArray[1] = GCDArray[2] - (num2 / num1) * GCDArray[1];
        resultArray[2] = GCDArray[1];
        return resultArray;
    }

    private int multiplicativeInverse(int a, int m) {
        int[] extGCDResult = extendedGCD(a, m);
        return extGCDResult[1] % m;
    }

    private int getPublicExponent(int p, int q) {
        int e = 1 + (int) (Math.random() * eulerFunction(p, q));

        while (GCD(e, eulerFunction(p, q)) != 1) {
            e = 1 + (int) (Math.random() * eulerFunction(p, q));
        }
        return e;
    }

    private int encode(int msg, int[] publicKey) {
        return (int) (Math.pow(msg, publicKey[0]) % publicKey[1]);
    }

    private int decode(Integer encodedMsg, int[] privateKey) {
        return (int) (Math.pow(encodedMsg, privateKey[0]) % privateKey[1]);
    }


    public HashMap<String, int[]> getKeys(String email) {
        HashMap<String, Integer> primeRSANumbers = getRSAPrimeNumbers(email);
        int p = primeRSANumbers.get("p");
        int q = primeRSANumbers.get("q");
        int RSAModule = p * q;

        HashMap<String, int[]> keys = new HashMap<>();
        int[] publicKey = new int[2];
        int[] privateKey = new int[2];

        publicKey[0] = getPublicExponent(p, q);
        publicKey[1] = RSAModule;

        privateKey[0] = multiplicativeInverse(getPublicExponent(p, q), eulerFunction(p, q));
        privateKey[1] = RSAModule;

        keys.put("publicKey", publicKey);
        keys.put("privateKey", privateKey);
        return keys;
    }

//    public String validateLicense(String msg, String email, int[] publicKey, int[] privateKey) {
//
//    }
}
