package seaweedfs.client;

import com.google.protobuf.ByteString;
import java.util.HashMap;
import java.util.Map;

/**
 * 对 Entry 的 ExtendedMap 的 key 进行统一格式化的工具类 <br>@date 2024/4/9 10:44<br>
 *
 * @author stillmoon
 */
public class ExtendedFormatUtil {

  public static void addKeyPrefix(FilerProto.Entry.Builder entry) {
    Map<String, ByteString> extendedMap = new HashMap<>(entry.getExtendedCount());
    entry.getExtendedMap().forEach((key, val) -> {
      extendedMap.put("Seaweed-" + key, val);
    });
    entry.clearExtended();
    entry.putAllExtended(extendedMap);
  }

  public static void removeKeyPrefix(FilerProto.Entry.Builder entry) {
    Map<String, ByteString> extendedMap = new HashMap<>(entry.getExtendedCount());
    entry.getExtendedMap().forEach((key, val) -> {
      extendedMap.put(key.replace("Seaweed-", ""), val);
    });
    entry.clearExtended();
    entry.putAllExtended(extendedMap);
  }

}
