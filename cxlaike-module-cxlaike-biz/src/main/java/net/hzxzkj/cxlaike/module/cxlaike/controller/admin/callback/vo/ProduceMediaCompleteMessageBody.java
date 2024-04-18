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
public class ProduceMediaCompleteMessageBody implements java.io.Serializable{

  private static final long serialVersionUID = 1L;
  @JsonProperty("Status")
  private String Status;
  @JsonProperty("MediaURL")
  private String MediaURL;
  @JsonProperty("MediaId")
  private String MediaId;
  @JsonProperty("ProjectId")
  private String ProjectId;
  @JsonProperty("ErrorCode")
  private String ErrorCode;
  @JsonProperty("ErrorMessage")
  private String ErrorMessage;
  @JsonProperty("JobId")
  private String JobId;
}
