
package acme.features.investor.application;

public class ParameterMethods {

	public static boolean isSpam(final String text, final String spamInfo, final Double threshold) {
		boolean res = false;
		Integer textCounter = 0;
		String[] spamWords = spamInfo.split(",");
		String[] textWords = text.split(" ");
		Integer textLength = textWords.length;
		int i;

		for (i = 0; i < spamWords.length; i++) {
			spamWords[i] = spamWords[i].trim();
		}

		for (String t : textWords) {
			if (ParameterMethods.haveSpam(t, spamWords)) {
				textCounter += 1;
			}
		}

		if ((double) textCounter / textLength * 100 >= threshold) {
			res = true;
		}

		return res;
	}

	private static boolean haveSpam(final String t, final String[] spamWords) {
		boolean res = false;

		for (String s : spamWords) {
			if (t.contains(s)) {
				res = true;
			}
		}
		return res;
	}

}
