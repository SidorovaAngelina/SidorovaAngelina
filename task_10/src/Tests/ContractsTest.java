package Tests;

import org.junit.*;



public class ContractsTest extends Assert{
    @Test
    public void create_CreateEmptyContracts_ContractCountEqualsZero(){
        Contract contract = Contract.create();
        assertEquals(0, contract.getContractsCount());
    }
}

