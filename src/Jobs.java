/**
 * Created by tobiaj on 2017-04-20.
 */
public class Jobs {

    private Integer numberOfScan;

    public Integer getNumberOfScan() {
        return numberOfScan;
    }

    public Integer getArticelNumber() {
        return articelNumber;
    }

    private Integer articelNumber;

    public Jobs(Integer numberOfScan, Integer articelNumber){
        this.numberOfScan = numberOfScan;
        this.articelNumber = articelNumber;
    }
}
