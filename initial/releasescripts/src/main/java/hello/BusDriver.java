package hello;

public class BusDriver {
    private final Long tc_kimlik;
    private final String ad;

    public BusDriver (Long tc_kimlik, String ad){
        this.tc_kimlik = tc_kimlik;
        this.ad = ad;
    }    

    public String getAd() {
        return ad;
    }

    public long getTc_kimlik() {
		return tc_kimlik;
	}
}