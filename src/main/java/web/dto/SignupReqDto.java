package web.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignupReqDto {
	private String name;
	private String email;
	private String username;
	private String password;
}
