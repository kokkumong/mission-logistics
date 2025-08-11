package mission.application.domain.dto;

import mission.application.domain.model.Address;

public class DurationDto {
    private Address place1Address;
    private Address place2Address;

    public DurationDto(Address place1Address, Address place2Address) {
        this.place1Address = place1Address;
        this.place2Address = place2Address;
    }
}
