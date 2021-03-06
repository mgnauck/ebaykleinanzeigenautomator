package de.unik.ebaykleinanzeigenautomator.flows;

import de.unik.ebaykleinanzeigenautomator.pageobjects.pages.Homepage;
import de.unik.ebaykleinanzeigenautomator.pageobjects.pages.LoginPage;
import de.unik.ebaykleinanzeigenautomator.pageobjects.pages.ManagedAdsPage;
import de.unik.ebaykleinanzeigenautomator.util.Context;

public class ChangeSmallAdsStatusFlow
{
    public boolean run(boolean activate)
    {
        try
        {
            Homepage homepage = new OpenHomepageFlow().run();
            LoginPage loginPage = homepage.header.clickLoginLink();

            loginPage.fillLoginDetails();
            homepage = loginPage.clickLogin();

            ManagedAdsPage managedAdsPage = homepage.header.clickManagedAds();

            if(activate)
            {
                managedAdsPage.activateAllSmallAds();
            }
            else
            {
                managedAdsPage.deactivateAllSmallAds();
            }

            managedAdsPage.header.clickLogoutLink();
        }
        catch (RuntimeException | AssertionError e)
        {
            System.out.println("Failed to " + (activate ? "activate" : "deactivate") + " small ads");
            System.out.println("Error was: " + e.toString());

            if (Context.get().getConfiguration().projectDebug())
            {
                e.printStackTrace();
            }

            return false;
        }

        return true;
    }
}
