package Data;

import org.testng.annotations.DataProvider;

public class SPRCOM_107202_DeviceOptions_Upgrade {

    @DataProvider(name = "DeviceOption")
    public Object[][] getData() {
        Object[][] deviceOption = {
                {"Phones", "Apple", "Apple iPhone XS", "Sprint Flex 18-mo. lease", "Unlimited Savings Plan", "Add protection", "Buy this phone", "Apple iPhone 8 Plus"},
//                {"Phones", "Apple", "Apple iPhone XS", "Sprint Flex 18-mo. lease", "Unlimited Savings Plan", "Add protection", "Return this phone", "Apple iPhone 8 Plus"},

        };
        return deviceOption;
    }
}
