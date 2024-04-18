package net.hzxzkj.cxlaike.module.cxlaike.controller.admin.callback.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/9/19]
 * @see [相关类/方法]
 * 创建日期: 2023/9/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProduceMediaCompleteVO implements java.io.Serializable{

  private static final long serialVersionUID = 1L;

  @JsonProperty("EventType")
  private String EventType;
  @JsonProperty("TikTokId")
  private Long UserId;
  @JsonProperty("EventTime")
  private String EventTime;
  @JsonProperty("MessageBody")
  private ProduceMediaCompleteMessageBody MessageBody;

}
