package com.lemon.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

/**
 * @author sjp 2019/1/11
 */
public class ShiroTest {

  @Test
  public void md5() {
    String hashAlgorithmName = "MD5";
    String credentials = "test";
    int hashIterations = 5;
    ByteSource credentialsSalt = ByteSource.Util.bytes("test");
    Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
    System.out.println(obj);
    // f9c1a12de11f7dafe582df64841e4b72
  }

}
