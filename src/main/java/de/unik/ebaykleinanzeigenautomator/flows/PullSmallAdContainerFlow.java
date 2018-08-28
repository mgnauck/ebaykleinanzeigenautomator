package de.unik.ebaykleinanzeigenautomator.flows;

import de.unik.ebaykleinanzeigenautomator.datamodels.SmallAdContainer;
import de.unik.ebaykleinanzeigenautomator.pageobjects.pages.Homepage;
import de.unik.ebaykleinanzeigenautomator.pageobjects.pages.LoginPage;
import de.unik.ebaykleinanzeigenautomator.pageobjects.pages.ManagedAdsPage;
import de.unik.ebaykleinanzeigenautomator.util.Context;

public class PullSmallAdContainerFlow
{
	public SmallAdContainer run()
	{
        Homepage homepage = new OpenHomepageFlow().run();
        LoginPage loginPage = homepage.header.clickLogin();
        
        loginPage.fillLoginDetails();
        homepage = loginPage.clickLogin();
        
        ManagedAdsPage managedAdsPage = homepage.header.clickManagedAds();
                
        SmallAdContainer smallAdContainer = new SmallAdContainer();
        managedAdsPage.pullAllSmallAds(smallAdContainer);
        
        managedAdsPage.header.clickLogout();

        return smallAdContainer;
	}
}
