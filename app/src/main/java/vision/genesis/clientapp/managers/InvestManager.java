package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
import io.swagger.client.model.Invest;
import io.swagger.client.model.InvestmentProgramRequests;
import io.swagger.client.model.InvestmentProgramRequestsFilter;
import io.swagger.client.model.InvestmentProgramViewModel;
import io.swagger.client.model.InvestmentProgramsFilter;
import io.swagger.client.model.InvestmentProgramsViewModel;
import io.swagger.client.model.InvestorDashboard;
import io.swagger.client.model.TradesFilter;
import io.swagger.client.model.TradesViewModel;
import io.swagger.client.model.WalletsViewModel;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.model.ProgramRequest;

/**
 * GenesisVision
 * Created by Vitaly on 1/24/18.
 */

public class InvestManager
{
	public BehaviorSubject<InvestmentProgramsFilter> filterSubject = BehaviorSubject.create();

	private InvestorApi investorApi;

	private ManagerApi managerApi;

	public InvestManager(InvestorApi investorApi, ManagerApi managerApi) {
		this.investorApi = investorApi;
		this.managerApi = managerApi;

		filterSubject.onNext(new InvestmentProgramsFilter());
	}

	public InvestmentProgramsFilter getFilter() {
		return filterSubject.getValue();
	}

	public void setFilter(InvestmentProgramsFilter filter) {
		filterSubject.onNext(filter);
	}

	public Observable<InvestmentProgramsViewModel> getProgramsList(InvestmentProgramsFilter filter) {
		return investorApi.apiInvestorInvestmentProgramsPost(AuthManager.token.getValue(), filter);
	}

//	public List<InvestmentProgram> parseInvestmentProgramsModel(InvestmentProgramsViewModel model) {
//		List<InvestmentProgram> investmentPrograms = new ArrayList<>();
//		for (io.swagger.client.model.InvestmentProgram program : model.getInvestments()) {
//			InvestmentProgram investmentProgram = new InvestmentProgram(program);
//			investmentProgram.chartData = MockProfitChartDataUtil.getEntries();
//			investmentPrograms.add(investmentProgram);
//		}
//		return investmentPrograms;
//	}

	public Observable<WalletsViewModel> invest(ProgramRequest investRequest) {
		Invest model = new Invest();
		model.setInvestmentProgramId(investRequest.programId);
		model.setAmount(investRequest.amount);
		return investorApi.apiInvestorInvestmentProgramsInvestPost(AuthManager.token.getValue(), model);
	}

	public Observable<Void> withdraw(ProgramRequest withdrawalRequest) {
		Invest model = new Invest();
		model.setInvestmentProgramId(withdrawalRequest.programId);
		model.setAmount(withdrawalRequest.amount);
		return investorApi.apiInvestorInvestmentProgramsWithdrawPost(AuthManager.token.getValue(), model);
	}

	public Observable<InvestorDashboard> getInvestments(String sorting) {
		return investorApi.apiInvestorDashboardGet(AuthManager.token.getValue(), sorting);
	}

	public Observable<InvestmentProgramViewModel> getInvestmentProgramDetails(UUID programId) {
		return investorApi.apiInvestorInvestmentProgramGet(programId, AuthManager.token.getValue());
	}

	public Observable<InvestmentProgramRequests> getInvestmentProgramRequests(InvestmentProgramRequestsFilter filter) {
		return investorApi.apiInvestorInvestmentProgramRequestsPost(AuthManager.token.getValue(), filter);
	}

	public Observable<TradesViewModel> getProgramTrades(TradesFilter filter) {
		return investorApi.apiInvestorInvestmentProgramTradesPost(filter);
	}

	public Observable<Void> cancelRequest(UUID requestId) {
		return investorApi.apiInvestorInvestmentProgramsCancelInvestmentRequestPost(requestId, AuthManager.token.getValue());
	}
}