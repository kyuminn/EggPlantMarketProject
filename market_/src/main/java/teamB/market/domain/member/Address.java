package teamB.market.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Address {
    private String postCode;
    private String roadAddr;
    private String detailAddr;
	@Override
	public String toString() {
		return postCode+" "+roadAddr+" "+detailAddr;
	}
    
    
    

    
//    public String toString(Address addr) {
//    	return addr.getRoadAddr()+" "+addr.getDetailAddr();
//    }
}
