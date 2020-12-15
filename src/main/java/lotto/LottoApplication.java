package lotto;

import lotto.domain.LottoNumbers;
import lotto.domain.LottoRunner;
import lotto.domain.WinningNumber;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoApplication {
	public static void main(String[] args) {
		long payment = InputView.scanPayment();
		int manualNumberSize = InputView.scanManualNumberSize();
		LottoNumbers manualLottoNumbers = InputView.scanManualNumbers(manualNumberSize);

		LottoRunner runner = new LottoRunner(payment, manualLottoNumbers);
		ResultView.printLottoNumbers(manualNumberSize, runner.getAutoLottoNumbers());

		WinningNumber winningNumber = InputView.scanWinningNumber();
		ResultView.printMatchResult(runner.match(winningNumber));
	}
}
