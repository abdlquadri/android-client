package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
import vision.genesis.clientapp.managers.AuthManager;

/**
 * GenesisVision
 * Created by Vitaly on 1/22/18.
 */

@Module
public class AuthModule
{
	@Provides
	@Singleton
	public AuthManager provideAuthManager(InvestorApi investorApi, ManagerApi managerApi) {
		return new AuthManager(investorApi, managerApi);
	}
}
