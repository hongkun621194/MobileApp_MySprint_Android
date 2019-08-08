package Data;

import org.testng.annotations.DataProvider;

public class SPRCOM_107202_DeviceOptions {

    @DataProvider(name = "DeviceOption")
    public Object[][] getData() {
        Object[][] deviceOption = {
                {"Phones", "Apple", "Apple iPhone XR", "Lease", "Unlimited Savings Plan", "Add protection"},
//                {"Phones", "Apple", "Apple iPhone XS", "Buy it with 24 monthly installments", "Unlimited Savings Plan", "Add protection"},
//                {"Phones", "Apple", "Apple iPhone XS", "Buy it at full price", "Unlimited Savings Plan", "Add protection"},
//
                {"Tablets", "Apple", "Apple iPad (6th generation)", "Buy", "Unlimited Tablet Plan", "Add protection"},
//                {"Tablets", "Apple", "10.5-inch Apple iPad Pro", "Buy it with 24 monthly installments", "Unlimited Savings Plan", "Add protection"}
//                {"Tablets", "Apple", "Apple iPad mini 4", "Buy it at full price", "Unlimited Savings Plan", "Add protection"}

                {"Watches", "Apple", "Apple Watch 4", "Buy", "Unlimited Savings Plan", "Add protection"},

                {"Others", "Sprint", "Sprint Drive", "Finance", "Sprint Drive Wifi 2GB Plan", "Add protection"},
//                {"Others", "Sprint", "Sprint Drive", "Buy it with 24 monthly installments", "Sprint Drive Wifi Unlimited", "Decline protection"},
        };
        return deviceOption;
    }
}
