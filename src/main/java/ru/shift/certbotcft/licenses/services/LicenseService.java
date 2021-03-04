package ru.shift.certbotcft.licenses.services;

import org.aspectj.weaver.ast.HasAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shift.certbotcft.api.repository.LicenseRepository;
import ru.shift.certbotcft.common.utils.Encryption;

import java.security.interfaces.RSAKey;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Service
public class LicenseService {

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
        int p = (int) (timestamp.getTime() % 1509) + 100;
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
        return b == 0 ? a : GCD(b, a % b);
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
//        int[] extGCDResult = extendedGCD(a, m);
//        System.out.println(-4484 % 54);
//        return extGCDResult[1] % m;
        a = a % m;

        for (int x = 1; x < m; ++x)
            if ((a * x) % m == 1) {
                return x;
            }
        return 1;
    }

    private int getPublicExponent(int p, int q) {
        int e = 1 + (int) (Math.random() * eulerFunction(p, q));

        while (GCD(e, eulerFunction(p, q)) != 1) {
            e = 1 + (int) (Math.random() * eulerFunction(p, q));
        }
        return e;
    }

    private int encode(int msg, int[] publicKey) {
        return ((int) Math.pow(msg, publicKey[0])) % publicKey[1];
    }

    private int decode(int encodedMsg, int[] privateKey) {
        return ((int) Math.pow(encodedMsg, privateKey[0])) % privateKey[1];
    }

    public HashMap<String, int[]> createKeys(String email) {
        HashMap<String, Integer> primeRSANumbers = getRSAPrimeNumbers(email);
        int p = primeRSANumbers.get("p");
        int q = primeRSANumbers.get("q");
        int RSAModule = p * q;
        int publicExponent = getPublicExponent(p, q);

        System.out.println(primeRSANumbers);
        HashMap<String, int[]> keys = new HashMap<>();
        int[] publicKey = new int[2];
        int[] privateKey = new int[2];

        publicKey[0] = publicExponent;
        publicKey[1] = RSAModule;

        privateKey[0] = multiplicativeInverse(publicExponent, eulerFunction(p, q));
        privateKey[1] = RSAModule;

        keys.put("publicKey", publicKey);
        keys.put("privateKey", privateKey);
        return keys;
    }

    public boolean validateLicense(int[] publicKey, int[] privateKey) {
        int msg = validateMessage(Integer.toString(1 + (int) (Math.random() * 100)));

        int encodedMsg = encode(msg, publicKey);
        int decodedMsg = decode(encodedMsg, privateKey);
        System.out.println(msg);
        System.out.println(encodedMsg);
        System.out.println(decodedMsg);

        return msg == decodedMsg;
    }
}
