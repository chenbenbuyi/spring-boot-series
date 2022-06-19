package cnblogs.chenbenbuyi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateTaskStatusDTO implements Serializable {
    private static final long serialVersionUID = 61566896540687625L;
    private Long id;

    private Integer status;

    private String msg;
}
