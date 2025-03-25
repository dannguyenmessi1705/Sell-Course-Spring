package com.learn.course.util;

import com.learn.course.config.SecurityConfig;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

@UtilityClass
@Slf4j
public class KeyUtils {
  public static KeyFactory keyFactory;

  public static KeyFactory loadKeyFactory() throws NoSuchAlgorithmException {
    if (Objects.nonNull(keyFactory)) {
      return keyFactory;
    }
    synchronized (SecurityConfig.class) {
      Security.addProvider(new BouncyCastleProvider());
      keyFactory = KeyFactory.getInstance("RSA");
      return keyFactory;
    }
  }

  public static PrivateKey getPrivateKey(byte[] bytePrivateKey) {
    try {
      PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytePrivateKey);
      return loadKeyFactory().generatePrivate(keySpec);
    } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
      log.error("Read private key error", e);
      throw new RuntimeException(e.getMessage());
    }
  }

  public static PublicKey getPublicKey(byte[] bytePublicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
    return loadKeyFactory().generatePublic(new X509EncodedKeySpec(bytePublicKey));
  }

}
