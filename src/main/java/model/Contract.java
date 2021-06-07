package model;

import enums.ContractDouble;

public class Contract {

    private final NormalBid contractBid;
    private final ContractDouble contractDouble;
    private final boolean isFourPass;

    public Contract(NormalBid contractBid, ContractDouble contractDouble, boolean isFourPass) {
        this.contractBid = contractBid;
        this.contractDouble = contractDouble;
        this.isFourPass = isFourPass;
    }

    public NormalBid getContractBid() {
        return this.contractBid;
    }

    public ContractDouble getContractDouble() {
        return this.contractDouble;
    }

    @Override
    public String toString() {
        return contractBid.toString();
    }
}
