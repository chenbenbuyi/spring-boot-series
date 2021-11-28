package cnblogs.chenbenbuyi.event;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo  implements Serializable{

    private Integer id;

    private String username;

    public UserVo(Integer id, String username) {
        this.id = id;
        this.username = username;
    }
}