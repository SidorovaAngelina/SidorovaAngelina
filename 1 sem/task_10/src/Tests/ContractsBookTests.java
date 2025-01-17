package Tests;

import org.junit.*;
import Core.*;

import java.util.ArrayList;
import java.util.List;


public class ContractsBookTests extends Assert {
    @Test
    public void create_CreateEmptyContractsBook_ContCountEqualsZero() {
        ContractsBook contractsBook = ContractsBook.create();
        assertEquals(0, contractsBook.getContCount());
    }


    @Test
    public void addCont_addContWithNumberAndDate_ContCountEqualsOne() {
        ContractsBook contractsBook = ContractsBook.create();
        contractsBook.addCont("number", "date");
        assertEquals(1, contractsBook.getContCount());
    }
    /* @Test
    public void addCont_addDContWithNullNumber_ThrowsException(){
        ContractsBook contractsBook = ContractsBook.create();
        var exc = assertThrows(IllegalArgumentException.class, () -> contractsBook.addCont(null,"date"));
        assertTrue(exc.getMessage().toLowerCase().contains("Number can't be null"));
    }
    @Test
    public void addCont_addContWithNullDate_ThrowsException(){
        ContractsBook contractsBook = ContractsBook.create();
        var exc = assertThrows(IllegalArgumentException.class, () -> contractsBook.addCont("number",null));
        assertTrue(exc.getMessage().toLowerCase().contains("Date can'tt be null"));
    }


    @Test
    public void addCont_addContWithNullNumberAndNullDate_ThrowsException(){
        ContractsBook contractsBook = ContractsBook.create();
        var exc = assertThrows(IllegalArgumentException.class, () -> contractsBook.addCont(null,null));
        assertTrue(exc.getMessage().toLowerCase().contains("Number can't be null") &&
                exc.getMessage().toLowerCase().contains("Date can't be null"));
    }
    */







    @Test
    public void registerPaymentCont_registerPaymentContWithoutData_PaymentContCountEqualsZero() {
        ContractsBook contractsBook = ContractsBook.create();
        contractsBook.addCont("number", "date");
        assertEquals(0, contractsBook.getConts().get("number").getPaymentContCount());
    }
    @Test
    public void registerPaymentCont_registerPaymentContWithData_PaymentContCountEqualsOne() {
        ContractsBook contractsBook = ContractsBook.create();
        contractsBook.addCont("number", "date");
        contractsBook.registerPaymentCont(100000, 1, "number", TypeOfPaymentCont.BankOrder, "20010101");
        assertEquals(1, contractsBook.getConts().get("number").getPaymentContCount());
    }
    @Test
    public void registerPaymentCont_registerPaymentContWithData_PaymentContCountEqualsThree() {
        ContractsBook contractsBook = ContractsBook.create();
        contractsBook.addCont("number", "date");
        contractsBook.registerPaymentCont(100000, 1, "number", TypeOfPaymentCont.PaymentOrder, "20000709");
        contractsBook.registerPaymentCont(200000, 2, "number", TypeOfPaymentCont.PaymentOrder, "20201220");
        contractsBook.registerPaymentCont(300000, 3, "number", TypeOfPaymentCont.BankOrder, "20050505");
        assertEquals(3, contractsBook.getConts().get("number").getPaymentContCount());
    }
    /* не раб.
    @Test
    public void registerPaymentCont_registerPaymentContWithSumLessThenZero_TrowsException(){
        ContractsBook contractsBook = ContractsBook.create();
        contractsBook.addCont("number","date");
        var exc = assertThrows(IllegalArgumentException.class, () ->
                contractsBook.registerPaymentCont(-12000, 1, "number", TypeOfPaymentCont.PaymentOrder, "20010901"));
        assertTrue(exc.getMessage().toLowerCase().contains("Sum is positive"));
    }
     */







    @Test
    public void getList_getListOfAllPayments_equalLists(){
        ContractsBook contractsBook = ContractsBook.create();
        contractsBook.addCont("number","date");
        contractsBook.registerPaymentCont(700000, 1, "number", TypeOfPaymentCont.PaymentOrder, "20000202");
        contractsBook.registerPaymentCont(100000, 2, "number", TypeOfPaymentCont.BankOrder, "20000202");
        contractsBook.registerPaymentCont(600000, 3, "number", TypeOfPaymentCont.PaymentOrder, "20000202");
        contractsBook.addCont("number2","date");
        contractsBook.registerPaymentCont(300000, 1, "number2", TypeOfPaymentCont.PaymentOrder, "20000202");
        contractsBook.registerPaymentCont(200000, 2, "number2", TypeOfPaymentCont.BankOrder, "20000202");
        contractsBook.registerPaymentCont(900000, 3, "number2", TypeOfPaymentCont.PaymentOrder, "20000202");
        List<Integer> paymentConts = new ArrayList();
        paymentConts.add(700000);
        paymentConts.add(100000);
        paymentConts.add(600000);
        paymentConts.add(300000);
        paymentConts.add(200000);
        paymentConts.add(900000);
        assertArrayEquals(paymentConts.toArray(), contractsBook.getAllPayments().toArray());
    }


    @Test
    public void deletePaymentCont_DeletePaymentContWithContNumberAndDate_PaymentContCountEqualsZero(){
        ContractsBook contractsBook = ContractsBook.create();
        contractsBook.addCont("number","20160703");
        contractsBook.registerPaymentCont(100000,2, "number", TypeOfPaymentCont.PaymentOrder,"20161023");
        contractsBook.registerPaymentCont(100000,1, "number", TypeOfPaymentCont.PaymentOrder,"202001221");
        contractsBook.deletePayment( "number", 2, "20161023");
        contractsBook.deletePayment( "number", 1, "202001221");
        assertEquals(0, contractsBook.getConts().get("number").getPaymentContCount());
    }
    @Test
    public void deletePaymentCont_DeleteNonExistentPaymentCont_EqualsOne(){
        ContractsBook contractsBook = ContractsBook.create();
        contractsBook.addCont("number","20100116");
        contractsBook.registerPaymentCont(100,2, "number", TypeOfPaymentCont.PaymentOrder,"20101216");
        contractsBook.deletePayment( "number", 1, "20170313");
        assertEquals(1, contractsBook.getConts().get("number").getPaymentContCount());
    }



    @Test
    public void getGeneralSum_GetContGeneralSumOfPayments_GeneralSumEquals80000(){
        ContractsBook contractsBook = ContractsBook.create();
        contractsBook.addCont("153","20210212");
        contractsBook.registerPaymentCont(30000,101,"153", TypeOfPaymentCont.BankOrder, "20220101");
        contractsBook.registerPaymentCont(50000,102,"153", TypeOfPaymentCont.BankOrder, "20130402");
        assertEquals(80000, contractsBook.getConts().get("153").getSumOfPayments());
    }



    @Test
    public void getListOfPayments_GetAllPaymentsFromCont_EqualLists(){
        ContractsBook contractsBook = ContractsBook.create();
        contractsBook.addCont("333","20150313");
        contractsBook.registerPaymentCont(600000,201,"333", TypeOfPaymentCont.BankOrder, "20220101");
        contractsBook.registerPaymentCont(400000,202,"333", TypeOfPaymentCont.BankOrder, "20110919");
        contractsBook.addCont("545","20200327");
        contractsBook.registerPaymentCont(300000,201,"545", TypeOfPaymentCont.BankOrder, "20220101");
        contractsBook.registerPaymentCont(100000,202,"545", TypeOfPaymentCont.BankOrder, "20110919");
        List<Integer> sums = new ArrayList();
        sums.add(600000);
        sums.add(400000);
        assertArrayEquals(sums.toArray(), contractsBook.getConts().get("333").getListOfPayments().toArray());
    }
}