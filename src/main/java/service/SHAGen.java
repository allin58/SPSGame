package service;

import org.bouncycastle.jcajce.provider.digest.SHA3;

import java.security.MessageDigest;
import java.security.SecureRandom;


public class SHAGen {



    public static String getKey() {

        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[32];
        random.nextBytes(bytes);
        String key = "";
        for (byte b : bytes) {
            String st = String.format("%02X", b);
            key = key + st;
        }
        return key;
    }


    public static String sha3(final String input) {
        final SHA3.DigestSHA3 sha3 = new SHA3.Digest256();

        sha3.update(input.getBytes());

        return hashToString(sha3);
    }

    public static String hashToString(MessageDigest hash) {
        return hashToString(hash.digest());
    }

    public static String hashToString(byte[] hash) {
        StringBuffer buff = new StringBuffer();

        for (byte b : hash) {
            buff.append(String.format("%02x", b & 0xFF));
        }

        return buff.toString();
    }


}
