package nl.multicode.bsn;

import nl.multicode.bsn.service.BsnService;


public class App {

    public static void main(String[] args) {
        BsnService bsnService = new BsnService();
        System.out.println(bsnService.generateBsn());
    }
}
