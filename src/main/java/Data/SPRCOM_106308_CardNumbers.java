package Data;

import org.testng.annotations.DataProvider;

public class SPRCOM_106308_CardNumbers {

    @DataProvider(name = "CardNumber")
    public Object[][] getData() {
        Object[][] card_data = {
                {"4444424444444440", "Visa", "LOAD TEST ACCOUNTS"},
                {"5500005555555559", "MasterCard", "LOAD TEST ACCOUNTS"},
                {"36111111111111", "MasterCard Diners", "LOAD TEST ACCOUNTS"},
//                {"371449635398431", "American Express", "LOAD TEST ACCOUNTS"},
//                {"6011000995500000", "Discover", "LOAD TEST ACCOUNTS"},
//                {"3528000000000007", "JCB", "CREDIT CARDS (UNITED STATES)"},
        };
        return card_data;
    }
}
