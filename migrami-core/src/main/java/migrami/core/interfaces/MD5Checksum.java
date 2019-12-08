package migrami.core.interfaces;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.security.MessageDigest;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode
@ToString
class MD5Checksum implements MigramiChecksum {
  private final String value;

  static MD5Checksum create(String content) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(content.getBytes());

      StringBuilder result = new StringBuilder();
      for (byte b : md.digest()) {
        result.append(String.format("%02x", b));
      }

      return new MD5Checksum(result.toString());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String value() {
    return value;
  }
}