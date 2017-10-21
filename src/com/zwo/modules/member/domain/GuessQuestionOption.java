package com.zwo.modules.member.domain;

import java.io.Serializable;
import java.util.List;

public class GuessQuestionOption implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GuessQuestion getGuessQuestion() {
		return guessQuestion;
	}

	public void setGuessQuestion(GuessQuestion guessQuestion) {
		this.guessQuestion = guessQuestion;
	}

	public List<GuessQuestionOptions> getGuessQuestionOptions() {
		return guessQuestionOptions;
	}

	public void setGuessQuestionOptions(
			List<GuessQuestionOptions> guessQuestionOptions) {
		this.guessQuestionOptions = guessQuestionOptions;
	}

	private GuessQuestion guessQuestion;
	private List<GuessQuestionOptions> guessQuestionOptions;
}
