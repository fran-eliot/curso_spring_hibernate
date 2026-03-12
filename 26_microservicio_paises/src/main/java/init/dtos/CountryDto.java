package init.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryDto {
	
	private String name;
	private String region;
	private long population;
	private String capital;
	private String flag;

}
