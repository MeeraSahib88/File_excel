package com.example.demo.utils;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.BuyRate;
import com.example.demo.payload.response.PricesList;
import com.example.demo.repository.BuyRateRepository;

@Component
public class FileAsyn {

	@Autowired
	ConvertDateToJodaDateUtil convertDateToJodaDateUtil;

	@Autowired
	BuyRateRepository buyRateRepository;

	public void buyPriceList(List<PricesList> list) {
		List<PricesList> pricesLists = new ArrayList<>();
		if (!list.isEmpty()) {
			for (PricesList pricesList : list) {
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss z uuuu")
						.withLocale(Locale.US);
				ZonedDateTime dateTime = ZonedDateTime.parse(pricesList.getDate(), dateTimeFormatter);
				LocalDate date = dateTime.toLocalDate();
				DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String output = date.format(fLocalDate);
				PricesList rateRequest = PricesList.builder().date(output).price(pricesList.getPrice()).build();
				pricesLists.add(rateRequest);
			}
			buyPriceSaving(pricesLists);
		}
	}

	private void buyPriceSaving(List<PricesList> pricesLists) {
		pricesLists.forEach(e -> {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(e.getDate().toString(), dateTimeFormatter);

			for (int i = 0; i <= 23; i++) {
				LocalTime fromlocalTime = LocalTime.parse(convertDateToJodaDateUtil.addFromTime().get(i).getTime());
				LocalTime tolocalTime = LocalTime.parse(convertDateToJodaDateUtil.toFromTime().get(i).getTime());
				new DateTime();
				BuyRate buyRate = BuyRate.builder().date(convertDateToJodaDateUtil.convertdate(date))
						.fromTime(fromlocalTime).toTime(tolocalTime).powerRate(1).createdDate(DateTime.now())
						.updatedDate(DateTime.now()).priceRate(Double.parseDouble(e.getPrice())).build();
				buyRateRepository.save(buyRate);
			}

		});
	}

}
