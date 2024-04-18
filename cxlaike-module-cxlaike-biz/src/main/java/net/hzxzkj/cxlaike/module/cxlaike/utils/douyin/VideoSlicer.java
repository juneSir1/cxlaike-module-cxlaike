package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.video.VideoTrackClip;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiVideoSliceVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.videoslice.VideoSliceDO;
import org.apache.commons.compress.utils.Lists;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/8/25]
 * @see [相关类/方法]
 * 创建日期: 2023/8/25
 */
@Slf4j
public class VideoSlicer {

  private List<Queue<AiVideoSliceVO>> list;

  public VideoSlicer(List<List<AiVideoSliceVO>> groupList) {
    list = Lists.newArrayList();
    groupList.forEach(
        group -> {
          // 使用LinkedList实现Queue，并用Collections.shuffle随机打乱切片顺序
          Queue<AiVideoSliceVO> queue = new LinkedList<>(group);
          Collections.shuffle((List<?>) queue);
          list.add(queue);
        }
    );
  }


  public List<AiVideoSliceVO> getSlice(List<AiVideoSliceVO> newList, Integer size) {
    // 按照要求，每次获取一个切片，都需要从两个队列中交替获取
    for (int i = 0; i < 1000; ++i) {
      //设置最大循环次数，防止死循环
      for (Queue<AiVideoSliceVO> queue : list) {
        if (newList.size() >= size) {
          return newList;
        }
        if (!queue.isEmpty()) {
          newList.add(queue.poll());
        }
      }
      //队列里没有元素了才会返回未完成的
      return newList;
    }
    return newList;
  }

  /**
   * 判断当前队列集合还有没有元素
   *
   * @param queues
   * @return
   */
  public static boolean allEmpty(List<Queue<AiVideoSliceVO>> queues) {
    for (Queue<AiVideoSliceVO> q : queues) {
      if (!q.isEmpty()) {
        return false;
      }
    }
    return true;
  }

  /**
   * 根据时长切片
   *
   * @param url
   * @param duration 时长
   * @return
   */
  public static List<AiVideoSliceVO> videoSlice(String url, Float duration) {
    List<AiVideoSliceVO> list = Lists.newArrayList();
    if (duration == null) {
      try {
        duration = Objects.requireNonNull(FileUtils.getFileLength(url)).floatValue();
      } catch (Exception e) {
        log.error("获取时长失败!", e);
        return list;
      }
    }
    // 时长小于等于3秒，不切片
    if (duration <= 3) {
      AiVideoSliceVO sliceVO = new AiVideoSliceVO();
      sliceVO.setUrl(url);
      sliceVO.setIn(0F);
      sliceVO.setOut(duration);
      sliceVO.setDuration(duration);
      list.add(sliceVO);
      return list;
    }
    // 时长大于3秒，切片
    // 1.切片数量
    int sliceNum = duration.intValue() / 3;
    // 2.每个切片时长
    float sliceDuration = 3;
    // 3.切片
    for (int i = 0; i < sliceNum; i++) {
      AiVideoSliceVO sliceVO = new AiVideoSliceVO();
      sliceVO.setUrl(url);
      sliceVO.setIn(i * sliceDuration);
      sliceVO.setOut((i + 1) * sliceDuration);
      sliceVO.setDuration(sliceDuration);
      list.add(sliceVO);
    }
    return list;
  }

  public static List<VideoTrackClip> getVideoTrackClipList(List<VideoSliceDO> sliceDOList,
      Integer duration) {

    //根据素材id小的在前面分组,list
    Map<Long, List<VideoSliceDO>> map = sliceDOList.stream()
        .sorted(Comparator.comparing(VideoSliceDO::getMaterialId))
        .collect(Collectors.groupingBy(VideoSliceDO::getMaterialId));

    List<List<VideoSliceDO>> videoSliceLists = Lists.newArrayList();

    map.forEach((k, v) -> {
      videoSliceLists.add(v);
    });

    List<VideoTrackClip> list = Lists.newArrayList();
    //查看需要多少个切片
    int size = duration / 3;
    //转场效果会减少切片使用时长
    int num = size + (size / 3) + 1;

    List<VideoSliceDO> sliceList = Lists.newArrayList();
    for (int i = 0; i < num; i++) {
      VideoSliceDO videoSlice = getVideoSlice(videoSliceLists, i);
      videoSlice.setNum(videoSlice.getNum() + 1);
      sliceList.add(videoSlice);
    }

    for (int i = 0; i < sliceList.size(); i++) {
      VideoSliceDO slice = sliceList.get(i);
      VideoTrackClip videoTrackClip = new VideoTrackClip();
      AiVideoSliceVO sliceVO = JsonUtils.parseObject(slice.getSlice(), AiVideoSliceVO.class);
      videoTrackClip.setMediaURL(sliceVO.getUrl());
      videoTrackClip.setIn(sliceVO.getIn());
      videoTrackClip.setOut(sliceVO.getOut());
      list.add(videoTrackClip);
    }

    return list;
  }

  private static VideoSliceDO getVideoSlice(List<List<VideoSliceDO>> videoSliceLists, int num) {

    int size = videoSliceLists.size();
    int i;
    if (num == 0) {
      i = num;
    } else {
      i = num % size;
    }

    List<VideoSliceDO> sliceDOList = videoSliceLists.get(i);
    Map<Integer, List<VideoSliceDO>> map = sliceDOList.stream()
        .sorted(Comparator.comparing(VideoSliceDO::getNum))
        .collect(Collectors.groupingBy(VideoSliceDO::getNum));
    //map获取第一个元素
    for (Map.Entry<Integer, List<VideoSliceDO>> entry : map.entrySet()) {
      List<VideoSliceDO> value = entry.getValue();
      //随机排序
      Collections.shuffle(value);
      return value.get(0);
    }
    return null;
  }
}
