package net.hzxzkj.cxlaike.module.cxlaike.enums;

import cn.hutool.core.util.ArrayUtil;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hzxzkj.cxlaike.framework.common.core.IntArrayValuable;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/8/22]
 * @see [相关类/方法]
 * 创建日期: 2023/8/22
 */
@Getter
@AllArgsConstructor
public enum AiSpeechRateAndPitchRateType implements IntArrayValuable {

  /**
   * 1
   */
  ONE(-10, -500),
  /**
   * 2
   */
  TWO(-9, -467),
  /**
   * 3
   */
  THREE(-8, -434),
  /**
   * 4
   */
  FOUR(-7, -401),
  /**
   * 5
   */
  FIVE(-6, -368),
  /**
   * 6
   */
  SIX(-5, -335),
  /**
   * 7
   */
  SEVEN(-4, -302),
  /**
   * 8
   */
  EIGHT(-3, -269),
  /**
   * 9
   */
  NINE(-2, -236),
  /**
   * 10
   */
  TEN(-1, -203),
  /**
   * 11
   */
  ELEVEN(0, -170),
  /**
   * 12
   */
  TWELVE(1, -137),
  /**
   * 13
   */
  THIRTEEN(2, -104),
  /**
   * 14
   */
  FOURTEEN(3, -71),
  /**
   * 15
   */
  FIFTEEN(4, -38),
  /**
   * 16
   */
  SIXTEEN(5, 0),

  /**
   * 17
   */
  SEVENTEEN(6, 33),

  /**
   * 18
   */
  EIGHTEEN(7, 66),

  /**
   * 19
   */
  NINETEEN(8, 100),

  /**
   * 20
   */
  TWENTY(9, 133),

  /**
   * 21
   */
  TWENTY_ONE(10, 166),

  /**
   * 22
   */
  TWENTY_TWO(11, 200),

  /**
   * 23
   */
  TWENTY_THREE(12, 233),

  /**
   * 24
   */
  TWENTY_FOUR(13, 266),

  /**
   * 25
   */
  TWENTY_FIVE(14, 300),

  /**
   * 26
   */
  TWENTY_SIX(15, 333),

  /**
   * 27
   */
  TWENTY_SEVEN(16, 366),

  /**
   * 28
   */
  TWENTY_EIGHT(17, 400),

  /**
   * 29
   */
  TWENTY_NINE(18, 433),

  /**
   * 30
   */
  THIRTY(19, 466),

  /**
   * 31
   */
  THIRTY_ONE(20, 500);


  public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(AiSpeechRateAndPitchRateType::getType)
      .toArray();

  /**
   * 状态值
   */
  private final Integer type;
  /**
   * 状态名
   */
  private final Integer value;

  public static AiSpeechRateAndPitchRateType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
