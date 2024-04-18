package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin;

import net.hzxzkj.cxlaike.framework.common.util.io.vo.FileStatsVO;
import org.bytedeco.javacv.FFmpegFrameGrabber;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/8/24]
 * @see [相关类/方法]
 * 创建日期: 2023/8/24
 */
public class FileUtils {

  /**
   * 根据url获取视频时长(秒)
   *
   * @param url
   * @return
   */
  public static Integer getFileLength(String url) {
    FFmpegFrameGrabber ff = new FFmpegFrameGrabber(url);
    try {
      ff.start();
      int ftp = ff.getLengthInFrames();
      int fps = (int) ff.getFrameRate();
      return ftp / fps;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 根据url获取音频时长(秒)
   *
   * @param url
   * @return
   */
  public static Integer getFileLengthAudio(String url) {
    FFmpegFrameGrabber ff = new FFmpegFrameGrabber(url);
    try {
      ff.start();
      long length = ff.getLengthInTime();
      return (int) (length/1000000);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 根据文本长度估算出读取需要多少时长 (秒)
   *
   * @param text 文本内容
   * @return
   */
  public static Integer getTextReadDuration(String text) {
    //大约1秒能读4个字
    return (int) Math.round((double) text.length() / 4);
  }

  /**
   * 根据时长判断需要视频切片数量 (向上取整)
   *
   * @param textReadDuration
   * @return
   */
  public static Integer getSliceNum(Integer textReadDuration) {
    return (int) Math.ceil((double) textReadDuration / 3);
  }
}
