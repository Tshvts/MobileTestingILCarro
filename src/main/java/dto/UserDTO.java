package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder

public class UserDTO
{
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}