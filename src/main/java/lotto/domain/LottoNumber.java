package lotto.domain;

import java.util.Collections;
import java.util.List;

public class LottoNumber {
	private final boolean isManual;
	private final List<Integer> numbers;
	private static final int LOTTO_NUMBERS_SIZE = 6;
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 45;

	public LottoNumber(List<Integer> numbers) {
		validateNumbers(numbers);
		this.numbers = Collections.unmodifiableList(numbers);
		this.isManual = false;
	}

	public LottoNumber(List<Integer> numbers, boolean isManual) {
		validateNumbers(numbers);
		this.numbers = Collections.unmodifiableList(numbers);
		this.isManual = isManual;
	}

	public int matchCount(LottoNumber matchTargetNumber) {
		return Math.toIntExact(matchTargetNumber.numbers
			.stream()
			.filter(this::isContains)
			.count());
	}

	public boolean isContains(int number) {
		return this.numbers.contains(number);
	}

	public boolean isAuto() {
		return !this.isManual;
	}

	private void validateNumbers(List<Integer> numbers) {
		this.checkValidSize(numbers);
		this.checkValidRange(numbers);
		this.checkSorted(numbers);
	}

	private void checkValidSize(List<Integer> numbers) {
		if (numbers.size() != LOTTO_NUMBERS_SIZE) {
			throw new IllegalArgumentException(String.format("로또 번호는 %d개 여야 합니다.", LOTTO_NUMBERS_SIZE));
		}
	}

	private void checkValidRange(List<Integer> numbers) {
		if (numbers.stream().anyMatch(number -> number > MAX_NUMBER || number < MIN_NUMBER)) {
			throw new IllegalArgumentException(String.format("로또 번호는 %d 과 %d 사이의 숫자여야 합니다.", MIN_NUMBER, MAX_NUMBER));
		}
	}

	private void checkSorted(List<Integer> numbers) {
		boolean isValid = true;
		for (int i = 1; i < numbers.size(); i++) {
			isValid = isValid && numbers.get(i) > numbers.get(i-1);
		}

		if (!isValid) {
			throw new IllegalArgumentException(String.format("로또 번호는 각자 다른 숫자로 오름차순 정렬되어야 합니다. %s", numbers.toString()));
		}
	}

	@Override
	public String toString() {
		return this.numbers.toString();
	}
}
